package com.notishi28.enderpearlswap;

import com.notishi28.enderpearlswap.config.EnderPearlSwapConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EnderPearlSwap.MOD_ID)
public class EnderPearlSwap {
    public static final String MOD_ID = "enderpearlswap";

    public EnderPearlSwap() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EnderPearlSwapConfig.SPEC, "enderpearlswap-common.toml");
    }
}
