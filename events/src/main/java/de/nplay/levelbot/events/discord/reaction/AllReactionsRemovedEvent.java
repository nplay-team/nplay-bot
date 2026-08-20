package de.nplay.levelbot.events.discord.reaction;

import de.nplay.levelbot.events.discord.DiscordEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveAllEvent;

/// All reactions of all emoji types were removed by a moderator.
public final class AllReactionsRemovedEvent extends DiscordEvent<MessageReactionRemoveAllEvent> {

    public AllReactionsRemovedEvent(MessageReactionRemoveAllEvent event) {
        super(event);
    }
}
