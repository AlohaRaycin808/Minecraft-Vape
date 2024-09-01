package com.chimericdream.athenaeum;

import com.chimericdream.athenaeum.loot.AthenaeumLootFunctionTypes;
import com.chimericdream.athenaeum.registries.AthenaeumRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AthenaeumMod {
    public static final Logger LOGGER = LoggerFactory.getLogger("athenaeum");

    public static void init() {
        AthenaeumRegistries.init();
        AthenaeumLootFunctionTypes.register();
    }
}
