package live.chanakancloud.chanasmp;

import live.chanakancloud.chanasmp.commands.UnfreezeCommand;
import live.chanakancloud.chanasmp.commands.FreezeCommand;
import live.chanakancloud.chanasmp.events.PlayerFreeze;
import live.chanakancloud.chanasmp.packet.PacketListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ChanaSMP extends JavaPlugin {

    public static List<String> FreezedPlayers = new ArrayList<String>();
    public static ChanaSMP plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getLogger().info("ChanaSMP v0.1.0-DEV Loading..");
        getLogger().info("Registering Events");
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerFreeze(), this);
        getLogger().info("registering commands");
        this.getCommand("freeze").setExecutor(new FreezeCommand());
        this.getCommand("unfreeze").setExecutor(new UnfreezeCommand());
        getLogger().info("Hooking into packet listener");
        PacketListener.Listener();
    }

    @Override
    public void onDisable() {

    }
}
