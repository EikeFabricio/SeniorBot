package io.github.eikefabricio.bot;

import io.github.eikefabricio.bot.api.command.map.CommandMap;
import io.github.eikefabricio.bot.commands.StartCommand;
import io.github.eikefabricio.bot.controller.Messages;
import io.github.eikefabricio.bot.listener.MessageListener;
import io.github.eikefabricio.bot.listener.ReactionListener;
import net.dv8tion.jda.api.JDABuilder;

public class SeniorBot {

    public static void main(String[] args) {
        CommandMap map = new CommandMap();
        Messages messages = new Messages();

        map.register("$start", new StartCommand(messages));

        try {
            new JDABuilder()
                    .setToken("secret-token-here")
                    .addEventListeners(map.getHandler(), new ReactionListener(messages), new MessageListener(messages))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
