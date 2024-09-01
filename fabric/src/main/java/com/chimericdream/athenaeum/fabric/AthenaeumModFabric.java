package com.chimericdream.athenaeum.fabric;

import com.chimericdream.athenaeum.AthenaeumMod;
import com.chimericdream.athenaeum.AthenaeumModInfo;
import com.chimericdream.athenaeum.registries.AthenaeumRegistries;
import com.google.common.base.Predicates;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.util.Map;

public final class AthenaeumModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        AthenaeumMod.init();

        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return Identifier.of(AthenaeumModInfo.MOD_ID, "athenaeum_book_resource_listener");
            }

            @Override
            public void reload(ResourceManager manager) {
                AthenaeumRegistries.BOOKS.clear();

                Map<Identifier, Resource> resources = manager.findResources("athenaeum_books", Predicates.alwaysTrue());
                resources.forEach((id, resource) -> {
                    Identifier bookId = Identifier.of(
                        id.getNamespace(),
                        id.getPath()
                            .replace("athenaeum_books/", "")
                            .replace(".json", "")
                    );

                    try (InputStream stream = resource.getInputStream()) {
                        AthenaeumRegistries.BOOKS.addFromInputStream(bookId, stream);
                    } catch (Exception e) {
                        AthenaeumMod.LOGGER.error("Error occurred while loading resource json" + id.toString(), e);
                    }
                });
            }
        });
    }
}
