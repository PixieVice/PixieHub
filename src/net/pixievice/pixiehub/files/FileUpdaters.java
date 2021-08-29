package net.pixievice.pixiehub.files;

import java.io.File;

import org.bukkit.Bukkit;

import net.pixievice.pixiehub.Main;

public class FileUpdaters {
	
	FileManagers fm = new FileManagers();
	private Main main = Main.getInstance();
	
	public void resetLang() {
		
		File configFile = new File(Bukkit.getServer().getPluginManager().getPlugin("PixieHub").getDataFolder(), "lang.yml"); 
		configFile.delete();
		fm.generateLang();
	}
	
	public void resetConfig() {
		
		File configFile = new File(main.getDataFolder(), "config.yml");
		configFile.delete();
		main.saveDefaultConfig();
		main.reloadConfig();
	}
	
	public void resetAll() {
		resetLang();
		resetConfig();
	}
}