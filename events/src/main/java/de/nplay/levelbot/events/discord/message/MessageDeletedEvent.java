package de.nplay.levelbot.events.discord.message;

import de.nplay.levelbot.events.discord.DiscordEvent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;

public final class MessageDeletedEvent extends DiscordEvent<MessageDeleteEvent> {

    private MessageDeletedEvent(MessageDeleteEvent event) {
        super(event);
    }

    /// Messages aren't cached, once deleted it cannot be retrieved. This will throw an [UnsupportedOperationException].
    @Override
    public Message message() {
        throw new UnsupportedOperationException();
    }
}
