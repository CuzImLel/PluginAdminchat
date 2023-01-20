package de.cuzimlel.teamchat;

import de.cuzimlel.teamchat.commands.ChatConfiger;
import de.cuzimlel.teamchat.listeners.ChatEvent;
import de.cuzimlel.teamchat.listeners.ServerList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static String prefix = ChatColor.BLUE + "[" + ChatColor.GREEN + "AdminChat" + ChatColor.BLUE + "] ";

    private static Main instance;

    private static ChatConfig chatConfig;

    public static Main getInstance() {
        return instance;
    }

    public static ChatConfig getChatConfig() {
        return chatConfig;
    }

    @Override
    public void onEnable() {
        System.out.println("AdminChat ---> Successfully enabled!");
        instance = this;
        chatConfig = new ChatConfig();
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new ServerList(), this);
        manager.registerEvents(new ChatEvent(),this);
        getCommand("chat").setExecutor(new ChatConfiger());
        getCommand("chat").setTabCompleter(new ChatConfiger());
    }

    @Override
    public void onDisable() {
        System.out.println("AdminChat ---> disabled!");
    }

}
