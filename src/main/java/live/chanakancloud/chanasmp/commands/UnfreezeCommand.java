package live.chanakancloud.chanasmp.commands;

import live.chanakancloud.chanasmp.ChanaSMP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnfreezeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f Usage: /unfreeze <Player Name>"));
            return true;
        }
        if(!sender.hasPermission("chanasmp.commands.unfreeze")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f You are not allowed to run this command!"));
            return true;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().equals(args[0])) {
                if (ChanaSMP.FreezedPlayers.contains(args[0])) {
                    ChanaSMP.FreezedPlayers.remove(args[0]);
                    sender.sendMessage(ChatColor.GREEN + "[ChanaSMP] " + ChatColor.WHITE + "Player unfroze!");
                    p.sendMessage(ChatColor.GREEN + "[ChanaSMP]" + ChatColor.RED + " You have been unfrozen!");
                }
                else {
                    sender.sendMessage(ChatColor.GREEN + "[ChanaSMP] " + ChatColor.WHITE + "Player is not frozen!");
                }
            }
        }
        return true;
    }
}
