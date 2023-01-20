package de.cuzimlel.teamchat.commands;

import de.cuzimlel.teamchat.AdminChat;
import de.cuzimlel.teamchat.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChatConfiger implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("adminchat.*")) {
                player.sendMessage(String.valueOf(Main.getChatConfig().getConfiguration().get("NoPerm")));
            } else {
                if (args.length == 1) {
                        switch (args[0]) {
                            case "enable":
                                Main.getChatConfig().enableChat();
                                Bukkit.getOnlinePlayers().forEach(user ->{
                                    user.sendMessage(String.valueOf(Main.getChatConfig().getConfiguration().get("chat-enable-broadcast")));
                                });
                                break;
                            case "disable":
                                Main.getChatConfig().disableChat();
                                Bukkit.getOnlinePlayers().forEach(user ->{
                                    user.sendMessage(String.valueOf(Main.getChatConfig().getConfiguration().get("chat-disable-broadcast")));
                                });
                                break;
                            case "clear":
                                clearChat();
                                break;
                            case "admin":
                                if (AdminChat.adminchat.contains(player)) {
                                    player.sendMessage(Main.prefix + ChatColor.BLUE + "You are already in Admin-Chat!");
                                } else {
                                    AdminChat.adminchat.add(player);
                                    player.sendMessage(Main.prefix + ChatColor.BLUE + "You are now in Admin-Chat!");
                                }
                                break;
                            case "public":
                                if (!AdminChat.adminchat.contains(player)) {
                                    player.sendMessage(Main.prefix + ChatColor.BLUE + "You are already in Public-Chat!");
                                } else {
                                    AdminChat.adminchat.remove(player);
                                    player.sendMessage(Main.prefix + ChatColor.BLUE + "You are now in Public-Chat!");
                                }
                                break;
                            case "motd-true":
                                player.sendMessage(Main.prefix + ChatColor.BLUE + "Set Motd to true!");
                                Main.getChatConfig().getConfiguration().set("motd",true);
                                 break;
                            case "motd-false":
                                player.sendMessage(Main.prefix + ChatColor.BLUE + "Set Motd to false!");
                                Main.getChatConfig().getConfiguration().set("motd",false);
                                break;
                            case "help":
                                net.md_5.bungee.api.chat.TextComponent component = new TextComponent(Main.prefix + ChatColor.GREEN + "--> " + ChatColor.BLUE + "Please visit the AdminChat plugin page for more information!");
                                player.spigot().sendMessage(new TextComponent(component));
                                break;
                            case "reload":
                                Main.getChatConfig().save();
                                player.sendMessage(Main.prefix + ChatColor.BLUE + "Reloaded the config!");
                                break;
                        }
                } else {
                    player.sendMessage(Main.prefix + ChatColor.RED + "CommandError: --> Please use /chat [arg]");
                }
            }
        }

        return false;
    }
    public void clearChat() {
        for (int i = 0; i < 200; i++) {
            Bukkit.broadcastMessage("");
            if (i == 199) {
                Bukkit.broadcastMessage(String.valueOf(Main.getChatConfig().getConfiguration().get("chat-clear-broadcast")));
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> arguments = new ArrayList<>();

        if (args.length == 1) {
            arguments.add("enable");
            arguments.add("disable");
            arguments.add("help");
            arguments.add("clear");
            arguments.add("public");
            arguments.add("admin");
            arguments.add("motd-true");
            arguments.add("motd-false");
            arguments.add("reload");
        }
        List<String> output = new ArrayList<>();
        String currentarg = args[args.length-1].toLowerCase();
        arguments.forEach(s -> {
            String s1 = s.toLowerCase();
            if (s1.startsWith(currentarg)) {
                output.add(s);
            }
        });
        return output;
    }
}
