package com.jdo.tikfinity;

import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TikfinityMod.MOD_ID)
public class TikfinityServerEvents {

    private static CommandServer commandServer;

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        TikfinityMod.LOGGER.error("Starting Web Server on Server");
        commandServer = new CommandServer(new CommandExecutor(event.getServer()));
        commandServer.startServer(event.getServer());
    }

    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
        TikfinityMod.LOGGER.error("Stopping Web Server");
        if (commandServer != null) {
            commandServer.stopServer();
        }
    }
}
