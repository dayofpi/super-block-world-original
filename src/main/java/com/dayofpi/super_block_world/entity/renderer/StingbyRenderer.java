package com.dayofpi.super_block_world.entity.renderer;

import com.dayofpi.super_block_world.Main;
import com.dayofpi.super_block_world.entity.model.StingbyModel;
import com.dayofpi.super_block_world.entity.registry.ModelLayers;
import com.dayofpi.super_block_world.entity.types.mobs.StingbyEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class StingbyRenderer<T extends StingbyEntity> extends MobEntityRenderer<T, StingbyModel<T>> {
    private static final Identifier NORMAL = new Identifier(Main.MOD_ID, "textures/entity/stingby.png");
    private static final Identifier ANGRY = new Identifier(Main.MOD_ID, "textures/entity/stingby_angry.png");

    public StingbyRenderer(EntityRendererFactory.Context context) {
        super(context, new StingbyModel<>(context.getPart(ModelLayers.STINGBY)), 0.5F);
    }

    @Override
    public Identifier getTexture(T entity) {
        return entity.isAttacking() ? ANGRY : NORMAL;
    }
}
