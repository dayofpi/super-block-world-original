package com.dayofpi.super_block_world.blocks;

import com.dayofpi.super_block_world.Main;
import com.dayofpi.super_block_world.blocks.types.*;
import com.dayofpi.super_block_world.blocks.types.template.*;
import com.dayofpi.super_block_world.misc.worldgen.FeatureReg;
import com.dayofpi.super_block_world.misc.worldgen.features.AmanitaTree;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

import java.util.function.ToIntFunction;

public class BlockTypes {
    public static final Block TOADSTOOL_GRASS = new ToadstoolGrass(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.LIME).strength(0.5F, 4.0F).sounds(BlockSoundGroup.GRASS));
    public static final Block TOADSTOOL_SOIL = new ToadstoolSoil(FabricBlockSettings.of(Material.SOIL, MapColor.TERRACOTTA_ORANGE).strength(0.5F, 4.0F).sounds(BlockSoundGroup.GRAVEL));
    public static final Block TOADSTOOL_FARMLAND = new ToadstoolFarmland(FabricBlockSettings.copyOf(TOADSTOOL_SOIL).ticksRandomly());
    public static final Block TOADSTOOL_PATH = new ToadstoolPath(FabricBlockSettings.copyOf(TOADSTOOL_SOIL).mapColor(MapColor.BROWN));
    public static final Block VANILLATE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_LIGHT_BLUE).requiresTool().strength(1.2F, 6.0F));
    public static final Block VANILLATE_BRICKS = new Block(FabricBlockSettings.copyOf(VANILLATE));
    public static final Block VANILLATE_CRUMBLE = new ModFallingBlock(FabricBlockSettings.copyOf(VANILLATE).mapColor(MapColor.WHITE_GRAY));
    public static final Block VANILLATE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(VANILLATE));
    public static final Block VANILLATE_STAIRS = new StairsBlock(VANILLATE.getDefaultState(), FabricBlockSettings.copyOf(VANILLATE)){};
    public static final Block VANILLATE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(VANILLATE_BRICKS));
    public static final Block VANILLATE_BRICK_STAIRS = new StairsBlock(VANILLATE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(VANILLATE_BRICKS)){};
    public static final Block TOPPED_VANILLATE = new Block(FabricBlockSettings.copyOf(VANILLATE).mapColor(MapColor.WHITE_GRAY));
    public static final Block COAL_TOPPED_VANILLATE = new OreBlock(FabricBlockSettings.copyOf(TOPPED_VANILLATE).strength(1.5F, 6.0F), UniformIntProvider.create(0, 1));
    public static final Block IRON_TOPPED_VANILLATE = new OreBlock(FabricBlockSettings.copyOf(TOPPED_VANILLATE).strength(1.5F, 6.0F), UniformIntProvider.create(0, 1));
    public static final Block GOLD_TOPPED_VANILLATE = new OreBlock(FabricBlockSettings.copyOf(TOPPED_VANILLATE).strength(1.5F, 6.0F), UniformIntProvider.create(0, 1));
    public static final Block BRONZE_ORE = new Block(FabricBlockSettings.copyOf(VANILLATE).strength(1.5F, 6.0F));
    public static final Block GLOOMSTONE_BRONZE_ORE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.DARK_AQUA).requiresTool().strength(1.5F, 1.0F).sounds(BlockSoundGroup.DEEPSLATE));
    public static final Block RAW_BRONZE_BLOCK = new Block(FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_ORANGE).requiresTool().strength(5.0F, 6.0F));
    public static final Block BRONZE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MapColor.TERRACOTTA_ORANGE).requiresTool().strength(5.0F, 8.0F));
    public static final Block BRONZE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(BRONZE_BLOCK));
    public static final Block BRONZE_STAIRS = new StairsBlock(BRONZE_BLOCK.getDefaultState(), FabricBlockSettings.copyOf(BRONZE_BLOCK)){};
    public static final Block QUESTION_BLOCK = new QuestionBlock(FabricBlockSettings.of(Material.METAL, MapColor.YELLOW).requiresTool().strength(3.0F, 200.0F).luminance(3));
    public static final Block COIN_BLOCK = new CoinBlock(FabricBlockSettings.of(Material.METAL, MapColor.GOLD).requiresTool().strength(3.0F, 200.0F).luminance(3));
    public static final Block EMPTY_BLOCK = new EmptyBlock(FabricBlockSettings.of(Material.METAL, MapColor.SPRUCE_BROWN).requiresTool().strength(3.0F, 200.0F));
    public static final Block DONUT_BLOCK = new DonutBlock(FabricBlockSettings.copyOf(TOADSTOOL_SOIL).strength(0.1F).nonOpaque());
    public static final Block TOADSTONE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_ORANGE).requiresTool().strength(1.2F, 1.0F));
    public static final Block TOADSTONE_BRICKS = new Block(FabricBlockSettings.copyOf(TOADSTONE));
    public static final Block TOADSTONE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(TOADSTONE));
    public static final Block TOADSTONE_STAIRS = new StairsBlock(TOADSTONE.getDefaultState(), FabricBlockSettings.copyOf(TOADSTONE)){};
    public static final Block GLOOMSTONE = new Block(FabricBlockSettings.copyOf(GLOOMSTONE_BRONZE_ORE).strength(1.2F, 1.0F));
    public static final Block GLOOMSTONE_BRICKS = new Block(FabricBlockSettings.copyOf(GLOOMSTONE).sounds(BlockSoundGroup.DEEPSLATE_BRICKS));
    public static final Block GLOOMSTONE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(GLOOMSTONE));
    public static final Block GLOOMSTONE_STAIRS = new StairsBlock(GLOOMSTONE.getDefaultState(), FabricBlockSettings.copyOf(GLOOMSTONE)){};
    public static final Block GOLDEN_BRICKS = new Block(FabricBlockSettings.copyOf(TOADSTONE_BRICKS).sounds(BlockSoundGroup.METAL).mapColor(MapColor.GOLD));
    public static final Block CRYSTAL_BRICKS = new Block(FabricBlockSettings.copyOf(TOADSTONE_BRICKS).sounds(BlockSoundGroup.AMETHYST_BLOCK).mapColor(MapColor.PURPLE));
    public static final Block AMANITA_LOG = new WoodBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block AMANITA_WOOD = new WoodBlock(FabricBlockSettings.copyOf(AMANITA_LOG));
    public static final Block STRIPPED_AMANITA_LOG = new PillarBlock(FabricBlockSettings.copyOf(AMANITA_LOG));
    public static final Block STRIPPED_AMANITA_WOOD = new PillarBlock(FabricBlockSettings.copyOf(AMANITA_LOG).mapColor(MapColor.TERRACOTTA_YELLOW));
    public static final Block AMANITA_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block AMANITA_SLAB = new SlabBlock(FabricBlockSettings.copyOf(AMANITA_PLANKS));
    public static final Block AMANITA_STAIRS = new StairsBlock(AMANITA_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(AMANITA_PLANKS)){};
    public static final Block AMANITA_DOOR = new DoorBlock(FabricBlockSettings.copyOf(AMANITA_PLANKS).strength(3.0F).nonOpaque()){};
    public static final Block AMANITA_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copyOf(AMANITA_PLANKS).strength(3.0F).nonOpaque().allowsSpawning(BlockTypes::never)){};
    public static final Block AMANITA_SIGN = new ModSign(FabricBlockSettings.copyOf(AMANITA_PLANKS).strength(1.0F).noCollision(), ModSignType.AMANITA);
    public static final Block AMANITA_WALL_SIGN = new ModWallSign(FabricBlockSettings.copyOf(AMANITA_PLANKS).strength(1.0F).noCollision(), ModSignType.AMANITA);
    public static final Block AMANITA_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(BlockTypes::canSpawnOnLeaves).suffocates(BlockTypes::never).blockVision(BlockTypes::never));
    public static final Block FRUITING_AMANITA_LEAVES = new LeavesBlock(FabricBlockSettings.copyOf(AMANITA_LEAVES));
    public static final Block AMANITA_SAPLING = new SaplingBlock(new AmanitaTree(), FabricBlockSettings.of(Material.PLANT, MapColor.YELLOW).noCollision().ticksRandomly().sounds(BlockSoundGroup.GRASS)){};
    public static final Block HORSETAIL = new HorsetailPlant(FabricBlockSettings.of(Material.PLANT).nonOpaque().noCollision().sounds(BlockSoundGroup.GRASS));
    public static final Block MUSHROOM_STEM = new PillarBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0F).sounds(BlockSoundGroup.NETHER_STEM));
    public static final Block BROWN_MUSHROOM_CAP = new MushroomCap(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.DIRT_BROWN).strength(1.0F).sounds(BlockSoundGroup.WART_BLOCK));
    public static final Block RED_MUSHROOM_CAP = new MushroomCap(FabricBlockSettings.copyOf(BROWN_MUSHROOM_CAP).mapColor(MapColor.RED));
    public static final Block YELLOW_MUSHROOM_CAP = new MushroomCap(FabricBlockSettings.copyOf(BROWN_MUSHROOM_CAP).mapColor(MapColor.YELLOW));
    public static final Block GREEN_MUSHROOM_CAP = new MushroomCap(FabricBlockSettings.copyOf(BROWN_MUSHROOM_CAP).mapColor(MapColor.EMERALD_GREEN));
    public static final Block PINK_MUSHROOM_CAP = new MushroomCap(FabricBlockSettings.copyOf(BROWN_MUSHROOM_CAP).mapColor(MapColor.PINK));
    public static final Block PURPLE_MUSHROOM_CAP = new MushroomCap(FabricBlockSettings.copyOf(BROWN_MUSHROOM_CAP).mapColor(MapColor.PALE_PURPLE));
    public static final Block ORANGE_MUSHROOM_CAP = new MushroomCap(FabricBlockSettings.copyOf(BROWN_MUSHROOM_CAP).mapColor(MapColor.ORANGE));
    public static final Block YELLOW_MUSHROOM = new MushroomPlantBlock(FabricBlockSettings.of(Material.PLANT, MapColor.YELLOW).noCollision().ticksRandomly().sounds(BlockSoundGroup.GRASS), () -> FeatureReg.HUGE_YELLOW_MUSHROOM);
    public static final Block GREEN_MUSHROOM = new MushroomPlantBlock(FabricBlockSettings.copyOf(YELLOW_MUSHROOM).mapColor(MapColor.EMERALD_GREEN).luminance(2), () -> FeatureReg.HUGE_GREEN_MUSHROOM);
    public static final Block PINK_MUSHROOM = new MushroomPlantBlock(FabricBlockSettings.copyOf(YELLOW_MUSHROOM).mapColor(MapColor.PINK), () -> FeatureReg.HUGE_PINK_MUSHROOM_WIDE);
    public static final Block PURPLE_MUSHROOM = new MushroomPlantBlock(FabricBlockSettings.copyOf(YELLOW_MUSHROOM).mapColor(MapColor.PALE_PURPLE), () -> FeatureReg.HUGE_PURPLE_MUSHROOM_WIDE);
    public static final Block ORANGE_MUSHROOM = new MushroomPlantBlock(FabricBlockSettings.copyOf(YELLOW_MUSHROOM).mapColor(MapColor.ORANGE), () -> FeatureReg.HUGE_ORANGE_MUSHROOM_WIDE);
    public static final Block YELLOW_FLOWERBED = new FlowerbedBlock(FabricBlockSettings.of(Material.PLANT, MapColor.YELLOW).noCollision().sounds(BlockSoundGroup.MOSS_CARPET));
    public static final Block HARDSTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE, MapColor.LIGHT_GRAY).requiresTool().strength(4.0F, 10.0F));
    public static final Block CRACKED_HARDSTONE_BRICKS = new Block(FabricBlockSettings.copyOf(HARDSTONE_BRICKS).strength(2.0F, 5.0F));
    public static final Block REDSTONE_TRAMPOLINE = new MushroomTrampoline(FabricBlockSettings.of(Material.SOLID_ORGANIC, MapColor.GRAY).strength(1.0F).sounds(BlockSoundGroup.WART_BLOCK));
    public static final Block WARP_FRAME = new PillarBlock(FabricBlockSettings.of(Material.METAL, MapColor.GOLD).requiresTool().strength(3.0F, 200F));
    public static final Block STONE_TORCH = new StoneTorch(FabricBlockSettings.copyOf(VANILLATE).nonOpaque().luminance(createLightLevelFromLitBlockState(15)));
    public static final Block POISON = new ModFluidBlock(Main.STILL_POISON, FabricBlockSettings.of(Material.LAVA, MapColor.PURPLE).noCollision().ticksRandomly().strength(100.0F).luminance(7));

    private static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    private static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    private static boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return false;
    }

    private static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
        return (state) -> (Boolean) state.get(Properties.LIT) ? litLevel : 0;
    }

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "toadstool_grass"), TOADSTOOL_GRASS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "toadstool_soil"), TOADSTOOL_SOIL);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "toadstool_farmland"), TOADSTOOL_FARMLAND);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "toadstool_path"), TOADSTOOL_PATH);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "vanillate"), VANILLATE);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "vanillate_bricks"), VANILLATE_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "vanillate_crumble"), VANILLATE_CRUMBLE);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "vanillate_slab"), VANILLATE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "vanillate_stairs"), VANILLATE_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "vanillate_brick_slab"), VANILLATE_BRICK_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "vanillate_brick_stairs"), VANILLATE_BRICK_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "topped_vanillate"), TOPPED_VANILLATE);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "coal_topped_vanillate"), COAL_TOPPED_VANILLATE);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "iron_topped_vanillate"), IRON_TOPPED_VANILLATE);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "gold_topped_vanillate"), GOLD_TOPPED_VANILLATE);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "bronze_ore"), BRONZE_ORE);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "gloomstone_bronze_ore"), GLOOMSTONE_BRONZE_ORE);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "raw_bronze_block"), RAW_BRONZE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "bronze_block"), BRONZE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "bronze_slab"), BRONZE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "bronze_stairs"), BRONZE_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "question_block"), QUESTION_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "coin_block"), COIN_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "empty_block"), EMPTY_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "donut_block"), DONUT_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "toadstone"), TOADSTONE);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "toadstone_bricks"), TOADSTONE_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "gloomstone"), GLOOMSTONE);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "gloomstone_bricks"), GLOOMSTONE_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "golden_bricks"), GOLDEN_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "crystal_bricks"), CRYSTAL_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "toadstone_slab"), TOADSTONE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "toadstone_stairs"), TOADSTONE_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "gloomstone_slab"), GLOOMSTONE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "gloomstone_stairs"), GLOOMSTONE_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "amanita_log"), AMANITA_LOG);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "amanita_wood"), AMANITA_WOOD);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "stripped_amanita_log"), STRIPPED_AMANITA_LOG);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "stripped_amanita_wood"), STRIPPED_AMANITA_WOOD);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "amanita_planks"), AMANITA_PLANKS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "amanita_slab"), AMANITA_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "amanita_stairs"), AMANITA_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "amanita_door"), AMANITA_DOOR);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "amanita_trapdoor"), AMANITA_TRAPDOOR);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "amanita_sign"), AMANITA_SIGN);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "amanita_wall_sign"), AMANITA_WALL_SIGN);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "amanita_leaves"), AMANITA_LEAVES);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "fruiting_amanita_leaves"), FRUITING_AMANITA_LEAVES);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "amanita_sapling"), AMANITA_SAPLING);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "horsetail"), HORSETAIL);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "mushroom_stem"), MUSHROOM_STEM);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "brown_mushroom_cap"), BROWN_MUSHROOM_CAP);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "red_mushroom_cap"), RED_MUSHROOM_CAP);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "yellow_mushroom_cap"), YELLOW_MUSHROOM_CAP);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "green_mushroom_cap"), GREEN_MUSHROOM_CAP);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "pink_mushroom_cap"), PINK_MUSHROOM_CAP);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "purple_mushroom_cap"), PURPLE_MUSHROOM_CAP);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "orange_mushroom_cap"), ORANGE_MUSHROOM_CAP);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "yellow_mushroom"), YELLOW_MUSHROOM);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "green_mushroom"), GREEN_MUSHROOM);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "pink_mushroom"), PINK_MUSHROOM);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "purple_mushroom"), PURPLE_MUSHROOM);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "orange_mushroom"), ORANGE_MUSHROOM);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "yellow_flowerbed"), YELLOW_FLOWERBED);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "hardstone_bricks"), HARDSTONE_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "cracked_hardstone_bricks"), CRACKED_HARDSTONE_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "warp_frame"), WARP_FRAME);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "stone_torch"), STONE_TORCH);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "redstone_trampoline"), REDSTONE_TRAMPOLINE);
        Registry.register(Registry.BLOCK, new Identifier(Main.MOD_ID, "poison"), POISON);
        BlockEntities.registerBlockEntities();
    }
}