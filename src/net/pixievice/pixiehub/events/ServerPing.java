package net.pixievice.pixiehub.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;

import net.pixievice.pixiehub.ChatUtils;
import net.pixievice.pixiehub.Main;
import net.pixievice.pixiehub.files.PlayersConfig;

public class ServerPing implements Listener {
	
	public static boolean maintenanceMode = false;
	
	private Main main;
	public ServerPing(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onPing(ServerListPingEvent e) {
		
		Boolean usesettings = main.getConfig().getBoolean("ServerSettings.use");
		
		if (usesettings == true) {
			String motd = main.getConfig().getString("ServerSettings.motd");
			String mmotd = main.getConfig().getString("ServerSettings.maintenance-motd");
		int maxP = main.getConfig().getInt("ServerSettings.max-players");
		
		if (maintenanceMode == false) {
			e.setMotd(ChatUtils.chat(motd));
		} else if (maintenanceMode == true) {
			e.setMotd(ChatUtils.chat(mmotd));
		}
			e.setMaxPlayers(maxP);
		}
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		
		Player player = e.getPlayer();
		String ip = e.getAddress().getHostAddress();
		
		if (PlayersConfig.get().getConfigurationSection("Players." + player.getUniqueId()) == null) {
			PlayersConfig.get().set("Players." + player.getUniqueId() + ".IP", ip);
			PlayersConfig.get().set("Players." + player.getUniqueId() + ".Username", player.getName());
			PlayersConfig.get().set("Players." + player.getUniqueId() + ".silentjoin", false);
			PlayersConfig.save();
		}
		
	}
	
	@EventHandler
	public void maintenanceJoin(PlayerJoinEvent e) {
		
		Player player = e.getPlayer();
		String mkick = main.getConfig().getString("ServerSettings.maintenance-disconnect");
		
		if (maintenanceMode == true) {
			if (!player.hasPermission("pixie.maintenance.admin")) {
				player.kickPlayer(ChatUtils.chat(mkick));
			}
		}
		
	}

}
