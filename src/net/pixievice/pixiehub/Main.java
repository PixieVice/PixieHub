package net.pixievice.pixiehub;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import net.pixievice.pixiehub.commands.HelpCommand;
import net.pixievice.pixiehub.commands.HoloCommands;
import net.pixievice.pixiehub.commands.HubCommand;
import net.pixievice.pixiehub.commands.JumpPadCommands;
import net.pixievice.pixiehub.commands.MainCommands;
import net.pixievice.pixiehub.commands.TeleportCommand;
import net.pixievice.pixiehub.commands.WarpCommands;
import net.pixievice.pixiehub.files.FileManagers;
import net.pixievice.pixiehub.files.Holos;
import net.pixievice.pixiehub.holograms.HoloManager;

public class Main extends JavaPlugin {
	
	private PluginDescriptionFile pluginInfo = getDescription();
	private String pver = pluginInfo.getVersion();
	private static Main instance;
	
	Holos hc = new Holos();
	HoloManager hm = new HoloManager();
	FileManagers fm = new FileManagers();
	
	
	@Override
	public void onEnable() {
		
		Boolean usehelp = getConfig().getBoolean("CommandOptions.custom-help-command.use");
		Boolean usehub = getConfig().getBoolean("CommandOptions.hub");
		Boolean usespawn = getConfig().getBoolean("CommandOptions.spawn");
		Boolean usestuck = getConfig().getBoolean("CommandOptions.stuck");
		Boolean uselobby = getConfig().getBoolean("CommandOptions.lobby");
		Boolean usel = getConfig().getBoolean("CommandOptions.l");
		
		instance = this;
		fm.generateLang();
		fm.generateHub();
		fm.generateFilters();
		fm.generatePlayers();
		fm.generateWarps();
		fm.generatePads();
		
		Bukkit.getLogger().info(ChatUtils.chat("&dPixieHub Loaded!"));
		Bukkit.getLogger().info(ChatUtils.chat("&7Discord: https://discord.gg/hAhZ4GqDmE"));
		
		this.getConfig().options().copyDefaults();
		saveDefaultConfig();
		
		
		Bukkit.getPluginManager().registerEvents(new Events(this), this);
		getCommand("phub").setExecutor(new MainCommands(this));
		getCommand("pwarp").setExecutor(new WarpCommands(this));
		getCommand("pteleport").setExecutor(new TeleportCommand(this));
		getCommand("phologram").setExecutor(new HoloCommands(this));
		getCommand("pjumppad").setExecutor(new JumpPadCommands(this));
		if (usehub == true) {
		getCommand("hub").setExecutor(new HubCommand(this));
		}
		if (usestuck == true) {
		getCommand("stuck").setExecutor(new HubCommand(this));
		}
		if (usespawn == true) {
		getCommand("spawn").setExecutor(new HubCommand(this));
		}
		if (uselobby == true) {
		getCommand("lobby").setExecutor(new HubCommand(this));
		}
		if (usel == true) {
		getCommand("l").setExecutor(new HubCommand(this));
		}
		if (usehelp == true) {
		getCommand("help").setExecutor(new HelpCommand(this));
		}
		
		hc.generateDefaultFolders(getDataFolder());
		hm.reloadStands();
		
		String ver = getConfig().getString("config-version");
		if (!ver.equals(pver)) {
			Bukkit.getLogger().info(ChatUtils.chat("&cConfig outdated! Please update it!"));
		} else if (ver.equals(pver)) {
			Bukkit.getLogger().info(ChatUtils.chat("&aConfig is up to date!"));
		}
		
	}
	
	public static Main getInstance() {
	    return instance;
	}
	
	@Override
	public void onDisable() {
		getLogger().info(ChatUtils.chat("&cPixieHub Disabled!"));
	}
	
}
