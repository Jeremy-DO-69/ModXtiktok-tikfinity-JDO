package com.jdo.tikfinity;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("tikfinity")
public class TikfinityMod {

    public static final String MOD_ID = "tikfinity";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public TikfinityMod() {
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.SPEC);
    }
}