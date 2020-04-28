package io.github.eikefabricio.bot.listener;

import io.github.eikefabricio.bot.controller.Messages;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class ReactionListener extends ListenerAdapter {

    private final Messages messages;

    public ReactionListener(Messages messages) {
        this.messages = messages;
    }

    public Messages getMessages() {
        return messages;
    }

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {
        if (!getMessages().isActivated()) return;
        if (event.getMessageId().equals("" + getMessages().getMessageId())) return;
        if (event.getMember().getUser().isBot()) return;

        event.getReaction().removeReaction().queue();

        event.getChannel().sendMessage(new EmbedBuilder()
                .setTitle("Stopping...")
                .setDescription("Stopping the messages collecting...")
                .build()).queue();

        getMessages().getMessages().forEach(message ->
            message.getChannel().sendMessage("Message received by " + message.getAuthor().getAsMention() + " > " + message.getContentRaw()).queue()
        );

        getMessages().setActivated(false);
    }

}
