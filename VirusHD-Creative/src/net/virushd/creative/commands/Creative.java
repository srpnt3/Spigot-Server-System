package net.virushd.creative.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.virushd.core.main.CoreMain;
import net.virushd.creative.inventories.Admin;
import net.virushd.creative.main.CreativeMain;
import net.virushd.creative.main.FileManager;
import net.virushd.core.main.PlaceHolder;

public class Creative implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			
			Player p = (Player) sender;
				
			String NotInMode = PlaceHolder.WithPlayer(net.virushd.core.main.FileManager.messages.getString("Messages.NotInMode"), p);
			String NoPerm = PlaceHolder.WithPlayer(net.virushd.core.main.FileManager.messages.getString("Messages.NoPerm"), p);
			String Usage = PlaceHolder.WithPlayer(FileManager.messages.getString("Messages.Usage"), p);
			String Help = PlaceHolder.WithPlayer(FileManager.messages.getString("Messages.Help"), p);
			String Commands = PlaceHolder.WithPlayer(FileManager.messages.getString("Messages.Commands"), p);
			String Rules = PlaceHolder.WithPlayer(FileManager.messages.getString("Messages.Rules"), p);
	
			if (cmd.getName().equalsIgnoreCase("creative")) {
				
				if (CoreMain.isNormal(p)) {
					
					/*
					 * Normal
					 */
					
					if (p.hasPermission("virushd.creative.command.creative") || p.hasPermission("*")) {
						if (CreativeMain.players.contains(p)) {
							switch (args.length) {
							case 0:
								p.sendMessage(Usage);
								break;
								
							// Help, Commands, Rules
							case 1:
								if (args[0].equalsIgnoreCase("hilfe") || args[0].equalsIgnoreCase("help")) {
									
									// help
									p.sendMessage(Help);
									break;
								} else if (args[0].equalsIgnoreCase("commands")) {
									
									// commands
									p.sendMessage(Commands);
									break;
								} else if (args[0].equalsIgnoreCase("regeln") || args[0].equalsIgnoreCase("rules")) {
									
									// rules
									p.sendMessage(Rules);
									break;
								} else {
									p.sendMessage(Usage);
									break;
								}
		
							default:
								p.sendMessage(Usage);
								break;
							}
						}
					} else {
						p.sendMessage(NoPerm);
					}
				} else if (CoreMain.isAdmin(p)) {
					
					/*
					 * Admin
					 */
					
					if (p.hasPermission("virushd.creative.command.creative") || p.hasPermission("*")) {
						Admin.open(p);
					} else {
						p.sendMessage(NoPerm);
					}
				} else {
					
					/*
					 * Troll
					 */
					
					p.sendMessage(NotInMode);
				}
			}
		}
		return false;
	}
}