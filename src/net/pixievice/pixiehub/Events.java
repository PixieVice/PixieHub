package net.pixievice.pixiehub;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import net.pixievice.pixiehub.arrays.Arrays;
import net.pixievice.pixiehub.files.FilterConfig;
import net.pixievice.pixiehub.files.HubConfig;
import net.pixievice.pixiehub.files.Lang;
import net.pixievice.pixiehub.files.PlayersConfig;
import net.pixievice.pixiehub.jumppads.PadBreakManager;
import net.pixievice.pixiehub.jumppads.PadJumpManager;

public class Events implements Listener {
	
	PadBreakManager pbm = new PadBreakManager();
	PadJumpManager pjm = new PadJumpManager();
	
	private Main main;
	
	public Events(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player player = e.getPlayer();
		String checkcreatedplayer = PlayersConfig.get().getString("Players." + player.getUniqueId());
		
		if (checkcreatedplayer == null) {
			PlayersConfig.get().set("Players." + player.getUniqueId() + ".silentjoin", false);
			PlayersConfig.save();
		}
		
		World world = Bukkit.getWorld(HubConfig.get().getString("Hub.world"));
		Double cordx = HubConfig.get().getDouble("Hub.X");
		Double cordy = HubConfig.get().getDouble("Hub.Y");
		Double cordz = HubConfig.get().getDouble("Hub.Z");
		Integer yaw = HubConfig.get().getInt("Hub.Yaw");
		Integer pitch = HubConfig.get().getInt("Hub.Pitch");
		
		Location loc = new Location(world, cordx, cordy, cordz);
		loc.setYaw(yaw);
		loc.setPitch(pitch);
		
		Boolean cjm = main.getConfig().getBoolean("UseCustomJoinMessage");
		Boolean toj = main.getConfig().getBoolean("TeleportOnJoin");
		Boolean jf = main.getConfig().getBoolean("UseJoinFly");
		Boolean sj = main.getConfig().getBoolean("UseSilentJoin");
		Boolean udj = main.getConfig().getBoolean("UseDoubleJump");
		
		Boolean checkallowedflight = new Boolean(player.getAllowFlight());
		Boolean checkisflying = new Boolean(player.isFlying());
		Boolean sjenabled = PlayersConfig.get().getBoolean("Players." + player.getUniqueId() + ".silentjoin");
		
		String prefix = Lang.get().getString("Prefix");
		String message = Lang.get().getString("JoinMessage");

		if (udj == true) {
			if (player.hasPermission("pixie.doublejump")) {
				
				player.setAllowFlight(true); 
				
			}
		}
		
		if (sj == true) {
			if (sjenabled == true) {
				if (player.hasPermission("pixie.silentjoin")) {
					e.setJoinMessage(""); } else { if (cjm == true) { message = message.replaceAll("%player%", player.getDisplayName()); e.setJoinMessage(ChatUtils.chat(message)); } }
			} else { if (cjm == true) { message = message.replaceAll("%player%", player.getDisplayName()); e.setJoinMessage(ChatUtils.chat(message)); } }
		} else { if (cjm == true) { message = message.replaceAll("%player%", player.getDisplayName()); e.setJoinMessage(ChatUtils.chat(message)); } }
		
		if (toj == true) { 
			if (HubConfig.get().getString("Hub.X").equals("") && HubConfig.get().getString("Hub.Y").equals("") && HubConfig.get().getString("Hub.Z").equals("")) {
				main.getLogger().info(ChatUtils.chat(prefix + "&cPlayer " + player.getDisplayName() + " to the hub because it has not been set yet!"));
				player.sendMessage(ChatUtils.chat(prefix + "&cCould not teleport you to the hub because it has not been set yet!"));
			} else {
				player.teleport(loc);
			}
		}
		
		if (jf == true) { 
			if (player.hasPermission("pixie.joinfly")) { 
				player.setAllowFlight(true); 
				player.setFlying(true); 
				} 
			} else { 
				if (jf == false) { 
					if (checkisflying == true) { 
						if (checkallowedflight == true) { 
							player.setAllowFlight(false); player.setAllowFlight(false); 
							} 
						} 
					}
				}
		
			}
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		
		Boolean usecooldown = main.getConfig().getBoolean("UseChatCooldown");
		Boolean usefilter = main.getConfig().getBoolean("UseChatFilter");
		
