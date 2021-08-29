package net.pixievice.pixiehub.managers;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import net.pixievice.pixiehub.files.HubConfig;
import net.pixievice.pixiehub.files.WarpsConfig;

public class MC {
	
	public void setHub(Player player) {
		
        HubConfig.get().set("Hub.X", Double.valueOf(player.getLocation().getX()));
        HubConfig.get().set("Hub.Y", Double.valueOf(player.getLocation().getY()));
        HubConfig.get().set("Hub.Z", Double.valueOf(player.getLocation().getZ()));
        HubConfig.get().set("Hub.world", player.getWorld().getName());
        HubConfig.get().set("Hub.Yaw", Float.valueOf(player.getLocation().getYaw()));
        HubConfig.get().set("Hub.Pitch", Float.valueOf(player.getLocation().getPitch()));
        HubConfig.save();
		
	}
	
	public void migrateHub(String warp) {
		
        World world = Bukkit.getWorld(WarpsConfig.get().getString("Warps." + warp + ".world"));
        Double cordx = Double.valueOf(WarpsConfig.get().getDouble("Warps." + warp + ".X"));
        Double cordy = Double.valueOf(WarpsConfig.get().getDouble("Warps." + warp + ".Y"));
        Double cordz = Double.valueOf(WarpsConfig.get().getDouble("Warps." + warp + ".Z"));
        Integer yaw = Integer.valueOf(WarpsConfig.get().getInt("Warps." + warp + ".Yaw"));
        Integer pitch = Integer.valueOf(WarpsConfig.get().getInt("Warps." + warp + ".Pitch"));
        HubConfig.get().set("Hub.X", cordx);
        HubConfig.get().set("Hub.Y", cordy);
        HubConfig.get().set("Hub.Z", cordz);
        HubConfig.get().set("Hub.world", world.getName());
        HubConfig.get().set("Hub.Yaw", yaw);
        HubConfig.get().set("Hub.Pitch", pitch);
        HubConfig.save();
		
	}

}
