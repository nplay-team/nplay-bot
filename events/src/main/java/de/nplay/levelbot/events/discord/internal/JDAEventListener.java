package de.nplay.levelbot.events.discord.internal;

import de.nplay.levelbot.events.bus.EventBus;
import de.nplay.levelbot.events.discord.message.MessageReceivedEvent;
import de.nplay.levelbot.events.discord.reaction.AllReactionsRemovedEvent;
import de.nplay.levelbot.events.discord.reaction.ReactionAddedEvent;
import de.nplay.levelbot.events.discord.reaction.ReactionTypeRemovedEvent;
import de.nplay.levelbot.events.discord.reaction.SingleReactionRemovedEvent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEmojiEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/// Listens to JDA events and wraps them in the internal bot events.
public class JDAEventListener extends ListenerAdapter {

    private final EventBus eventBus;

    public JDAEventListener(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onGenericMessage(GenericMessageEvent genericEvent) {
        // the bot doesn't interact with private messages
        if (!genericEvent.isFromGuild()) {
            return;
        }

        // also webhook messages should not interact with the bot
        // the message retrieve should never throw because we request the second the message event was received
        Message message = genericEvent.getChannel().retrieveMessageById(genericEvent.getMessageId()).complete();
        if (message.isWebhookMessage()) {
            return;
        }

        switch (genericEvent) {
            case net.dv8tion.jda.api.events.message.MessageReceivedEvent event ->
                    eventBus.publish(new MessageReceivedEvent(event));
            case MessageReactionAddEvent event -> eventBus.publish(new ReactionAddedEvent(event));
            case MessageReactionRemoveEvent event -> eventBus.publish(new SingleReactionRemovedEvent(event));
            case MessageReactionRemoveEmojiEvent event -> eventBus.publish(new ReactionTypeRemovedEvent(event));
            case MessageReactionRemoveAllEvent event -> eventBus.publish(new AllReactionsRemovedEvent(event));
            default -> {
            }
        }
    }
}
