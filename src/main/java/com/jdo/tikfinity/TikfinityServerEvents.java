package com.jdo.tikfinity;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TikfinityMod.MOD_ID)
public class TikfinityServerEvents {

    private static boolean serverStarted = false;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();

        if (!serverStarted && mc.level != null && mc.player != null) {
            TikfinityMod.LOGGER.info("🔌 Starting HTTP/WebSocket server on CLIENT for player: " + mc.player.getName().getString());

            TikfinityClient.initWebServer(); // 🔥 Lancement du serveur ici

            serverStarted = true;
        }

        if (mc.level == null && serverStarted) {
            TikfinityMod.LOGGER.info("🛑 Stopping HTTP/WebSocket server (client disconnected)");
            TikfinityClient.stopWebServer();

            serverStarted = false;
        }
    }
}
