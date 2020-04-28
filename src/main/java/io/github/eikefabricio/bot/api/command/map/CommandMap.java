package io.github.eikefabricio.bot.api.command.map;

import io.github.eikefabricio.bot.api.command.DiscordCommand;
import io.github.eikefabricio.bot.api.command.map.handler.CommandHandler;

import java.util.HashMap;
import java.util.Map;

public class CommandMap {

    private final CommandHandler handler;
    private final Map<String, DiscordCommand> commandMap;

    public CommandMap() {
        this.handler = new CommandHandler(this);
        this.commandMap = new HashMap<>();
    }

    public CommandHandler getHandler() {
        return handler;
    }

    private Map<String, DiscordCommand> getCommandMap() {
        return commandMap;
    }

    public void register(String commandKey, DiscordCommand discordCommand) {
        commandMap.put(commandKey, discordCommand);
    }

    public boolean isCommand(String commandKey) {
        return commandKey.startsWith("$");
    }

    public boolean isCommandKey(String commandKey) {
        return commandMap.containsKey(commandKey);
    }

    public DiscordCommand of(String commandKey) {
        return commandMap.get(commandKey);
    }

}
