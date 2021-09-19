package com.dayofpi.super_block_world.entity.types.mobs;

import com.dayofpi.super_block_world.misc.ModDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SpiderNavigation;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SpikeTopEntity extends AbstractBuzzy {
    private static final TrackedData<Byte> SPIKE_TOP_FLAGS;

    static {
        SPIKE_TOP_FLAGS = DataTracker.registerData(SpikeTopEntity.class, TrackedDataHandlerRegistry.BYTE);
    }

    public SpikeTopEntity(EntityType<? extends AbstractBuzzy> entityType, World world) {
        super(entityType, world);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SPIKE_TOP_FLAGS, (byte)0);
    }

    public static boolean canSpawn(ServerWorldAccess world, BlockPos pos, Random random) {
        boolean bool1 = checkValidBlock(world.getBlockState(pos.down()));
        boolean bool2 = checkValidBlock(world.getBlockState(pos.up()));
        return AbstractTroop.isSpawnDark(world, pos, random) && !world.isSkyVisible(pos) && (bool1 || bool2);
    }

    protected EntityNavigation createNavigation(World world) {
        return new SpiderNavigation(this, world);
    }

    public void tick() {
        super.tick();
        if (!this.world.isClient) {
            this.setClimbingWall(this.horizontalCollision);
        }
    }

    public boolean isClimbing() {
        return this.isClimbingWall();
    }

    public boolean isClimbingWall() {
        return (this.dataTracker.get(SPIKE_TOP_FLAGS) & 1) != 0;
    }

    public void setClimbingWall(boolean climbing) {
        byte b = this.dataTracker.get(SPIKE_TOP_FLAGS);
        if (climbing) {
            b = (byte)(b | 1);
        } else {
            b &= -2;
        }

        this.dataTracker.set(SPIKE_TOP_FLAGS, b);
    }

    public void pushAwayFrom(Entity entity) {
        super.pushAwayFrom(entity);
        if (entity.getY() > this.getY() && !this.isUpsideDown() && entity.fallDistance > 0 && entity.getBlockX() == this.getBlockX() && !(entity instanceof AbstractBuzzy)) {
            boolean damaged = entity.damage(ModDamageSource.spikyMob(this), entity.fallDistance);
            if (damaged) {
                this.playSound(SoundEvents.ENCHANT_THORNS_HIT, 1.0F, getSoundPitch());
            }
            entity.damage(ModDamageSource.spikyMob(this), entity.fallDistance + 1);
        }
    }

    public void doLandingEffects(float fallDistance, float damageMultiplier) {
        int i = this.computeFallDamage(fallDistance, damageMultiplier);
        if (i > 0) {
            this.world.getOtherEntities(this, this.getBoundingBox().expand(3, 0, 3), EntityPredicates.EXCEPT_SPECTATOR).forEach((entity) -> entity.damage(ModDamageSource.spikyMob(this), i * 2));
            this.playSound(this.getLandingSound(), this.getSoundVolume(), this.getSoundPitch());
            ((ServerWorld) this.world).spawnParticles(ParticleTypes.EXPLOSION, this.getX(), this.getBodyY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
        }
        if (fallDistance > 0.0F) { this.setUpsideDown(false); }
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
