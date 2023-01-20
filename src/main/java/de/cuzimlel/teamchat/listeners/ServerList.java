package de.cuzimlel.teamchat.listeners;

import de.cuzimlel.teamchat.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerList implements Listener {
    @EventHandler
    public void onServerList(ServerListPingEvent event) {
        if (Main.getChatConfig().getConfiguration().get("motd").equals(true)) {
            event.setMotd(String.valueOf(Main.getChatConfig().getConfiguration().get("motd-text")));
        }
    }
}
