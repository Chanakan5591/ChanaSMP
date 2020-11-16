package live.chanakancloud.chanasmp.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import live.chanakancloud.chanasmp.ChanaSMP;
import live.chanakancloud.chanasmp.ChatEvent;

public class PacketListener {
    public static void Listener() {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new PacketAdapter(ChanaSMP.plugin,
                ListenerPriority.NORMAL, PacketType.Play.Client.CHAT) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                ChatEvent.Filter(event);
            }
        });
    }
}
