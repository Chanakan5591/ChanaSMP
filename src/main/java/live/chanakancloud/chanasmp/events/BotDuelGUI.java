package live.chanakancloud.chanasmp.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BotDuelGUI implements Listener {
    public static Inventory inv;

    public BotDuelGUI() {
        inv = Bukkit.createInventory(null, 9, "Bot Duel Kits");
        initializeItems();
    }
    public void initializeItems() {
        inv.addItem(createGuiItem(Material.DIAMOND_SWORD, "&anodebuf", null, null));
        inv.addItem(createGuiItem(Material.IRON_HELMET, "§bdebuf", null, null));
    }
    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
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
        if(event.getInventory() != inv) return;
        event.setCancelled(true);
        final ItemStack ClickedItem = event.getCurrentItem();
        if(ClickedItem == null || ClickedItem.getType() == Material.AIR) return;
        if(ClickedItem.getType() == Material.DIAMOND_SWORD) {
            TeleportToArenaSpawnNPC(0);
        }
        final Player p = (Player) event.getWhoClicked();
    }

    public void TeleportToArenaSpawnNPC(int gameMode) {
        
        switch (gameMode) {
            case 0:
                ; //TODOS 
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryDragEvent event) {
        if (event.getInventory() == inv) {
            event.setCancelled(true);
        }
    }

}
