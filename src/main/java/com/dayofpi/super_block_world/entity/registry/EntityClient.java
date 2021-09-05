package com.dayofpi.super_block_world.entity.registry;

import com.dayofpi.super_block_world.entity.renderer.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class EntityClient {
    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(EntityList.MOD_BOAT, ModBoatRenderer::new);
        EntityRendererRegistry.register(EntityList.BUZZY_SHELL, (context) -> new BuzzyShellRenderer<>(context, ModelLayers.BUZZY_SHELL));
        EntityRendererRegistry.register(EntityList.MOO_MOO, MooMooRenderer::new);
        EntityRendererRegistry.register(EntityList.BUZZY_BEETLE, BuzzyBeetleRenderer::new);
        EntityRendererRegistry.register(EntityList.SPIKE_TOP, SpikeTopRenderer::new);
        EntityRendererRegistry.register(EntityList.NIPPER_PLANT, NipperPlantRenderer::new);
    }
}
