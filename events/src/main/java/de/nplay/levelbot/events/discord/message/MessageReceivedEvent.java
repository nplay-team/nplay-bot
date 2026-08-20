package de.nplay.levelbot.events.discord.message;

import de.nplay.levelbot.events.discord.DiscordEvent;
import net.dv8tion.jda.api.entities.Message;

public final class MessageReceivedEvent extends DiscordEvent<net.dv8tion.jda.api.events.message.MessageReceivedEvent> {

    public MessageReceivedEvent(net.dv8tion.jda.api.events.message.MessageReceivedEvent jdaEvent) {
        super(jdaEvent);
    }

    @Override
    public Message message() {
        return jdaEvent().getMessage();
    }
}
