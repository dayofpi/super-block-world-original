package com.dayofpi.super_block_world.blocks.types;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

@SuppressWarnings("deprecation")
public class MushroomTrampoline extends Block {
    public static final BooleanProperty POWERED;

    static {
        POWERED = Properties.POWERED;
    }

    public MushroomTrampoline(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(POWERED, false));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(POWERED, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos blockPos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            boolean bl = state.get(POWERED);
            if (bl != world.isReceivingRedstonePower(blockPos)) {
                if (bl) {
                    world.getBlockTickScheduler().schedule(blockPos, this, 4);
                } else {
                    world.setBlockState(blockPos, state.cycle(POWERED), Block.NOTIFY_LISTENERS);
                }
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(POWERED) && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(POWERED), Block.NOTIFY_LISTENERS);
        }

    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance, 0.2F, DamageSource.FALL);
    }

    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        if (entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity);
        } else {
            this.bounce(world, entity);
        }

    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    protected void bounce(BlockView world, Entity entity) {
        Vec3d vec3d = entity.getVelocity();
        if (vec3d.y < 0.0D && world.getBlockState(entity.getLandingPos()).get(POWERED)) {
            entity.setVelocity(vec3d.x, 0.9, vec3d.z);
        }
    }
}
