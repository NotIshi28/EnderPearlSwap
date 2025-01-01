package com.notishi28.enderpearlswap.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class EnderPearlSwapConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Double> LANDING_AREA_DISTANCE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> MODE;

    static {
        BUILDER.push("Configs for Skillcloaks");

        MODE = BUILDER.comment("How will the mod function. True = only directly hitting with an ender pearl causes the switch. False = entities near the landing area are switched too. (Default = true)")
                .define("On which mode the mod will be", true);

        LANDING_AREA_DISTANCE = BUILDER.comment("How far in blocks do entities need to be from the pearl to be teleported (Default = 1.1)")
                .define("Distance from pearl", 1.1);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
