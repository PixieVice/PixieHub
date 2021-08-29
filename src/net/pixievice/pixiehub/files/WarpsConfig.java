package net.pixievice.pixiehub.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.pixievice.pixiehub.ChatUtils;

public class WarpsConfig {

	private static File file;
	private static FileConfiguration configFile;
	
	public static void setup() {
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("PixieHub").getDataFolder(), "warps.yml"); 
		
		if (!file.exists()) {
			try {
			file.createNewFile();
			} catch (IOException e) {
				System.out.println(ChatUtils.chat("&cCould not load warps file."));
			}
		}
		
		configFile = YamlConfiguration.loadConfiguration(file);
	}
	
	public static FileConfiguration get() {
		return configFile;
	}
	
	public static void save() {
		try {
			configFile.save(file);
		} catch (IOException e) {
			System.out.println(ChatUtils.chat("&cCould not save warps file."));
		}
		
	}
	
	public static void reload() {
		configFile = YamlConfiguration.loadConfiguration(file);
	}
}
