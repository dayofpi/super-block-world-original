package com.dayofpi.mixin;

import net.minecraft.world.gen.feature.HugeMushroomFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(HugeMushroomFeature.class)
public class MixinHugeMushroom {
    @Inject(at=@At("HEAD"), method = "getHeight(Ljava/util/Random;)I", cancellable = true)
    protected void getHeight(Random random, CallbackInfoReturnable<Integer> info) {
        int i = random.nextInt(3) + 4;
        if (random.nextInt(12) == 0) {
            i *= 2;
        } else if (random.nextInt(13) == 0) {
            i *= 4;
        }

        info.setReturnValue(i);
        info.cancel();
    }
}
