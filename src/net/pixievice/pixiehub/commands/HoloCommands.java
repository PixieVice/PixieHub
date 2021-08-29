package net.pixievice.pixiehub.commands;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.pixievice.pixiehub.ChatUtils;
import net.pixievice.pixiehub.Main;
import net.pixievice.pixiehub.ReferanceLang;
import net.pixievice.pixiehub.files.Holos;
import net.pixievice.pixiehub.holograms.HoloManager;

public class HoloCommands implements CommandExecutor {
  ReferanceLang rl = new ReferanceLang();
  HoloManager hm = new HoloManager();
  Holos hc = new Holos();
  
  DecimalFormat df = new DecimalFormat("0.00");
  
  Main main;
  
  public HoloCommands(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    String boarder = new String(ChatUtils.chat("&d--------------------"));
    String number = Integer.toString(this.hc.getHighestFile(this.hc.listFiles(new File(Bukkit.getPluginManager().getPlugin("PixieHub").getDataFolder() + "/holos/"))).intValue() + 1);
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (player.hasPermission("pixie.holos.admin")) {
        if (args.length >= 2 && args[0].equalsIgnoreCase("create")) {
          if (args.length >= 1) {
            String holo = "";
            for (int i = 1; i < args.length; i++)
              holo = String.valueOf(holo) + args[i] + " "; 
            ArrayList<String> holoName = new ArrayList<>();
            holoName.add(holo);
            this.hm.createHolo(player.getLocation(), holoName, number);
            File holoFile = this.hc.createFile(String.valueOf(String.valueOf(number)) + ".yml");
            YamlConfiguration dataFile = YamlConfiguration.loadConfiguration(holoFile);
            dataFile.set("Location.World", player.getWorld().getName());
            dataFile.set("Location.X", Double.valueOf(player.getLocation().getX()));
            dataFile.set("Location.Y", Double.valueOf(player.getLocation().getY()));
            dataFile.set("Location.Z", Double.valueOf(player.getLocation().getZ()));
            List<String> lines = new ArrayList<>();
            lines.add(String.valueOf(holo) + "-");
            dataFile.set("Lines", lines);
            try {
              dataFile.save(holoFile);
            } catch (IOException iOException) {}
            this.hm.reloadStands();
            player.sendMessage(ChatUtils.chat(String.valueOf(rl.prefix()) + rl.createholo().replaceAll("%id%", number)));
          } else {
            player.sendMessage(ChatUtils.chat(String.valueOf(rl.prefix()) + rl.invalidholocreate()));
          } 
        } else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
          if (this.hm.removeStand(args[1])) {
            player.sendMessage(ChatUtils.chat(String.valueOf(rl.prefix()) + rl.removeholo().replaceAll("%id%", args[1])));
          } else {
            player.sendMessage(ChatUtils.chat(String.valueOf(rl.prefix()) + rl.invalidhloid()));
          } 
        } else if (args.length >= 2 && args[0].equalsIgnoreCase("addline")) {
          File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PixieHub").getDataFolder() + "/holos/" + args[1] + ".yml");
          if (!file.exists()) {
            player.sendMessage(ChatUtils.chat(String.valueOf(rl.prefix()) + rl.invalidhloid()));
            return false;
          } 
          if (args.length >= 2) {
            String id = args[1];
            String holo = "";
            for (int i = 2; i < args.length; i++)
              holo = String.valueOf(holo) + args[i] + " "; 
            this.hm.addLine(sender, holo, id);
          } 
        } else if (args.length == 3 && args[0].equalsIgnoreCase("removeline")) {
          File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PixieHub").getDataFolder() + "/holos/" + args[1] + ".yml");
          if (!file.exists()) {
            player.sendMessage(ChatUtils.chat(String.valueOf(rl.prefix()) + rl.invalidhloid()));
            return false;
          } 
          this.hm.removeLine(sender, args[1], args[2]);
        } else if (args.length == 1 && args[0].equalsIgnoreCase("near")) {
          List<ArmorStand> near = this.hm.findNear(player.getLocation());
          if (near.size() == 0) {
            player.sendMessage(ChatUtils.chat(String.valueOf(rl.prefix()) + rl.nonearholos()));
          } else {
            player.sendMessage(ChatUtils.chat(rl.prefix()));
          } 
          for (ArmorStand a : near) {
            TextComponent t = new TextComponent(String.valueOf(ChatUtils.chat("&dID: &7")) + a.getBoots().getItemMeta().getDisplayName() + ChatUtils.chat(".&d XYZ: &7"));
            TextComponent t2 = new TextComponent(ChatUtils.chat(String.valueOf(String.valueOf(this.df.format(a.getLocation().getX()))) + ", " + this.df.format(a.getLocation().getY()) + ", " + this.df.format(a.getLocation().getZ())));
            t2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(ChatUtils.chat(rl.hoverholochat()))).create()));
            t2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + t2.getText().replace(",", "")));
            player.spigot().sendMessage(new BaseComponent[] { (BaseComponent)t, (BaseComponent)t2 });
          } 
        } else if (args.length == 2 && args[0].equalsIgnoreCase("movehere")) {
          File file = new File(Bukkit.getServer().getPluginManager().getPlugin("PixieHub").getDataFolder() + "/holos/" + args[1] + ".yml");
          if (!file.exists()) {
            player.sendMessage(ChatUtils.chat(String.valueOf(rl.prefix()) + rl.invalidhloid()));
            return false;
          } 
          YamlConfiguration configFile = YamlConfiguration.loadConfiguration(file);
          configFile.set("Location.World", player.getWorld().getName());
          configFile.set("Location.X", Double.valueOf(player.getLocation().getX()));
          configFile.set("Location.Y", Double.valueOf(player.getLocation().getY()));
          configFile.set("Location.Z", Double.valueOf(player.getLocation().getZ()));
          try {
            configFile.save(file);
          } catch (IOException iOException) {}
          this.hm.reloadStands();
          player.sendMessage(ChatUtils.chat(String.valueOf(rl.prefix()) + rl.movedholo().replaceAll("%id%", args[1])));
        } else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
          player.sendMessage(ChatUtils.chat(boarder));
          player.sendMessage(ChatUtils.chat("&e/PHolo create &7<text>"));
          player.sendMessage(ChatUtils.chat("&e/PHolo remove &7<id>"));
          player.sendMessage(ChatUtils.chat("&e/PHolo addline &7<id> <text>"));
          player.sendMessage(ChatUtils.chat("&e/PHolo removeline &7<id> <line-id>"));
          player.sendMessage(ChatUtils.chat("&e/PHolo movehere &7<id>"));
          player.sendMessage(ChatUtils.chat("&e/PHolo near"));
          player.sendMessage(ChatUtils.chat(boarder));
        } else {
          player.sendMessage(ChatUtils.chat(String.valueOf(rl.prefix()) + rl.unknownholo()));
        } 
      } else {
        player.sendMessage(ChatUtils.chat(String.valueOf(rl.prefix()) + rl.noperm()));
      } 
    } else {
      Bukkit.getLogger().info(ChatUtils.chat(String.valueOf(rl.prefix()) + rl.notplayer()));
    } 
    return true;
  }
}