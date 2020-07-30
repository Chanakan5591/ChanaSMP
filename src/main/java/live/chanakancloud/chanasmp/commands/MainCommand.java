package live.chanakancloud.chanasmp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f ChanaSMP v0.2.4 by Chanakan Mungtin"));
            return true;
        }
    return false;
    }
}
