package net.pixievice.pixiehub.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.pixievice.pixiehub.ChatUtils;
import net.pixievice.pixiehub.Main;
import net.pixievice.pixiehub.ReferanceLang;
import net.pixievice.pixiehub.files.FileUpdaters;
import net.pixievice.pixiehub.files.Lang;
import net.pixievice.pixiehub.files.PlayersConfig;
import net.pixievice.pixiehub.files.WarpsConfig;
import net.pixievice.pixiehub.holograms.HoloManager;
import net.pixievice.pixiehub.managers.MC;

public class MainCommands implements CommandExecutor {

	MC mc = new MC();
	HoloManager hm = new HoloManager();
	FileUpdaters fu = new FileUpdaters();
	ReferanceLang rl = new ReferanceLang();
  
  private Main main;
  
  public MainCommands(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    String boarder = new String(ChatUtils.chat("&d--------------------"));
    
    if (sender instanceof Player) {
    Player player = (Player)sender;
    Boolean silentjoin = Boolean.valueOf(PlayersConfig.get().getBoolean("Players." + player.getUniqueId() + ".silentjoin"));
    if (args.length == 1 && args[0].equalsIgnoreCase("silentjoin")) {
    if (player.hasPermission("pixie.silentjoin")) {
    if (silentjoin.booleanValue()) {
        sender.sendMessage(ChatUtils.chat(rl.prefix() + rl.sjd()));
        PlayersConfig.get().set("Players." + player.getUniqueId() + ".silentjoin", Boolean.valueOf(false));
        PlayersConfig.save();
    } else if (!silentjoin.booleanValue()) {
        sender.sendMessage(ChatUtils.chat(rl.prefix() + rl.sje()));
        PlayersConfig.get().set("Players." + player.getUniqueId() + ".silentjoin", Boolean.valueOf(true));
        PlayersConfig.save();
    } 
    } else {
        player.sendMessage(ChatUtils.chat(rl.prefix() + rl.noperm()));
    } 
    } else if (args.length == 1 && args[0].equalsIgnoreCase("sethub")) {
    if (player.hasPermission("pixie.sethub")) {
    	
    	mc.setHub(player);
        player.sendMessage(ChatUtils.chat(rl.prefix() + rl.updatehub()));
          
    } else {
        player.sendMessage(ChatUtils.chat(rl.prefix() + rl.noperm()));
    } 
    } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
    if (player.hasPermission("pixie.reload")) {
          
    	player.sendMessage(ChatUtils.chat(rl.prefix() + "&7config.yml&a, &7lang.yml&a, and &7holograms &areloaded!"));
    	Lang.reload();
        main.reloadConfig();
        hm.reloadStands();
        
    } else {
        player.sendMessage(ChatUtils.chat(rl.prefix() + rl.noperm()));
    } 
    } else if (args.length == 2 && args[0].equalsIgnoreCase("migratehub")) {
    if (player.hasPermission("pixie.migratehub")) {
    if (args[1] != null) {
    if (WarpsConfig.get().getConfigurationSection("Warps." + args[1]) != null) {
    	
        player.sendMessage(ChatUtils.chat(rl.prefix() + "&aMigrating..."));
        mc.migrateHub(args[1]);
        player.sendMessage(ChatUtils.chat(rl.prefix() + "&aDone!"));
             
    } else {
        player.sendMessage(ChatUtils.chat(rl.prefix() + rl.warpnotfound()));
    } 
    } else {
        player.sendMessage(ChatUtils.chat(rl.prefix() + rl.warpnotfound()));
    } 
    } else {
        player.sendMessage(ChatUtils.chat(rl.prefix() + rl.noperm()));
    } 
    } else if (args.length == 2 && args[0].equalsIgnoreCase("reset")) {
    	
    if (player.hasPermission("pixie.resetconfigs")) {
    if (args[1].equalsIgnoreCase("lang")) {
    	
        fu.resetLang();
        player.sendMessage(ChatUtils.chat(rl.prefix() + "&7lang.yml &ahas been reset!"));
    } else {
    if (args[1].equalsIgnoreCase("config")) {
    	
        fu.resetConfig();
        player.sendMessage(ChatUtils.chat(rl.prefix() + "&7config.yml &ahas been reset!"));
    } else {
    if (args[1].equalsIgnoreCase("all")) {
    	
        fu.resetAll();
        player.sendMessage(ChatUtils.chat(rl.prefix() + "&aAll configs been reset!"));
        
    }
    }
    }
    }
    } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
    if (player.hasPermission("pixie.help")) {
    	
        player.sendMessage(boarder);
        
    	player.sendMessage(ChatUtils.chat("&e/PHub Help &7| displayes this text."));
        player.sendMessage(ChatUtils.chat("&e/PWarp Help &7| displayes the warp help text."));
    	player.sendMessage(ChatUtils.chat("&e/PHolo Help &7| displays the hologram help text."));
    	player.sendMessage(ChatUtils.chat("&e/PJP Help &7| displays the jump pad help text."));
    	player.sendMessage(ChatUtils.chat("&e/PHub Sethub &7| changes the hub location to your location."));
    	player.sendMessage(ChatUtils.chat("&e/PHub MigrateHub <warp> &7| changes the hub location to a warp location."));
    	player.sendMessage(ChatUtils.chat("&e/PHub SilentJoin &7enabes/disables your ability to join silently."));
    	player.sendMessage(ChatUtils.chat("&e/PTeleport <player> &7| teleport to a player."));
    	player.sendMessage(ChatUtils.chat("&e/PHub Reload &7Reloads files."));
    	player.sendMessage(ChatUtils.chat("&e/Phub Reset <config/lang/all> &7| Resets configs to the version defaults."));
        player.sendMessage("");
    	player.sendMessage(ChatUtils.chat("&dTogglable via config:"));
    	player.sendMessage(ChatUtils.chat("&e/Hub &7Teleports you to the hub."));
    	player.sendMessage(ChatUtils.chat("&e/Stuck &7Teleports you to the hub."));
    	player.sendMessage(ChatUtils.chat("&e/Spawn &7Teleports you to the hub."));
    	player.sendMessage(ChatUtils.chat("&e/Lobby &7Teleports you to the hub."));
    	player.sendMessage(ChatUtils.chat("&e/L &7Teleports you to the hub."));
    	          
    	player.sendMessage(boarder);
    } else {
    	player.sendMessage(ChatUtils.chat(rl.prefix() + rl.noperm()));
    } 
    } else {
        player.sendMessage(ChatUtils.chat(rl.prefix() + rl.unknown()));
    }
    } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
    	
    	Bukkit.getLogger().info(ChatUtils.chat(String.valueOf(rl.prefix()) + "&7config.yml&a, &7lang.yml&a, and &7holograms &areloaded!"));
    	Lang.reload();
    	main.reloadConfig();
    	hm.reloadStands();
    	
    } else if (args.length == 2 && args[0].equalsIgnoreCase("reset")) {
  	if (args[1].equalsIgnoreCase("lang")) {
  		
  		fu.resetLang();
  		sender.sendMessage(ChatUtils.chat(rl.prefix() + "&7lang.yml &ahas been reset!"));
  	} else {
    if (args[1].equalsIgnoreCase("config")) {
    	
  	    fu.resetConfig();
  	    sender.sendMessage(ChatUtils.chat(rl.prefix() + "&7config.yml &ahas been reset!"));
    } else {
  	if (args[1].equalsIgnoreCase("all")) {
  		
  	    fu.resetAll();
  	    sender.sendMessage(ChatUtils.chat(rl.prefix() + "&aAll configs been reset!"));
  	}
    }
  	}
    	
    } else {
    	Bukkit.getLogger().info(ChatUtils.chat(rl.prefix() + rl.notplayer()));
    }
    return true;
  }
}
