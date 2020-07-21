package live.chanakancloud.chanasmp.events;

import com.comphenix.protocol.events.PacketEvent;
import live.chanakancloud.chanasmp.ChanaSMP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;

public class PlayerFreeze implements Listener {
    @EventHandler
    public static void playerMove(PlayerMoveEvent event) {
        if(ChanaSMP.FreezedPlayers.contains(event.getPlayer().getName())) {
            event.getPlayer().teleport(event.getFrom());
            event.getPlayer().sendMessage(ChatColor.GREEN + "[ChanaSMP] " + ChatColor.RED + "You have been frozen, you cannot move!");
        }
    }

    @EventHandler
    public static void playerDropItems(PlayerDropItemEvent event) {
        if(ChanaSMP.FreezedPlayers.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.GREEN + "[ChanaSMP] " + ChatColor.RED + "You have been frozen, you cannot drop items!");
        }
    }

    @EventHandler
    public static void playerInteract(PlayerInteractEvent event) {
        if(ChanaSMP.FreezedPlayers.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.GREEN + "[ChanaSMP] " + ChatColor.RED + "You have been frozen, you cannot interact with blocks!");
        }
    }

    @EventHandler
    public static void playerAttackEntity(EntityDamageEvent event) {
        if(ChanaSMP.FreezedPlayers.contains(event.getEntity().getName())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public static void playerGetAttackByEntity(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            if(event.getEntity() instanceof Player) {
                if(ChanaSMP.FreezedPlayers.contains(event.getEntity().getName())) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void playerOnBow(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Arrow) {
            if (event.getEntity() instanceof Player) {
                if (ChanaSMP.FreezedPlayers.contains(event.getEntity().getName())) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void playerOnCommands(PlayerCommandPreprocessEvent event) {
        if (ChanaSMP.FreezedPlayers.contains(event.getPlayer().getName())) {
            final String message = event.getMessage();
            final String[] array = message.split(" ");
            if (!array[0].equalsIgnoreCase("/msg") && !array[0].equalsIgnoreCase("/tell") && !array[0].equalsIgnoreCase("/r") && !array[0].equalsIgnoreCase("/whisper") && !array[0].equalsIgnoreCase("/t") && !array[0].equalsIgnoreCase("/w")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&c You are not allowed to run this commands when you are frozen!"));
            }
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            if (ChanaSMP.FreezedPlayers.contains(event.getEntity().getName())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onFight(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (event.getEntity() instanceof Player) {
                if (ChanaSMP.FreezedPlayers.contains(event.getDamager().getName())) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if(ChanaSMP.FreezedPlayers.contains(event.getPlayer().getName())) {
            for (Player pl : ChanaSMP.plugin.getServer().getOnlinePlayers()) {
                if (pl.hasPermission("chanasmp.quitmessage")) {
                    pl.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f Frozen player &b had quit!"));
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + event.getPlayer().getName() + "Suspected of Unfair Advantages");
                }
                else {
                    return;
                }
            }
        }
    }

}
