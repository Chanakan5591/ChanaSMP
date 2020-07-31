package live.chanakancloud.chanasmp.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
    public void onInventoryClick(final InventoryClickEvent event) throws IOException {
        if(event.getInventory() != inv) return;
        event.setCancelled(true);
        final ItemStack ClickedItem = event.getCurrentItem();
        if(ClickedItem == null || ClickedItem.getType() == Material.AIR) return;
        HumanEntity PlayerSender = event.getWhoClicked();
        if(ClickedItem.getType() == Material.DIAMOND_SWORD) {
            TeleportToArenaSpawnNPC(0, PlayerSender);
        }
        final Player p = (Player) event.getWhoClicked();
    }

    public void TeleportToArenaSpawnNPC(int gameMode, HumanEntity sender) throws IOException {
        for(int i = 0; i < Bukkit.getServer().getWorld("arena").getPlayers().size(); i++) {
            if(i == 0) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + sender.getName() + " arena");
                String uuid = sender.getUniqueId().toString();
                HttpURLConnection sessionConn = (HttpURLConnection) new URL("https://sessionserver.mojang.com/session/minecraft/profile/"+uuid.replace("-", "")).openConnection();
                /*JSONTokener tokener = new JSONTokener(sessionConn.getInputStream());
                JSONObject root = new JSONObject(tokener);
                Object sessionProperties = root.getString("properties");*/
                //Above Do the JSON parse
            }
            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[ChanaSMP]&f Arena is currently in used, please try again later."));
            }
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
