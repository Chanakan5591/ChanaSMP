package live.chanakancloud.chanasmp.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JumpscareCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f Usage: /jumpscare <Player Name>"));
            return true;
        }
        if(!sender.hasPermission("chanasmp.commands.jumpscare")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f You are not allowed to run this command!"));
            return true;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().equals(args[0])) {
                p.playEffect(EntityEffect.GUARDIAN_TARGET);
                p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.MASTER, 2F, 1F);
                return true;
            }
        }
        return true;
    }
}
