package net.pixievice.pixiehub.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pixievice.pixiehub.ChatUtils;
import net.pixievice.pixiehub.Main;
import net.pixievice.pixiehub.ReferanceLang;
import net.pixievice.pixiehub.files.Pads;
import net.pixievice.pixiehub.jumppads.PadManager;

public class JumpPadCommands implements CommandExecutor {
	
	ReferanceLang rl = new ReferanceLang();
	PadManager pm = new PadManager();
	
	Main main;
	public JumpPadCommands(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
		
		String boarder = new String(ChatUtils.chat("&d--------------------"));
		Material jumppad = Material.matchMaterial(main.getConfig().getString("JumpPads.block"));
		
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (player.hasPermission("pixie.jp.admin")) {
			if (args.length == 4 && args[0].equalsIgnoreCase("create")) {
			try {
				Integer.parseInt(args[2]);
				Integer.parseInt(args[3]);
			if (Pads.get().getConfigurationSection("JumpPads." + args[1]) == null) {
				pm.createData(player, args[1], Integer.valueOf(args[2]), Integer.valueOf(args[3]));
				pm.addBlock(player, args[1], jumppad);
					

				player.sendMessage(ChatUtils.chat(rl.prefix() + rl.createpad(player).replaceAll("%name%", args[1])));
			} else {
					player.sendMessage(ChatUtils.chat(rl.prefix() + rl.padalreadyexists(player)));
			}
			} catch (NumberFormatException e) { player.sendMessage(ChatUtils.chat(rl.prefix() + "&cVelocity & force must be a number.")); }
			} else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
			if (Pads.get().getConfigurationSection("JumpPads." + args[1]) != null) {
				pm.removeBlock(args[1]);
				Pads.save();
					
				player.sendMessage(ChatUtils.chat(rl.prefix() + rl.removepad(player).replaceAll("%name%", args[1])));
			} else {
				player.sendMessage(ChatUtils.chat(rl.prefix() + rl.padnotfound(player)));
			}
			} else if (args.length == 3 && args[0].equalsIgnoreCase("setsound")) {
			if (Pads.get().getConfigurationSection("JumpPads." + args[1]) != null) {
				pm.modifySound(args[1], args[2]);
				player.sendMessage(ChatUtils.chat(rl.prefix() + rl.updatepadsounds(player).replaceAll("%sound%", args[2])));
				player.sendMessage(ChatUtils.chat("&7(If the sound name was entered wrongly it will throw an error)"));
			} else {
				player.sendMessage(ChatUtils.chat(rl.prefix() + rl.padnotfound(player)));
			}
			} else if (args.length == 3 && args[0].equalsIgnoreCase("setparticle")) {
			if (Pads.get().getConfigurationSection("JumpPads." + args[1]) != null) {
				pm.modifyParticle(args[1], args[2]);
				player.sendMessage(ChatUtils.chat(rl.prefix() + rl.updatepadparticle(player).replaceAll("%particle%", args[2])));
				player.sendMessage(ChatUtils.chat("&7(If the particle name was entered wrongly it will throw an error)"));
			} else {
				player.sendMessage(ChatUtils.chat(rl.prefix() + rl.padnotfound(player)));
			}
			} else if (args.length == 4 && args[0].equalsIgnoreCase("setpower")) {
			try {
				pm.modifyPower(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
				player.sendMessage(ChatUtils.chat(rl.prefix() + "&aUpdated force &7(" + args[2] + ") &aand velocity &7(" + args[3] + ")&a!"));
			} catch (NumberFormatException e) { player.sendMessage(ChatUtils.chat(rl.prefix() + "&cVelocity & force must be a number.")); }
			} else if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
				pm.listPads(player);
			} else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
				player.sendMessage(ChatUtils.chat(boarder));
				player.sendMessage(ChatUtils.chat("&e/PJP create <name> <force> <velocity>"));
				player.sendMessage(ChatUtils.chat("&e/PJP remove <name>"));
				player.sendMessage(ChatUtils.chat("&e/PJP setsound <name> <sound>"));
				player.sendMessage(ChatUtils.chat("&e/PJP setparticle <name> <particle>"));
				player.sendMessage(ChatUtils.chat("&e/PJP setpower <name> <force> <velocity>"));
				player.sendMessage(ChatUtils.chat("&e/PJP list"));
				player.sendMessage(ChatUtils.chat(boarder));
			} else {
				player.sendMessage(ChatUtils.chat(rl.prefix() + rl.unknownjp(player)));
			}
			
			
		} else {
			player.sendMessage(ChatUtils.chat(rl.prefix()) + rl.noperm(player));
		}
		} else {
			Bukkit.getLogger().info(ChatUtils.chat(rl.prefix() + rl.notplayer()));
		}
		
	return true;	
	}

}
