package de.nplay.levelbot.events.discord.reaction;

import de.nplay.levelbot.events.discord.DiscordEvent;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.emoji.EmojiUnion;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

/// a single emoji reaction was added to a guild message by a member
public final class ReactionAddedEvent extends DiscordEvent<MessageReactionAddEvent> {

    public ReactionAddedEvent(MessageReactionAddEvent jdaEvent) {
        super(jdaEvent);
    }

    public MessageReaction reaction() {
        return jdaEvent().getReaction();
    }

    public EmojiUnion emoji() {
        return jdaEvent().getEmoji();
    }
}