		if (usecooldown == true) {
		String timerstring = main.getConfig().getString("CooldownTimer");
		
		String prefix = Lang.get().getString("Prefix");
		String filteredword = Lang.get().getString("BlackListedWord");
		String cooldownmessage = Lang.get().getString("ChatCooldownMessage");
		cooldownmessage = cooldownmessage.replaceAll("%cooldown%", timerstring);
		
		int ticks = main.getConfig().getInt("CooldownTimer");
		int seconds = ticks * 20;
		
		
		Player player = e.getPlayer();
		
		if (!(player.hasPermission("pixie.chat.bypass"))) {
			
			if (Arrays.cooldown.contains(player)) { 
				player.sendMessage(ChatUtils.chat(prefix + cooldownmessage)); e.setCancelled(true);
				
			} else {
				if (usefilter == true) {
					if (!(player.hasPermission("pixie.bypassfilter"))) {
						for(String list : FilterConfig.get().getStringList("FilteredWords")){
							for(String blockedword : e.getMessage().split(" ")) {
								if (blockedword.toLowerCase().equalsIgnoreCase(list.toLowerCase())) {
									
									e.setCancelled(true);
									player.sendMessage(ChatUtils.chat(prefix + filteredword));
								}
							}
						}
					}
					
				}
				Arrays.cooldown.add(player); Bukkit.getScheduler ().runTaskLater (main, () -> Arrays.cooldown.remove(player), seconds);
				}
			}
		
	
		}
	}

    @EventHandler
    public void onPlayerDoubleJump(PlayerToggleFlightEvent e) {
    	
    	Boolean udj = main.getConfig().getBoolean("UseDoubleJump");
        Player player = e.getPlayer();
        
        if (udj == true) {
        	if (player.hasPermission("pixie.doublejump")) {
        		if (e.isFlying()) {
        			if (player.getLocation().subtract(0, 2, 0).getBlock().getType() != Material.AIR) {
        				player.setVelocity(player.getLocation().getDirection().multiply(1).setY(1));
        				
        				e.setCancelled(true);
        			} else {
        				if (player.getGameMode() != GameMode.CREATIVE) {
        					if (!player.hasPermission("pixie.joinfly")) {
        						
        						e.setCancelled(true);
        						
        					}
        				}
        			}
        				
        		}
        	}
        }
    }
    
    //Jump Pads//
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
    	Player player = e.getPlayer();
    	Material jumppad = Material.matchMaterial(main.getConfig().getString("JumpPads.block"));
    	
    	if (jumppad == e.getBlock().getType()) {
    		pbm.BreakManager(player, e);
     }
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
    	
    	Player player = e.getPlayer();
    	Material jumppad = Material.matchMaterial(main.getConfig().getString("JumpPads.block"));
    	Boolean usesound = main.getConfig().getBoolean("JumpPads.jump-sound");
    	Boolean useparticle = main.getConfig().getBoolean("JumpPads.jump-particle");
    	
        if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == jumppad) {
        	pjm.JumpManager(player, e, usesound, useparticle);
        	
        	
        } else if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
        	if (Arrays.jumpers.contains(player.getUniqueId())) {
        		Bukkit.getScheduler ().runTaskLater (main, () -> Arrays.jumpers.remove(player.getUniqueId()), 10);
        	}
        }
    }

    // Disabled features//
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
		Boolean disablefall = main.getConfig().getBoolean("JumpPad.disable-fall-damage");
			
		if(e.getEntity().getType() == EntityType.PLAYER) {
		Player player = (Player) e.getEntity();
		if (ch == true) {
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

}
