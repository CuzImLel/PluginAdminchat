package de.cuzimlel.teamchat;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ChatConfig {

    private final File file;
    private final File dir;
    private final YamlConfiguration configuration;

    public ChatConfig() {
        this.dir = new File("./plugins/AdminChat");
        if (!this.dir.exists()) {
            dir.mkdirs();
        }
        this.file = new File(dir, "config.yml");
        if (!this.file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.configuration = YamlConfiguration.loadConfiguration(file);
        if (!this.configuration.contains("NoPerm")) {
            this.configuration.set("NoPerm", ChatColor.RED + "Nope");
            save();
        }
        if (!this.configuration.contains("chatting")) {
            this.configuration.set("chatting", true);
            save();
        }
        if (this.configuration.get("chatting") == null) {
            this.configuration.set("chatting",true);
            save();
        }

        if (!this.configuration.contains("chat-enable-broadcast")) {
            this.configuration.set("chat-enable-broadcast", ChatColor.GREEN + "Chat is enabled");
            save();
        }
        if (!this.configuration.contains("chat-disable-broadcast")) {
            this.configuration.set("chat-disable-broadcast", ChatColor.RED + "Chat is disabled");
            save();
        }
        if (!this.configuration.contains("chat-clear-broadcast")) {
            this.configuration.set("chat-clear-broadcast", ChatColor.RED + "Chat was cleared!");
            save();
        }
        if (!this.configuration.contains("motd")) {
            this.configuration.set("motd", false);
            save();
        }
        if (this.configuration.get("motd") == null) {
            this.configuration.set("motd", false);
           save();
        }
        if (!this.configuration.contains("motd-text")) {
            this.configuration.set("motd-text", "Change Motd in Plugin Config");
            save();
        }
        if (!this.configuration.contains("cooldown")) {
            this.configuration.set("cooldown", 0);
            save();
        }
        if (this.configuration.get("cooldown") == null) {
            this.configuration.set("cooldown", 0);
            save();
        }
        if (!this.configuration.contains("cooldown-message")) {
            this.configuration.set("cooldown-message", ChatColor.RED + "Please wait until you write a next message!");
            save();
        }
    }

    public File getFile() {
        return file;
    }

    public File getDir() {
        return dir;
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }
    public void save() {
        try {
            this.configuration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void enableChat() {
        this.configuration.set("chatting",true);
        save();
    }
    public void disableChat() {
        this.configuration.set("chatting",false);
        save();
    }
}
