package live.chanakancloud.chanasmp.events;

import live.chanakancloud.chanasmp.ChanaSMP;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class BotDuelGUI implements Listener {
    private final Inventory inv;

    public BotDuelGUI() {
        inv = Bukkit.createInventory(null, 9, "Bot Duel Kits");
        initializeItems();
    }
    public void initializeItems() {
        inv.addItem(createGuiItem(Material.DIAMOND_SWORD, "§anodebuf", null, null));
        inv.addItem(createGuiItem(Material.IRON_HELMET, "§bdebuf", null, null));
    }
    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        assert meta != null;
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }
    public void openInventory(final Player player) {
        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        event.getWhoClicked().sendMessage("E");
        if(event.getInventory() != inv) return;
        event.setCancelled(true);
        final ItemStack ClickedItem = event.getCurrentItem();
        if(ClickedItem == null || ClickedItem.getType() == Material.AIR) return;
        Player PlayerSender = (Player) event.getWhoClicked();
        if(ClickedItem.getType() == Material.DIAMOND_SWORD) {
            TeleportToArenaSpawnNPC(0, PlayerSender);
        }
    }

    @EventHandler
    public void onPlayerBlockBreak(final BlockBreakEvent event) {
        if(event.getPlayer().getWorld().getName().equals("arena")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerBlockPlace(final BlockPlaceEvent event) {
        if(event.getPlayer().getWorld().getName().equals("arena")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void OnPlayerInteractEvent(final PlayerInteractEvent event) {
        if(event.getPlayer().getWorld().getName().equals("arena")) {
            if (event.getMaterial() == Material.ENDER_PEARL) {
                if(!ChanaSMP.PlayerOnPearlCooldown.contains(event.getPlayer().getName())) {
                    ChanaSMP.PlayerOnPearlCooldown.add(event.getPlayer().getName());
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ChanaSMP.plugin, new Runnable(){
                        public void run(){
                            ChanaSMP.PlayerOnPearlCooldown.remove(event.getPlayer().getName());
                        }
                    }, 240L);
                } else {
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f You must wait for another 12 seconds before using the pearl again."));
                    event.setCancelled(true);
                }
            }
        }
    }

    public static void TeleportToArenaSpawnNPC(int gameMode, Player sender) {
        if(Bukkit.getServer().getWorld("arena").getPlayers().isEmpty()) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + sender.getName() + " arena");
            String uuid = sender.getUniqueId().toString();
            //HttpURLConnection sessionConn = (HttpURLConnection) new URL("https://sessionserver.mojang.com/session/minecraft/profile/"+uuid.replace("-", "")).openConnection();
            NPC FightBot = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, sender.getName());
        }
        else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f Arena is currently in used, please try again later."));
        }

        //kit add to user and NPC
        /*switch (gameMode) {
            case 0:

        }*/
    }

    @EventHandler
    public void onInventoryClick(final InventoryDragEvent event) {
        if (event.getInventory() == inv) {
            event.setCancelled(true);
        }
    }

}
