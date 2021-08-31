package net.pixievice.pixiehub.commands;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pixievice.pixiehub.ChatUtils;
import net.pixievice.pixiehub.Main;
import net.pixievice.pixiehub.ReferanceLang;
import net.pixievice.pixiehub.files.WarpsConfig;
import net.pixievice.pixiehub.managers.Warps;

public class WarpCommands implements CommandExecutor {
	
	ReferanceLang rl = new ReferanceLang();
	Warps warps = new Warps();
	
	Main main;
	
	public WarpCommands(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String border = new String(ChatUtils.chat("&d--------------------"));
		
		if (sender instanceof Player) {
		Player player = (Player) sender;
		if (args.length == 2 && args[0].equalsIgnoreCase("setwarp")) {
		if (player.hasPermission("pixie.setwarp")) {
		if (WarpsConfig.get().getString("Warps." + args[1]) == null) {
			
			warps.setWarp(player, args[1]);
			player.sendMessage(ChatUtils.chat(rl.prefix() + "&aNew warp named &7" + args[1] + " &ahas set to your location!"));
		
		} else {
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.warpalreadyexists(player)));
		}
						
		} else {
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.noperm(player)));	
		}
		
		} else {
		if (args.length == 2 && args[0].equalsIgnoreCase("delwarp")) {
		if (player.hasPermission("pixie.delwarp")) {
		if (!(WarpsConfig.get().getString("Warps." + args[1]) == null)) {
			
			WarpsConfig.get().set("Warps." + args[1], null);
			WarpsConfig.save();
			player.sendMessage(ChatUtils.chat(rl.prefix()+ "&cWarp &7" + args[1] + " &chas been deleted!"));
								
		} else {
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.warpnotfound(player)));
		}
		
		} else {
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.noperm(player)));
		}
		
		} else {
		if (args.length == 2 && args[0].equalsIgnoreCase("warp")) {
		if (player.hasPermission("pixie.warps." + args[1])) {
		if (WarpsConfig.get().getConfigurationSection("Warps." + args[1]) != null) {

			warps.warpTeleport(player, args[1]);	
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.onwarp(player).replaceAll("%warp%", args[1])));
		
		} else {
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.warpnotfound(player)));
		}
		
		} else {
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.nowarpperm(player)));
		}
		} else {
		if (args.length == 1 && args[0].equalsIgnoreCase("warps")) {
		if (player.hasPermission("pixie.warps")) {
			Set<String> keys = WarpsConfig.get().getConfigurationSection("Warps").getKeys(false);
			player.sendMessage(ChatUtils.chat("&aWarps:"));
		for (String key : keys) {
			player.sendMessage(ChatUtils.chat("&7- &d" + key));
		}
		} else {
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.noperm(player)));
		}
		
		} else {
		if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
		if (player.hasPermission("pixie.help.warp")) {
			
			player.sendMessage(ChatUtils.chat(border));
			player.sendMessage(ChatUtils.chat("&e/Pwarp Setwarp <new-warp> &7| creates a new warp."));
			player.sendMessage(ChatUtils.chat("&e/Pwarp Delwarp <warp> &7| deletes a warp."));
			player.sendMessage(ChatUtils.chat("&e/Pwarp warps &7| displayes a list of all warps."));
			player.sendMessage(ChatUtils.chat("&e/Pwarp <warp> &7| teleports you to a warp."));
			player.sendMessage(ChatUtils.chat(border));
			
		} else {
			
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.noperm(player)));
		}
		
		} else {
			player.sendMessage(ChatUtils.chat(rl.prefix() + rl.unknownwarp(player)));
		}
		}
		}
		}
		}
	} else {
		Bukkit.getLogger().info(ChatUtils.chat(rl.prefix() + rl.notplayer()));
	}
		
	return true;	
	}

}
