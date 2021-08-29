package net.pixievice.pixiehub.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pixievice.pixiehub.ChatUtils;
import net.pixievice.pixiehub.Main;
import net.pixievice.pixiehub.ReferanceLang;
import net.pixievice.pixiehub.files.HubConfig;
import net.pixievice.pixiehub.managers.Hub;

public class HubCommand implements CommandExecutor {
	
	Hub hub = new Hub();
	ReferanceLang rl = new ReferanceLang();

	private Main main;
	
	public HubCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
		
		Boolean moh = main.getConfig().getBoolean("MessageOnHub");
		
		if (sender instanceof Player) {
		Player player = (Player) sender;
		if (player.hasPermission("pixie.hub")) {
		if (HubConfig.get().getString("Hub.X").equals("")) {
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.nohubloc()));
		} else {
				hub.hubTeleport(player);
				if (moh == true) {
					player.sendMessage(ChatUtils.chat(rl.prefix() + rl.onhub()));
					}
				}
			} else {
				player.sendMessage(ChatUtils.chat(rl.prefix() + rl.unknown()));	
			}
	
		} else { 
			Bukkit.getLogger().info(ChatUtils.chat(rl.prefix() + rl.unknown())); 
		}
		return true;
	}
}
