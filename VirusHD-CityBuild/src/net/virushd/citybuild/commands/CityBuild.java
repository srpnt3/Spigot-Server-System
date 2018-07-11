package net.virushd.citybuild.commands;

import net.virushd.core.main.Utils;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.virushd.citybuild.inventories.Admin;
import net.virushd.citybuild.inventories.Plot;
import net.virushd.citybuild.main.CityBuildMain;
import net.virushd.citybuild.main.FileManager;

import net.virushd.core.main.PlaceHolder;
import net.virushd.core.main.CoreMain;
import net.virushd.core.main.SaveUtils;

public class CityBuild implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			
			Player p = (Player) sender;

			String NotInMode = PlaceHolder.WithPlayer(net.virushd.core.main.FileManager.messages.getString("Messages.NotInMode"), p);
			String NoPerm = PlaceHolder.WithPlayer(net.virushd.core.main.FileManager.messages.getString("Messages.NoPerm"), p);
			String Usage = PlaceHolder.WithPlayer(FileManager.messages.getString("Messages.Usage"), p);
			String OnlyInCityBuildWorld = PlaceHolder.WithPlayer(FileManager.messages.getString("Messages.OnlyInCityBuildWorld"), p);
			String Help = PlaceHolder.WithPlayer(FileManager.messages.getString("Messages.Help"), p);
			String Commands = PlaceHolder.WithPlayer(FileManager.messages.getString("Messages.Commands"), p);
			String Rules = PlaceHolder.WithPlayer(FileManager.messages.getString("Messages.Rules"), p);
			String WrongWorld = PlaceHolder.WithPlayer(FileManager.messages.getString("Messages.WrongWorld"), p);

			if (cmd.getName().equalsIgnoreCase("CityBuild")) {

				if (CoreMain.isNormal(p)) {

					/*
					 * Normal
					 */

					if (p.hasPermission("virushd.citybuild.command.citybuild") || p.hasPermission("*")) {
						if (CityBuildMain.players.contains(p)) {
							switch (args.length) {
							case 0:
								p.sendMessage(Usage);
								break;

							// Help, Commands, Rules, Plot
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
								} else if (args[0].equalsIgnoreCase("plot")) {

									// plot
									if (p.getWorld().equals(SaveUtils.GetLocationFromFile(FileManager.config, "Spawns.CityBuild").getWorld())) {
										Plot.open(p);
									} else {
										p.sendMessage(OnlyInCityBuildWorld);
									}
									break;
								} else {
									p.sendMessage(Usage);
									break;
								}

							// tp
							case 2:

								// tp
								if (args[0].equalsIgnoreCase("tp")) {
									if (args[1].equalsIgnoreCase("farmwelt") || args[1].equalsIgnoreCase("farmworld")) {
										
										Utils.SmoothTeleport(p, SaveUtils.GetLocationFromFile(FileManager.config, "Spawns.Farmworld"));
										p.setGameMode(GameMode.SURVIVAL);
										break;
									} else if (args[1].equalsIgnoreCase("nether")) {
										
										Utils.SmoothTeleport(p, SaveUtils.GetLocationFromFile(FileManager.config, "Spawns.Nether"));
										p.setGameMode(GameMode.SURVIVAL);
										break;
									} else if (args[1].equalsIgnoreCase("citybuild")) {
										
										Utils.SmoothTeleport(p, SaveUtils.GetLocationFromFile(FileManager.config, "Spawns.CityBuild"));
										p.setGameMode(GameMode.SURVIVAL);
										break;
									} else {
										p.sendMessage(WrongWorld);
										break;
									}
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

					if (p.hasPermission("virushd.citybuild.command.citybuild") || p.hasPermission("*")) {
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

//	private void PlotInventory(Player p) {
//
//		String InventoryDisplayName = PlaceHolder.Normal(FileManager.inv_plot.getString("InventoryDisplayName"));
//		
//		Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, InventoryDisplayName);
//
//	
//		
//		// create Items
//		ItemStack Pos1 = SaveUtils.GetItemFromFile(FileManager.inv_plot, "Items.Pos1");
//		ItemStack Pos2 = SaveUtils.GetItemFromFile(FileManager.inv_plot, "Items.Pos2");
//		ItemStack Delete = SaveUtils.GetItemFromFile(FileManager.inv_plot, "Items.Delete");
//		ItemStack Create = SaveUtils.GetItemFromFile(FileManager.inv_plot, "Items.Create");
//
//		inv.setItem(0, Pos1);
//		inv.setItem(1, Pos2);
//		inv.setItem(3, Delete);
//		inv.setItem(4, Create);
//
//		p.openInventory(inv);
//	}
}