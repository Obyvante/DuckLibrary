package com.duck;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class DuckLibraryTest implements Listener {

    @EventHandler
    public void e(PlayerToggleSneakEvent event) {
        if (event.isSneaking())
            return;
        DuckLibrary.getMessageHandler().sendTitle(event.getPlayer(), "&aIt's a fucking test!", "&bSubitle!", 30, 120, 30);
        DuckLibrary.getMessageHandler().sendActionBar(event.getPlayer(), "&aIt's another fucking example!");
    }

}
