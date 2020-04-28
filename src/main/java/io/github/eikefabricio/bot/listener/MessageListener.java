package io.github.eikefabricio.bot.listener;

import io.github.eikefabricio.bot.controller.Messages;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class MessageListener extends ListenerAdapter {

    private final Messages messages;

    public MessageListener(Messages messages) {
        this.messages = messages;
    }

    public Messages getMessages() {
        return messages;
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if (getMessages().getTextChannel() == null) return;
        if (event.getMember().getUser().isBot()) return;
        if (!event.getChannel().getId().equals(getMessages().getTextChannel().getId())) return;
        if (!getMessages().isActivated()) return;

        getMessages().add(event.getMessage());
    }
}
