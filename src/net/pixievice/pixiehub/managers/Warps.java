package net.pixievice.pixiehub.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import net.pixievice.pixiehub.files.WarpsConfig;

public class Warps {
	
	public void setWarp(Player player, String warp) {
		
		WarpsConfig.get().createSection("Warps." + warp);
		WarpsConfig.get().set("Warps." + warp + ".X", player.getLocation().getX());
		WarpsConfig.get().set("Warps." + warp + ".Y", player.getLocation().getY());
		WarpsConfig.get().set("Warps." + warp + ".Z", player.getLocation().getZ());
		WarpsConfig.get().set("Warps." + warp + ".world", player.getWorld().getName());
		WarpsConfig.get().set("Warps." + warp + ".Yaw", player.getLocation().getYaw());
		WarpsConfig.get().set("Warps." + warp + ".Pitch", player.getLocation().getPitch());
		WarpsConfig.save();
		
	}
	
	public void warpTeleport(Player player, String warp) {
		
		World world = Bukkit.getWorld(WarpsConfig.get().getString("Warps." + warp + ".world"));
		Double cordx = WarpsConfig.get().getDouble("Warps." + warp + ".X");
		Double cordy = WarpsConfig.get().getDouble("Warps." + warp + ".Y");
		Double cordz = WarpsConfig.get().getDouble("Warps." + warp + ".Z");
		Integer yaw = WarpsConfig.get().getInt("Warps." +  warp + ".Yaw");
		Integer pitch = WarpsConfig.get().getInt("Warps." +  warp + ".Pitch");
		Location loc = new Location(world, cordx, cordy, cordz);
		loc.setYaw(yaw);
		loc.setPitch(pitch);
		player.teleport(loc);
		
	}

}
