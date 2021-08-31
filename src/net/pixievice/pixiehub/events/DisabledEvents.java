package net.pixievice.pixiehub.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.pixievice.pixiehub.Main;
import net.pixievice.pixiehub.arrays.Arrays;
import net.pixievice.pixiehub.managers.Hub;

public class DisabledEvents implements Listener {
	
	Hub hub = new Hub();
	private Main main;
	
	public DisabledEvents(Main main) {
		this.main = main;
	}
	
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
    	Player player = e.getPlayer();
    	
    	Boolean udc = main.getConfig().getBoolean("DisableFunctions.item-drop");
    	if (udc == true) {
    	if (!(player.hasPermission("pixie.override.id"))) {
    		e.setCancelled(true);
    		}
    	}
    	
    }
    
	@EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
		Player player = e.getPlayer();
		
    	Boolean upuc = main.getConfig().getBoolean("DisableFunctions.item-drop");
    	if (!(player.hasPermission("pixie.override.ipu"))) {
    	if (upuc == true) {
    		e.setCancelled(true);
    		}
    	}
    }
	
	@EventHandler
	public void onHungerChange(FoodLevelChangeEvent e) {
		Boolean che = main.getConfig().getBoolean("DisableFunctions.player-hunger");
		
		if (che == true) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onHealthChange(EntityDamageEvent e) {
		
		Boolean ch = main.getConfig().getBoolean("DisableFunctions.player-health");
		Boolean vd = main.getConfig().getBoolean("TeleportOnVoid");
		Boolean disablefall = main.getConfig().getBoolean("JumpPad.disable-fall-damage");
			
		if(e.getEntity().getType() == EntityType.PLAYER) {
			Player player = (Player) e.getEntity();
		if (e.getCause() == DamageCause.VOID) {
		if (vd == true) {
			player.setFallDistance(0);
			e.setCancelled(true);
			hub.hubTeleport(player);
		}
		} else if (ch == true) {
			e.setCancelled(true);
			}
			if (disablefall == true) {
            if (e.getCause() == DamageCause.FALL && Arrays.jumpers.contains(player.getUniqueId())) {
                e.setCancelled(true);
                Arrays.jumpers.remove(player.getUniqueId());
            	}
            }
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		Boolean  disablequit = main.getConfig().getBoolean("DisableFunctuins.quit-message");
		
		if (disablequit == true) {
			e.setQuitMessage("");
		}
		
	}

}
