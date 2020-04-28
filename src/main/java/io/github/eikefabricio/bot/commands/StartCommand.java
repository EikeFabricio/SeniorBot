package io.github.eikefabricio.bot.commands;

import io.github.eikefabricio.bot.api.command.DiscordCommand;
import io.github.eikefabricio.bot.controller.Messages;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

public class StartCommand extends DiscordCommand {

    private final Messages messages;

    public StartCommand(Messages messages) {
        super(Permission.MESSAGE_READ);

        this.messages = messages;
    }

    private Messages getMessages() {
        return messages;
    }

    @Override
    public void execute(GuildMessageReceivedEvent e) {
        e.getChannel().sendMessage(new EmbedBuilder()
                .setTitle("I'm ready!")
                .setDescription("Collecting the new messages...")
                .setColor(Color.WHITE)
                .build())
                .queue(it -> it.addReaction("✅").queue());

        getMessages().setMessageId(e.getMessageIdLong());
        getMessages().setActivated(true);
        getMessages().setTextChannel(e.getChannel());
    }

}
