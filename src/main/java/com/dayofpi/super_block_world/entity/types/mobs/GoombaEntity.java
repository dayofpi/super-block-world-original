package com.dayofpi.super_block_world.entity.types.mobs;

import com.dayofpi.super_block_world.entity.registry.EntityList;
import com.dayofpi.super_block_world.item.registry.ItemList;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LightType;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class GoombaEntity extends EnemyEntity {
    private static final TrackedData<Integer> SIZE;
    static final Predicate<ItemEntity> POWER_UP_FILTER;

    static {
        SIZE = DataTracker.registerData(GoombaEntity.class, TrackedDataHandlerRegistry.INTEGER);
        POWER_UP_FILTER = (item) -> !item.cannotPickup() && item.isAlive() && item.getStack().isOf(ItemList.SUPER_MUSHROOM);
    }
    public GoombaEntity(EntityType<? extends EnemyEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return EnemyEntity.createAttributes()
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    public void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.add(2, new RevengeGoal(this));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.7D));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static boolean canSpawn(ServerWorldAccess world, BlockPos pos, EntityType<? extends MobEntity> type) {
        return world.getBlockState(pos.down()).allowsSpawning(world, pos, type) && !(world.getLightLevel(LightType.BLOCK, pos) > 0);
    }

    public boolean tryAttack(Entity target) {
        if (this.distanceTo(target) < 2F) {
            return super.tryAttack(target);
        } return false;
    }

    public double getMountedHeightOffset() {
        return this.getDimensions(getPose()).height;
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        if (this.random.nextFloat() > 0.5F) {
            this.setSize(1);
            if (this.random.nextInt(10) == 0) {
                GoombaEntity goombaEntity = EntityList.GOOMBA.create(world.toServerWorld());
                if (goombaEntity != null) {
                    goombaEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                    goombaEntity.initialize(world, difficulty, SpawnReason.JOCKEY, null, null);
                    goombaEntity.setSize(1);
                    goombaEntity.startRiding(this, true);
                }
            }
        } else {
            this.setSize(this.random.nextInt(3));
        }
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    public void tick() {
        super.tick();
        if (this.getSize() < 2 && this.isAlive()) {
            List<ItemEntity> list = GoombaEntity.this.world.getEntitiesByClass(ItemEntity.class, this.getBoundingBox(), POWER_UP_FILTER);
            if (!list.isEmpty()) {
                list.forEach((Entity::discard));
                this.setSize(this.getSize() + 1);
            }
        }
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SIZE, 1);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Size", this.getSize());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setSize(nbt.getInt("Size"));
        super.readCustomDataFromNbt(nbt);
    }

    public void onTrackedDataSet(TrackedData<?> data) {
        if (SIZE.equals(data)) {
            this.calculateDimensions();
            this.setYaw(this.headYaw);
            this.bodyYaw = this.headYaw;
            if (this.isTouchingWater() && this.random.nextInt(20) == 0) {
                this.onSwimmingStart();
            }
        }
        super.onTrackedDataSet(data);
    }

    public int getSize() {
        return this.dataTracker.get(SIZE);
    }

    public void setSize(int size) {
        int clampedSize = MathHelper.clamp(size, 0, 2);

        double speed = 0.25D;
        if (clampedSize == 0) {
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(1);
            speed = 0.5D;
        } else if (clampedSize == 2) {
            speed = 0.15D;
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(7);
        } else {
            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(4);
        }
        this.dataTracker.set(SIZE, size);
        this.refreshPosition();
        this.calculateDimensions();
        this.getAttributeInstance(EntityAttributes.GENERIC_FOLLOW_RANGE).setBaseValue(6 + clampedSize);
        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(clampedSize + 1);
        this.setHealth(this.getMaxHealth());

        this.experiencePoints = clampedSize * 2;
    }

    public void remove(RemovalReason reason) {
        int i = this.getSize();
        if (!this.world.isClient && i == 2 && this.isDead()) {
            Text text = this.getCustomName();
            boolean bl = this.isAiDisabled();
            float f = (float)i / 4.0F;
            int k = 2 + this.random.nextInt(3);

            for(int l = 0; l < k; ++l) {
                float g = ((float)(l % 2) - 0.5F) * f;
                float h = ((float)(l / 2) - 0.5F) * f;
                GoombaEntity goombaEntity = (GoombaEntity)this.getType().create(this.world);
                if (goombaEntity != null) {
                    if (this.isPersistent()) {
                        goombaEntity.setPersistent();
                    }

                    goombaEntity.setCustomName(text);
                    goombaEntity.setAiDisabled(bl);
                    goombaEntity.setInvulnerable(this.isInvulnerable());
                    goombaEntity.setSize(1);
                    goombaEntity.refreshPositionAndAngles(this.getX() + (double) g, this.getY() + 0.5D, this.getZ() + (double) h, this.random.nextFloat() * 360.0F, 0.0F);
                    this.world.spawnEntity(goombaEntity);
                }
            }
        }

        super.remove(reason);
    }

    public void calculateDimensions() {
        double d = this.getX();
        double e = this.getY();
        double f = this.getZ();
        super.calculateDimensions();
        this.setPosition(d, e, f);
    }

    public EntityDimensions getDimensions(EntityPose pose) {
        return super.getDimensions(pose).scaled(0.5F * (float)this.getSize() + 0.5F);
    }

    protected Identifier getLootTableId() {
        return this.getSize() > 0 ? this.getType().getLootTableId() : LootTables.EMPTY;
    }
}