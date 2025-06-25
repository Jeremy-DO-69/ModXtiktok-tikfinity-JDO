package com.jdo.tikfinity;


import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.IntValue PORT;

    static {
        BUILDER.push("General");

        PORT = BUILDER
                .comment("The port used by Tikfinity")
                .defineInRange("port", 4567, 1, 65535);

        BUILDER.pop();
    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}