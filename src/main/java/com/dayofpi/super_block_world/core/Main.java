package com.dayofpi.super_block_world.core;

import com.dayofpi.super_block_world.core.utility.FeatureHelper;
import com.dayofpi.super_block_world.core.utility.ModSounds;
import com.dayofpi.super_block_world.core.utility.ModTags;
import com.dayofpi.super_block_world.core.utility.fluids.PoisonFluid;
import com.dayofpi.super_block_world.core.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.kyrptonaught.customportalapi.portal.PortalIgnitionSource;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.FluidModificationItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class Main implements ModInitializer {
    // Mod ID
    public static final String MOD_ID = "super_block_world";
    // Item group
    public static final ItemGroup MOD_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "item_group"), () -> new ItemStack(ItemReg.POWER_STAR));

    public static final FlowableFluid STILL_POISON = new PoisonFluid.Still();
    public static final FlowableFluid FLOWING_POISON = new PoisonFluid.Flowing();

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        Registry.register(Registry.FLUID, new Identifier(MOD_ID, "poison"), STILL_POISON);
        Registry.register(Registry.FLUID, new Identifier(MOD_ID, "flowing_poison"), FLOWING_POISON);
        DispenserBehavior dispenserBehavior = new ItemDispenserBehavior() {
            private final ItemDispenserBehavior fallbackBehavior = new ItemDispenserBehavior();

            public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                FluidModificationItem fluidModificationItem = (FluidModificationItem) stack.getItem();
                BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                World world = pointer.getWorld();
                if (fluidModificationItem.placeFluid(null, world, blockPos, null)) {
                    fluidModificationItem.onEmptied(null, world, stack, blockPos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.fallbackBehavior.dispense(pointer, stack);
                }
            }
        };
        DispenserBlock.registerBehavior(ItemReg.POISON_BUCKET, dispenserBehavior);
        BlockReg.registerBlocks();
        BlockEntities.registerBlockEntities();
        ItemReg.registerItems();
        EntityReg.registerEntities();
        FeatureReg.registerFeatures();
        FeatureHelper.registerFeatures();
        ModTags.registerTags();
        ModSounds.registerSounds();
        CustomPortalApiRegistry.addPortal(BlockReg.WARP_FRAME, PortalIgnitionSource.ItemUseSource(ItemReg.POWER_STAR), new Identifier(MOD_ID, "mushroom_kingdom"), 230, 200, 224);
    }
}