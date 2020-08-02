package live.chanakancloud.chanasmp.commands;

import live.chanakancloud.chanasmp.events.BotDuelGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BotDuelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            ;
        }
        if(sender instanceof Player) {
            BotDuelGUI bdg = new BotDuelGUI();
            Player p = (Player) sender;
            bdg.openInventory(p);
            return true;
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&c Only Player can use this command!"));
            return true;
        }
    }
}
