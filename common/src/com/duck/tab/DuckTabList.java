package com.duck.tab;

import org.bukkit.entity.Player;

public interface DuckTabList {



    enum Type {
        EMPTY,
        ONE_STACK,
        TWO_STACK,
        THREE_STACK,
        FOUR_STACK
    }

    Player getPlayer();


}
