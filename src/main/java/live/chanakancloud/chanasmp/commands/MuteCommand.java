package live.chanakancloud.chanasmp.commands;

import live.chanakancloud.chanasmp.ChanaSMP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//TODOS: Check if player have bypass perm, if yes then error

//DON'T FORGET TO CREATE UNMUTE

public class MuteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("chanasmp.commands.mute")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f You are not allowed to run this command!"));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f Usage: /mute <Player Name> <Reason>"));
            return true;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().equals(args[0])) {
               if(!ChanaSMP.MutedPlayer.contains(args[0])) {
                   ChanaSMP.MutedPlayer.add(args[0]);
                   sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f Muted Player"));
               }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f Player is not online!"));
            }
        }
        return true;
    }
}
