package net.pixievice.pixiehub;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import net.pixievice.pixiehub.files.Lang;

public class ReferanceLang {
	
	//General//
	public String prefix() {
		return Lang.get().getString("Prefix");
	}
	public String noperm(Player player) {
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("NoPermission"));
	}
	public String sje(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("SilentJoinEnable"));
	}
	public String sjd(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("SilentJoinDisable"));
	}
	public String notplayer() { 
		return Lang.get().getString("NotPlayer");
	}
	
	//MSG//
	public String cooldownmsg(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("ChatCooldownMessage"));
	}
	public String joinmsg(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("JoinMessage"));
	}
	
	//Unknown//
	public String unknown(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Unknown.main"));
	}
	public String unknownwarp(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Unknown.warp"));
	}
	public String unknownholo(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Unknown.holograms"));
	}
	public String unknownjp(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Unknown.jump-pads"));
	}
	
	//Hub//
	public String updatehub(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Hub.update-hub"));
	}
	public String onhub(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Hub.message"));
	}
	public String nohubloc(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Hub.no-location"));
	}
	
	//Warps//
	public String warpalreadyexists(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Warp.already-exists"));
	}
	public String warpnotfound(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Warp.none-found"));
	}
	public String onwarp(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Warp.warpped"));
	}
	public String nowarpperm(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Warp.no-permission"));
	}
	
	//Teleport//
	public String onteleport(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Teleport.teleported"));
	}
	public String teleportnpo(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Teleport.player-not-online"));
	}
	public String selfteleport(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Teleport.self-teleport"));
	}
	public String teleportusage(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Teleport.usage"));
	}
	
	//Holograms//
	public String createholo(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Hologram.create"));
	}
	public String removeholo(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Hologram.remove"));
	}
	public String invalidhloid(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Hologram.invalid-id"));
	}
	public String invalidhololine(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Hologram.invalid-line"));
	}
	public String invalidholocreate(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Hologram.invalid-create"));
	}
	public String addhololine(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Hologram.addline"));
	}
	public String removehololine(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Hologram.removeline"));
	}
	public String movedholo(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Hologram.moved"));
	}
	public String hoverholochat(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Hologram.chat-hover"));
	}
	public String nearholos(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Hologram.near"));
	}
	public String nonearholos(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("Hologram.none-near"));
	}
	
	//Jump Pads//
	public String createpad(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("JumpPads.create"));
	}
	public String removepad(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("JumpPads.remove"));
	}
	public String padalreadyexists(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("JumpPads.already-exists"));
	}
	public String padnotfound(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("JumpPads.none-exists"));
	}
	public String updatepadsounds(Player player) {
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("JumpPads.update-sound"));
	}
	public String updatepadparticle(Player player) { 
		return PlaceholderAPI.setPlaceholders(player, Lang.get().getString("JumpPads.update-particle"));
	}
	
	//Maintenance//
	public String enableMaintenance() {
		return Lang.get().getString("Maintenance.enable");
	}
	public String disableMaintenance() {
		return Lang.get().getString("Maintenance.disable");
	}

}
