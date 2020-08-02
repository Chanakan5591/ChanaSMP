package live.chanakancloud.chanasmp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static live.chanakancloud.chanasmp.events.BotDuelGUI.openInventory;

public class BotDuelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            ;
        }
        if(sender instanceof Player) {
            Player p = (Player) sender;
            openInventory(p);
            return true;
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&c Only Player can use this command!"));
            return true;
        }
    }
}
