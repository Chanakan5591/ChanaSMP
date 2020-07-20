package live.chanakancloud.chanasmp;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(ChatColor.GREEN + "[ChanaSMP] " + ChatColor.WHITE + "Welcome, " + ChatColor.AQUA + event.getPlayer().getName() + ChatColor.WHITE + " to the server!");
    }
}
