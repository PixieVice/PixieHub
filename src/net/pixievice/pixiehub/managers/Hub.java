package net.pixievice.pixiehub.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import net.pixievice.pixiehub.files.HubConfig;

public class Hub {
	
	public void hubTeleport(Player player) {
		
		World world = Bukkit.getWorld(HubConfig.get().getString("Hub.world"));
		Double cordx = HubConfig.get().getDouble("Hub.X");
		Double cordy = HubConfig.get().getDouble("Hub.Y");
		Double cordz = HubConfig.get().getDouble("Hub.Z");
		Integer yaw = HubConfig.get().getInt("Hub.Yaw");
		Integer pitch = HubConfig.get().getInt("Hub.Pitch");
		Location loc = new Location(world, cordx, cordy, cordz);
		loc.setYaw(yaw);
		loc.setPitch(pitch);
		player.teleport(loc);
	}

}
