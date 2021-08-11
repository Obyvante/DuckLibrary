package com.duck;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.concurrent.TimeUnit;

public class DuckLibraryTest implements Listener {

    @EventHandler
    public void e(PlayerToggleSneakEvent event) {
        if (event.isSneaking())
            return;

        //Message
        DuckLibrary.getMessageHandler().sendTitle(event.getPlayer(), "&aIt's a test!", "&bSubtitle!", 30, 120, 30);
        DuckLibrary.getMessageHandler().sendActionBar(event.getPlayer(), "&aIt's another test!");
    }

    @EventHandler
    public void e(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        DuckLibrary.createChain()
                .thenSync(() -> Bukkit.getOnlinePlayers().forEach(player1 -> player1.sendMessage("§aKlo")))
                .thenDelayedSync(() -> player.sendMessage("waitied 10"), 10, TimeUnit.SECONDS)
                .thenSync(() -> player.sendMessage("test!"))
                .start();
    }

}
