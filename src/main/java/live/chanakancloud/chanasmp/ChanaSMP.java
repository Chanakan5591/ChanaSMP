package live.chanakancloud.chanasmp;

import live.chanakancloud.chanasmp.commands.*;
import live.chanakancloud.chanasmp.events.PlayerFreeze;
// import live.chanakancloud.chanasmp.events.BotDuelGUI;
import live.chanakancloud.chanasmp.packet.PacketListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ChanaSMP extends JavaPlugin {

    public static List<String> FreezedPlayers = new ArrayList<String>();
    public static List<String> PlayerOnPearlCooldown = new ArrayList<String>();
    public static List<String> MutedPlayer = new ArrayList<String>();
    public static ChanaSMP plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getLogger().info("ChanaSMP v1.4.7-DEV Loading..");
        getLogger().info("Registering Events");
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerFreeze(), this);
        // getServer().getPluginManager().registerEvents(new BotDuelGUI(), this);
        getLogger().info("Registering Commands");
        this.getCommand("fly").setExecutor(new FlyCommand());
        // this.getCommand("botduel").setExecutor(new BotDuelCommand());
        this.getCommand("freeze").setExecutor(new FreezeCommand());
        this.getCommand("unfreeze").setExecutor(new UnfreezeCommand());
        this.getCommand("chanasmp").setExecutor(new MainCommand());
        this.getCommand("jumpscare").setExecutor(new JumpscareCommand());
        this.getCommand("mute").setExecutor(new MuteCommand());
        getLogger().info("Hooking into PacketListener");
        PacketListener.Listener();
    }

    @Override
    public void onDisable() {
        FreezedPlayers = new ArrayList<String>();
        MutedPlayer = new ArrayList<String>();
    }
}
