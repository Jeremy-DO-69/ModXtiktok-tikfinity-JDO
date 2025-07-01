package com.jdo.tikfinity;

import net.minecraft.client.Minecraft;

public class TikfinityClient {

    private static CommandServer commandServer; // ou ton propre serveur HTTP/WebSocket

    public static void initWebServer() {
        if (commandServer == null) {
            commandServer = new CommandServer(new CommandExecutor());
            commandServer.startServer();
        }
    }

    public static void stopWebServer() {
        if (commandServer != null) {
            commandServer.stopServer();
            commandServer = null;
        }
    }
}
