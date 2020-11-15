package live.chanakancloud.chanasmp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("chanasmp.commands.fly")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f You are not allowed to run this command!"));
            return true;
        }

        if (sender instanceof Player) {
          Player player = (Player) sender;
          if(player.getAllowFlight() == false) {
            player.setAllowFlight(true);
          } else {
            player.setAllowFlight(false);
          }
        }
        return true;
    }
}
