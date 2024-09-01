package com.chimericdream.athenaeum.loot;

import com.chimericdream.athenaeum.AthenaeumModInfo;
import com.mojang.serialization.MapCodec;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AthenaeumLootFunctionTypes {
    public static LootFunctionType<GetRandomBookFunction> GET_RANDOM_BOOK;

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static <T extends LootFunction> LootFunctionType<T> register(String id, MapCodec<T> codec) {
        return (LootFunctionType) Registry.register(
            Registries.LOOT_FUNCTION_TYPE,
            Identifier.of(AthenaeumModInfo.MOD_ID, id),
            new LootFunctionType(codec)
        );
    }

    public static void register() {
        GET_RANDOM_BOOK = register("get_random_book", GetRandomBookFunction.CODEC);
    }
}
