package net.pixievice.pixiehub.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pixievice.pixiehub.ChatUtils;
import net.pixievice.pixiehub.Main;
import net.pixievice.pixiehub.ReferanceLang;

public class TeleportCommand implements CommandExecutor {
	
	ReferanceLang rl = new ReferanceLang();
	
	Main main;
	
	public TeleportCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
		
		if (sender instanceof Player) {
		Player player = (Player) sender;
		if (player.hasPermission("pixie.teleport")) {
		if (args.length == 1) {
		Player target = Bukkit.getPlayer(args[0]);
		if (target != null) {	
		if (target.getDisplayName().equals(player.getDisplayName())) {
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.selfteleport(player)));
		} else {
			player.teleport(target.getLocation());
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.onteleport(player).replaceAll("%target%", target.getDisplayName())));
		}
					
		} else {
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.teleportnpo(player)));
		}
				
		} else {
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.teleportusage(player)));
		}
			
		} else {
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.noperm(player)));
		}
		
		} else {
			Bukkit.getLogger().info(ChatUtils.chat(rl.prefix() + rl.notplayer()));
		}
		

	return true;
	}

}
