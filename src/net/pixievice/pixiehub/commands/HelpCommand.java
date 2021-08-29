package net.pixievice.pixiehub.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.pixievice.pixiehub.ChatUtils;
import net.pixievice.pixiehub.Main;

public class HelpCommand implements CommandExecutor {
	
	private Main main;
	
	public HelpCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args) {
		List<String> helpMessage = main.getConfig().getStringList("CommandOptions.custom-help-command.lines");
		for (String s : helpMessage) {
			sender.sendMessage(ChatUtils.chat(s));
		}
		
	return true;	
	}

}
