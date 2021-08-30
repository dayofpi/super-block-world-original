package com.dayofpi.super_block_world.misc;

import com.dayofpi.super_block_world.Main;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundList {

    // Identifiers
    protected static final Identifier ITEM_IN_ID = new Identifier(Main.MOD_ID, "block.item_in");
    protected static final Identifier ITEM_OUT_ID = new Identifier(Main.MOD_ID, "block.item_out");
    protected static final Identifier COIN_ID = new Identifier(Main.MOD_ID, "block.coin");
    protected static final Identifier TRAMPOLINE_ID = new Identifier(Main.MOD_ID, "block.trampoline");
    protected static final Identifier POWERED_TRAMPOLINE_ID = new Identifier(Main.MOD_ID, "block.powered_trampoline");
    protected static final Identifier BUZZY_BEETLE_HURT_ID = new Identifier(Main.MOD_ID, "entity.buzzy_beetle.hurt");
    protected static final Identifier BUZZY_BEETLE_DEATH_ID = new Identifier(Main.MOD_ID, "entity.buzzy_beetle.death");
    protected static final Identifier BUZZY_BEETLE_DROP_ID = new Identifier(Main.MOD_ID, "entity.buzzy_beetle.drop");
    protected static final Identifier BUZZY_BEETLE_LAND_ID = new Identifier(Main.MOD_ID, "entity.buzzy_beetle.land");
    protected static final Identifier BUZZY_BEETLE_BLOCK_ID = new Identifier(Main.MOD_ID, "entity.buzzy_beetle.block");

    // Events
    public static final SoundEvent ITEM_IN = new SoundEvent(ITEM_IN_ID);
    public static final SoundEvent ITEM_OUT = new SoundEvent(ITEM_OUT_ID);
    public static final SoundEvent COIN = new SoundEvent(COIN_ID);
    public static final SoundEvent TRAMPOLINE = new SoundEvent(TRAMPOLINE_ID);
    public static final SoundEvent POWERED_TRAMPOLINE = new SoundEvent(POWERED_TRAMPOLINE_ID);
    public static final SoundEvent BUZZY_BEETLE_HURT = new SoundEvent(BUZZY_BEETLE_HURT_ID);
    public static final SoundEvent BUZZY_BEETLE_DEATH = new SoundEvent(BUZZY_BEETLE_DEATH_ID);
    public static final SoundEvent BUZZY_BEETLE_DROP = new SoundEvent(BUZZY_BEETLE_DROP_ID);
    public static final SoundEvent BUZZY_BEETLE_LAND = new SoundEvent(BUZZY_BEETLE_LAND_ID);
    public static final SoundEvent BUZZY_BEETLE_BLOCK = new SoundEvent(BUZZY_BEETLE_BLOCK_ID);

    public static void registerSounds() {
        Registry.register(Registry.SOUND_EVENT, ITEM_IN_ID, ITEM_IN);
        Registry.register(Registry.SOUND_EVENT, ITEM_OUT_ID, ITEM_OUT);
        Registry.register(Registry.SOUND_EVENT, COIN_ID, COIN);
        Registry.register(Registry.SOUND_EVENT, TRAMPOLINE_ID, TRAMPOLINE);
        Registry.register(Registry.SOUND_EVENT, POWERED_TRAMPOLINE_ID, POWERED_TRAMPOLINE);
        Registry.register(Registry.SOUND_EVENT, BUZZY_BEETLE_HURT_ID, BUZZY_BEETLE_HURT);
        Registry.register(Registry.SOUND_EVENT, BUZZY_BEETLE_DEATH_ID, BUZZY_BEETLE_DEATH);
        Registry.register(Registry.SOUND_EVENT, BUZZY_BEETLE_DROP_ID, BUZZY_BEETLE_DROP);
        Registry.register(Registry.SOUND_EVENT, BUZZY_BEETLE_LAND_ID, BUZZY_BEETLE_LAND);
        Registry.register(Registry.SOUND_EVENT, BUZZY_BEETLE_BLOCK_ID, BUZZY_BEETLE_BLOCK);
    }
}