package de.cuzimlel.teamchat;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class AdminChat {
    public static List<Player> adminchat = new ArrayList<>();
    public static List<Player> cooldown = new ArrayList<>();


    public static void addPlayer(Player player, int time) {
        cooldown.add(player);
        new BukkitRunnable() {
            @Override
            public void run() {
                cooldown.remove(player);
            }
        }.runTaskLater(Main.getInstance(),time * 20L);
    }

}

