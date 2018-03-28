package net.poweredbyawesome.kickcounter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandKickCounter implements CommandExecutor {

    KickCounter plugin;

    public CommandKickCounter(KickCounter kickCounter) {
        this.plugin = kickCounter;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length >= 2) {
            if (args[0].equalsIgnoreCase("check") && sender.hasPermission("kickcounter.check")) {
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                if (offlinePlayer != null) {
                    sender.sendMessage(coulour(String.format("&a%s has been kicked &c%s &atimes", offlinePlayer.getName(), plugin.getKick(offlinePlayer.getUniqueId()).toString())));
                } else {
                    sender.sendMessage(coulour("&cPlayer does not exist!"));
                }
            }
        }
        return false;
    }

    public String coulour(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
