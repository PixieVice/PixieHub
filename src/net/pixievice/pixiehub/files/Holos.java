package net.pixievice.pixiehub.files;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;

public class Holos {
  public File createFile(String name) {
    File file = new File(Bukkit.getPluginManager().getPlugin("PixieHub").getDataFolder() + "/holos/" + name);
    try {
      if (!file.exists())
        file.createNewFile(); 
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return file;
  }
  
  public ArrayList<File> listFiles(File dir) {
    ArrayList<File> files = new ArrayList<>();
    byte b;
    int i;
    File[] arrayOfFile;
    for (i = (arrayOfFile = dir.listFiles()).length, b = 0; b < i; ) {
      File file = arrayOfFile[b];
      files.add(file);
      b++;
    } 
    return files;
  }
  
  public Integer getHighestFile(ArrayList<File> files) {
    File highestFile = new File("0.yml");
    try {
      for (File file : files) {
        if (Integer.valueOf(file.getName().replace(".yml", "")).intValue() > Integer.valueOf(highestFile.getName().replace(".yml", "")).intValue())
          highestFile = file; 
      } 
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } 
    return Integer.valueOf(Integer.parseInt(highestFile.getName().replace(".yml", "")));
  }
  
  public void generateDefaultFolders(File dataFolder) {
    if (!dataFolder.exists())
      dataFolder.mkdir(); 
    File holoFolder = new File(dataFolder + "/holos/");
    if (!holoFolder.exists())
      holoFolder.mkdir(); 
  }
}
