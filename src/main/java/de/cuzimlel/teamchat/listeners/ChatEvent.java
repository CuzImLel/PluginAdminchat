package de.cuzimlel.teamchat.listeners;

import de.cuzimlel.teamchat.AdminChat;
import de.cuzimlel.teamchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();


        if (!Main.getChatConfig().getConfiguration().get("chatting").equals(true)) {
            if (!player.hasPermission("adminchat.*")) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "Chat is currently disabled!");
            } else {
                event.setCancelled(false);

            }
        } else {
            if (!player.hasPermission("adminchat.*")) {
                if (AdminChat.cooldown.contains(player)) {
                    event.setCancelled(true);
                    player.sendMessage(String.valueOf(Main.getChatConfig().getConfiguration().get("cooldown-message")));
                } else {
                    event.setCancelled(false);
                    AdminChat.addPlayer(player, Main.getChatConfig().getConfiguration().getInt("cooldown"));
                }
            }
        }
        if (AdminChat.adminchat.contains(player)) {
            event.setCancelled(true);
            String message = event.getMessage();
            Bukkit.getOnlinePlayers().forEach(op -> {
                if (op.hasPermission("adminchat.*")) {
                    op.sendMessage(Main.prefix + ChatColor.GOLD + event.getPlayer().getName() + ChatColor.GRAY + " : " + ChatColor.BLUE + message);
                }
            });
        }
    }
}
