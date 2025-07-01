package com.jdo.tikfinity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.commands.Commands;

import java.util.Arrays;

public class CommandExecutor {
    public CommandExecutor() {
    }
    public void execute(String command) {
        if (command == null || command.trim().isEmpty()) return;

        TikfinityMod.LOGGER.error("Executing command (client): " + command);

        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;

        if (player == null || mc.level == null) {
            TikfinityMod.LOGGER.error("No player or world loaded");
            return;
        }

        String[] commands = Arrays.stream(command.split("\n"))
                .map(String::trim)
                .map(cmd -> cmd.startsWith("/") ? cmd.substring(1) : cmd)
                .toArray(String[]::new);

        for (String cmd : commands) {
            TikfinityMod.LOGGER.error("Sending command: /" + cmd);
            try {
                player.connection.sendCommand(cmd);
            } catch (Exception e) {
                TikfinityMod.LOGGER.error("Failed to send command '{}': {}", cmd, e.getMessage());
            }
        }
    }
}
