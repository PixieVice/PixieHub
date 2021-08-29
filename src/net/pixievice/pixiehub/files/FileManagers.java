package net.pixievice.pixiehub.files;

import java.util.ArrayList;
import java.util.List;

public class FileManagers {
	
	public void generatePlayers() {
		PlayersConfig.setup();
		if (PlayersConfig.get().getConfigurationSection("Players") == null) {
		PlayersConfig.get().createSection("Players");
		PlayersConfig.save();
		}
	}
	
	public void generateHub() {
		HubConfig.setup();
		if (HubConfig.get().getString("Hub.X") == null) { HubConfig.get().set("Hub.X", ""); }
		if (HubConfig.get().getString("Hub.Y") == null) { HubConfig.get().set("Hub.Y", ""); }
		if (HubConfig.get().getString("Hub.Z") == null) { HubConfig.get().set("Hub.Z", ""); }
		if (HubConfig.get().getString("Hub.world") == null) { HubConfig.get().set("Hub.world", ""); }
		if (HubConfig.get().getString("Hub.Yaw") == null) { HubConfig.get().set("Hub.Yaw", ""); }
		if (HubConfig.get().getString("Hub.Pitch") == null) { HubConfig.get().set("Hub.Pitch", ""); }
		HubConfig.save();
	}
	
	public void generateWarps() {
		WarpsConfig.setup();
		if (WarpsConfig.get().getConfigurationSection("Warps") == null) { WarpsConfig.get().createSection("Warps"); }
		WarpsConfig.save();
	}
	
	public void generatePads() {
		Pads.setup();
		if (Pads.get().getConfigurationSection("JumpPads") == null) { Pads.get().createSection("JumpPads"); }
		Pads.save();
	}
	
	public void generateFilters() {
		FilterConfig.setup();
		if (FilterConfig.get().getConfigurationSection("FilteredWords") == null) {
			List<String> filterlist = new ArrayList<>();
			filterlist.add("example"); filterlist.add("example0"); filterlist.add("example1");
			FilterConfig.get().set("FilteredWords", filterlist);
			FilterConfig.save();
		}
	}
	
	public void generateLang() {
		Lang.setup();
		
		// General //
		Lang.get().addDefault("Prefix", "&8[&dPixieHub&8] » ");
		Lang.get().addDefault("NoPermission", "&cYou do not have permission to execute this command!");
		Lang.get().addDefault("JoinMessage", "&e%player% has joined the game!");
		Lang.get().addDefault("BlackListedWord", "&cOne or more words in your message is not allowed!");
		Lang.get().addDefault("SilentJoinEnable", "&aEnabled Silent Join. &7Players will not be notified when you join.");
		Lang.get().addDefault("SilentJoinDisable", "&cDisabled Silent Join. &7Players will now be notified when you join.");
		Lang.get().addDefault("ChatCooldownMessage", "&cPlease wait %cooldown% seconds before chatting again!");
		Lang.get().addDefault("NotPlayer", "&cOnly players can execute this command!");
		
		// Unknown Returns //
		Lang.get().addDefault("Unknown.main", "&cUnknown arguments. Use &7/PHub Help &cfor a list of commands.");
		Lang.get().addDefault("Unknown.warp", "&cUnknown arguments. Use &7/PWarp Help &cfor a list of commands.");
		Lang.get().addDefault("Unknown.holograms", "&cUnknown arguments. Use &7/PHolo Help &cfor a list of commands.");
		Lang.get().addDefault("Unknown.jump-pads", "&cUnknown arguments. Use &7/PJP Help &cfor a list of commands.");
		
		// Hub //
		Lang.get().addDefault("Hub.update-hub", "&aHub has been updated to your location.");
		Lang.get().addDefault("Hub.message", "&7You have been teleported to the hub!");
		Lang.get().addDefault("Hub.no-location", "&cThe hub location has not been set.");
		
		// Warp //
		Lang.get().addDefault("Warp.already-exists", "&cThis warp already exists!");
		Lang.get().addDefault("Warp.none-found", "&cThis warp does not exist!");
		Lang.get().addDefault("Warp.warpped", "&7You warped to &b%warp%&7!");
		Lang.get().addDefault("Warp.no-permission", "&cYou do not have access to this warp.");
		
		// Teleport //
		Lang.get().addDefault("Teleport.teleported", "&aYou have teleported to &7%target%&a!");
		Lang.get().addDefault("Teleport.player-not-online", "&cThis player is not online!");
		Lang.get().addDefault("Teleport.self-teleport", "&cYou can not teleport to yourself.");
		Lang.get().addDefault("Teleport.usage", "&cUSAGE: /Pteleport <player>");
		
		//Holograms//
		Lang.get().addDefault("Hologram.create", "&aYou have created a new hologram with id &7%id%&a!");
		Lang.get().addDefault("Hologram.remove", "&cYou have removed a hologram with id &7%id%&c!");
		Lang.get().addDefault("Hologram.invalid-id", "&cInvalid hologram ID.");
		Lang.get().addDefault("Hologram.invalid-line", "&cInvalid line ID.");
		Lang.get().addDefault("Hologram.invalid-create", "&cUSAGE: &7/Pholo create <text>&c!");
		Lang.get().addDefault("Hologram.addline", "&aLine added!");
		Lang.get().addDefault("Hologram.removeline", "&cLine removed!");
		Lang.get().addDefault("Hologram.moved", "&aHologram with id &7%id%&a has been moved to your location!");
		Lang.get().addDefault("Hologram.chat-hover", "&dTeleport to hologram?");
		Lang.get().addDefault("Hologram.near", "&aNearby holograms:");
		Lang.get().addDefault("Hologram.none-near", "&cNo holograms found nearby.");
		
		Lang.get().addDefault("JumpPads.create", "&aYou have created a new jump pad named &7%name%&a!");
		Lang.get().addDefault("JumpPads.remove", "&cYou have renoved a jump pad named &7%name%&c!");
		Lang.get().addDefault("JumpPads.already-exists", "&cThis jump pad already exists!");
		Lang.get().addDefault("JumpPads.none-exists", "&cThis jump pad does not exits!");
		Lang.get().addDefault("JumpPads.update-sound", "&aUpdated the sound to &7%sound%&a!");
		Lang.get().addDefault("JumpPads.update-particle", "&aUpdated the particle to &7%particle%&a!");
		
		Lang.get().options().copyDefaults(true);
		Lang.save();
	}

}
