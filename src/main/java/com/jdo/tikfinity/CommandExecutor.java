package com.jdo.tikfinity;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.commands.Commands;

import java.util.Arrays;

public class CommandExecutor {

    private final MinecraftServer server;

    public CommandExecutor(MinecraftServer server) {
        this.server = server;
    }

    public void execute(String command) {
        if (command == null || command.trim().isEmpty()) return;
        TikfinityMod.LOGGER.error(command);
        PlayerList playerList = server.getPlayerList();
        if (playerList.getPlayers().isEmpty()) return;

        ServerPlayer player = playerList.getPlayers().get(0);
        if (player == null) return;

        CommandSourceStack commandSource = player.createCommandSourceStack();
        Commands commandDispatcher = server.getCommands();

        String[] commands = Arrays.stream(command.split("\n"))
                .map(String::trim)
                .map(cmd -> cmd.startsWith("/") ? cmd.substring(1) : cmd)
                .toArray(String[]::new);

        for (String cmd : commands) {
            try {
                commandDispatcher.performPrefixedCommand(commandSource, cmd);
            } catch (Exception e) {
                TikfinityMod.LOGGER.error("Failed to execute command '{}': {}", cmd, e.getMessage());
            }
        }
    }
}
