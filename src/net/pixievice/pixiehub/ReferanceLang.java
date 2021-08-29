package net.pixievice.pixiehub;

import net.pixievice.pixiehub.files.Lang;

public class ReferanceLang {
	
	//General//
	public String prefix() {
		return Lang.get().getString("Prefix");
	}
	public String noperm() {
		return Lang.get().getString("NoPermission");
	}
	public String sje() { 
		return Lang.get().getString("SilentJoinEnable");
	}
	public String sjd() { 
		return Lang.get().getString("SilentJoinDisable");
	}
	public String notplayer() { 
		return Lang.get().getString("NotPlayer");
	}
	
	//MSG//
	public String cooldownmsg() { 
		return Lang.get().getString("ChatCooldownMessage");
	}
	public String joinmsg() { 
		return Lang.get().getString("JoinMessage");
	}
	
	//Unknown//
	public String unknown() { 
		return Lang.get().getString("Unknown.main");
	}
	public String unknownwarp() { 
		return Lang.get().getString("Unknown.warp");
	}
	public String unknownholo() { 
		return Lang.get().getString("Unknown.holograms");
	}
	public String unknownjp() { 
		return Lang.get().getString("Unknown.jump-pads");
	}
	
	//Hub//
	public String updatehub() { 
		return Lang.get().getString("Hub.update-hub");
	}
	public String onhub() { 
		return Lang.get().getString("Hub.message");
	}
	public String nohubloc() { 
		return Lang.get().getString("Hub.no-location");
	}
	
	//Warps//
	public String warpalreadyexists() { 
		return Lang.get().getString("Warp.already-exists");
	}
	public String warpnotfound() { 
		return Lang.get().getString("Warp.none-found");
	}
	public String onwarp() { 
		return Lang.get().getString("Warp.warpped");
	}
	public String nowarpperm() { 
		return Lang.get().getString("Warp.no-permission");
	}
	
	//Teleport//
	public String onteleport() { 
		return Lang.get().getString("Teleport.teleported");
	}
	public String teleportnpo() { 
		return Lang.get().getString("Teleport.player-not-online");
	}
	public String selfteleport() { 
		return Lang.get().getString("Teleport.self-teleport");
	}
	public String teleportusage() { 
		return Lang.get().getString("Teleport.usage");
	}
	
	//Holograms//
	public String createholo() { 
		return Lang.get().getString("Hologram.create");
	}
	public String removeholo() { 
		return Lang.get().getString("Hologram.remove");
	}
	public String invalidhloid() { 
		return Lang.get().getString("Hologram.invalid-id");
	}
	public String invalidhololine() { 
		return Lang.get().getString("Hologram.invalid-line");
	}
	public String invalidholocreate() { 
		return Lang.get().getString("Hologram.invalid-create");
	}
	public String addhololine() { 
		return Lang.get().getString("Hologram.addline");
	}
	public String removehololine() { 
		return Lang.get().getString("Hologram.removeline");
	}
	public String movedholo() { 
		return Lang.get().getString("Hologram.moved");
	}
	public String hoverholochat() { 
		return Lang.get().getString("Hologram.chat-hover");
	}
	public String nearholos() { 
		return Lang.get().getString("Hologram.near");
	}
	public String nonearholos() { 
		return Lang.get().getString("Hologram.none-near");
	}
	
	//Jump Pads//
	public String createpad() { 
		return Lang.get().getString("JumpPads.create");
	}
	public String removepad() { 
		return Lang.get().getString("JumpPads.remove");
	}
	public String padalreadyexists() { 
		return Lang.get().getString("JumpPads.already-exists");
	}
	public String padnotfound() { 
		return Lang.get().getString("JumpPads.none-exists");
	}
	public String updatepadsounds() {
		return Lang.get().getString("JumpPads.update-sound");
	}
	public String updatepadparticle() { 
		return Lang.get().getString("JumpPads.update-particle");
	}

}
