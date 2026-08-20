package de.nplay.levelbot.events.discord.reaction;

import de.nplay.levelbot.events.discord.DiscordEvent;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.emoji.EmojiUnion;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;

/// A single reaction was removed by the member that initially added the reaction
public final class SingleReactionRemovedEvent extends DiscordEvent<MessageReactionRemoveEvent> {

    public SingleReactionRemovedEvent(MessageReactionRemoveEvent event) {
        super(event);
    }

    public MessageReaction reaction() {
        return jdaEvent().getReaction();
    }

    public EmojiUnion emoji() {
        return jdaEvent().getEmoji();
    }

}
