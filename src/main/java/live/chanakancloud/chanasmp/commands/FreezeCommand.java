package live.chanakancloud.chanasmp.commands;

import live.chanakancloud.chanasmp.ChanaSMP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args == null) {
            return false;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().equals(args[0])) {
                if (!ChanaSMP.FreezedPlayers.contains(args[0])) {
                    ChanaSMP.FreezedPlayers.add(args[0]);
                    sender.sendMessage(ChatColor.GREEN + "[ChanaSMP] " + ChatColor.WHITE + "Player froze!");
                    p.sendMessage(ChatColor.GREEN + "[ChanaSMP] " + ChatColor.RED + "You have been frozen by moderator!");
                } else {
                    sender.sendMessage(ChatColor.GREEN + "[ChanaSMP]" + ChatColor.WHITE + "Player is already frozen!");
                }
            }
            else {
                sender.sendMessage(ChatColor.GREEN + "[ChanaSMP] " + ChatColor.WHITE + "Player is offline or not a valid player name");
            }
        }
        return true;
    }
}
