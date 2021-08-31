package net.pixievice.pixiehub.holograms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.pixievice.pixiehub.ChatUtils;
import net.pixievice.pixiehub.Main;
import net.pixievice.pixiehub.files.Holos;
import net.pixievice.pixiehub.files.Lang;

public class HoloManager {
  Holos hc = new Holos();
  
  public void createHolo(Location loc, List<String> name, String number) {
    double Y = 0.0D;
    boolean firstline = true;
    for (String line : name) {
      ItemStack boots = new ItemStack(Material.COAL, 1);
      if (firstline) {
        boots.setType(Material.STICK);
        firstline = false;
      } 
      ItemMeta bootsMeta = boots.getItemMeta();
      bootsMeta.setDisplayName(number);
      boots.setItemMeta(bootsMeta);
      boots.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
      ArmorStand stand = (ArmorStand)loc.getWorld().spawn(new Location(loc.getWorld(), loc.getX(), loc.getY() - Y, loc.getZ()), ArmorStand.class);
      stand.setGravity(false);
      stand.setVisible(false);
      stand.setCustomNameVisible(true);
      stand.setCustomName(ChatUtils.chat(line.replaceAll(" -", "")));
      stand.setBoots(boots);
      Y += Main.getInstance().getConfig().getDouble("Holograms.spacing");
    } 
  }
  
  public boolean removeStand(String number) {
    File file = new File(Bukkit.getPluginManager().getPlugin("PixieHub").getDataFolder() + "/holos/" + number + ".yml");
    if (file.exists()) {
      file.delete();
      reloadStands();
      return true;
    } 
    return false;
  }
  
  public void addLine(Player player, String newLine, String id) {
    player.sendMessage(ChatUtils.chat(Lang.get().getString("Prefix") + Lang.get().getString("Hologram.addline")));
    File standFile = new File(Bukkit.getServer().getPluginManager().getPlugin("PixieHub").getDataFolder() + "/holos/" + id + ".yml");
    YamlConfiguration configFile = YamlConfiguration.loadConfiguration(standFile);
    List<String> lines = configFile.getStringList("Lines");
    lines.add(String.valueOf(newLine) + "-");
    configFile.set("Lines", lines);
    try {
      configFile.save(standFile);
    } catch (IOException iOException) {}
    reloadStands();
  }
  
  public void removeLine(Player player, String id, String lineid) {
    File standFile = new File(Bukkit.getServer().getPluginManager().getPlugin("PixieHub").getDataFolder() + "/holos/" + id + ".yml");
    YamlConfiguration configFile = YamlConfiguration.loadConfiguration(standFile);
    List<String> lines = configFile.getStringList("Lines");
    int index = Integer.valueOf(lineid).intValue();
    try {
      lines.remove(index);
    } catch (IndexOutOfBoundsException e) {
      player.sendMessage(ChatUtils.chat(Lang.get().getString("Prefix") + Lang.get().getString("Hologram.invalid-line")));
      return;
    } 
    configFile.set("Lines", lines);
    try {
      configFile.save(standFile);
    } catch (IOException iOException) {}
    reloadStands();
    player.sendMessage(ChatUtils.chat(Lang.get().getString("Prefix") + Lang.get().getString("Hologram.removeline")));
  }
  
  public void generateStands() {
    for (File f : this.hc.listFiles(new File(Bukkit.getPluginManager().getPlugin("PixieHub").getDataFolder() + "/holos/"))) {
      YamlConfiguration cf = YamlConfiguration.loadConfiguration(f);
      Location loc = new Location(Bukkit.getServer().getWorld(cf.getString("Location.World")), cf.getDouble("Location.X"), cf.getDouble("Location.Y"), cf.getDouble("Location.Z"));
      createHolo(loc, cf.getStringList("Lines"), f.getName().replace(".yml", ""));
    } 
  }
  
  public void removeStands() {
    for (World w : Bukkit.getServer().getWorlds()) {
      for (Entity e : w.getEntities()) {
        if (e.getType().equals(EntityType.ARMOR_STAND)) {
          ArmorStand a = (ArmorStand)e;
          ItemStack boots = new ItemStack(Material.STICK);
          ItemStack boots2 = new ItemStack(Material.COAL);
          boots.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
          boots2.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
          if (a.getBoots().getType().equals(boots.getType()) && a.getBoots().getEnchantments().equals(boots.getEnchantments()))
            e.remove();
          if (a.getBoots().getType().equals(boots2.getType()) && a.getBoots().getEnchantments().equals(boots2.getEnchantments()))
            e.remove(); 
        } 
      } 
    } 
  }
  
  public void reloadStands() {
    removeStands();
    generateStands();
  }
  
  public List<ArmorStand> findNear(Location loc) {
    List<ArmorStand> near = new ArrayList<>();
    for (Entity e : loc.getWorld().getNearbyEntities(loc, 10.0D, 10.0D, 10.0D)) {
      if (e.getType().equals(EntityType.ARMOR_STAND)) {
        ArmorStand a = (ArmorStand)e;
        ItemStack boots = new ItemStack(Material.STICK);
        boots.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        if (a.getBoots().getType().equals(boots.getType()) && a.getBoots().getEnchantments().equals(boots.getEnchantments()))
          near.add(a); 
      } 
    } 
    return near;
  }
}