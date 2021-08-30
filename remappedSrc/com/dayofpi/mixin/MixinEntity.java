package com.dayofpi.mixin;

import com.dayofpi.super_block_world.Main;
import com.dayofpi.super_block_world.misc.Tags;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Entity.class)
public abstract class MixinEntity {
    private final Random random = new Random();
    protected boolean touchingPoison;

    public boolean isTouchingPoison() {
        return this.touchingPoison;
    }

    @Inject(at = @At("TAIL"), method = "baseTick")
    void baseTick(CallbackInfo info) {
        if (this.isTouchingPoison()) {
            if (!((InterfaceEntity) this).aType().isIn(Tags.POISON_IMMUNE)) {
                if (((InterfaceEntity) this).iDamage(Main.POISON, 4.0F)) {
                    ((InterfaceEntity) this).aWorld().playSound(null, ((InterfaceEntity) this).aBlockPos(), SoundEvents.ENTITY_GENERIC_BURN, ((InterfaceEntity) this).iGetSoundCategory(), 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
                }
                ((InterfaceEntity) this).iDamage(Main.POISON, 4.0F);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onSwimmingStart", cancellable = true)
    public void onSwimmingStart(CallbackInfo info) {
        if (((InterfaceEntity) this).iUpdateMovementInFluid(Tags.POISON, 0.014D)) {
            // Do not show water splash particles
            info.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "checkWaterState")
    void checkWaterState(CallbackInfo info) {
        this.touchingPoison = ((InterfaceEntity) this).iUpdateMovementInFluid(Tags.POISON, 0.014D);
    }
}
