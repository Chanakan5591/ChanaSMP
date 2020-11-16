package live.chanakancloud.chanasmp;

import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatEvent {
    public static void Filter(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        String message = packet.getStrings().read(0);

        if (!event.getPlayer().hasPermission("chanasmp.filter.bypass")) {
            if (message.contains("shit") || message.contains("fuck") || message.contains("bitch") || message.contains("nigger") || message.contains("nigga")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.GREEN + "[ChanaSMP] " + ChatColor.WHITE + "You are not allowed to say that, this is your first warning. Further infractions will result you in a mute!");
                for (Player p : Bukkit.getOnlinePlayers()) {

                    if (p.hasPermission("chanasmp.notify")) {
                        p.sendMessage(ChatColor.GREEN + "[ChanaSMP] " + ChatColor.AQUA + event.getPlayer().getName() + ChatColor.WHITE + " message was filtered. Message was: " + ChatColor.LIGHT_PURPLE + event.getPacket().getStrings().read(0));
                    }

                }
            }
        }
    }
    public static void Mute(PacketEvent event) {
        PacketContainer packet = event.getPacket();

        if(ChanaSMP.MutedPlayer.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f You are currently muted!"));
        }
    }
}