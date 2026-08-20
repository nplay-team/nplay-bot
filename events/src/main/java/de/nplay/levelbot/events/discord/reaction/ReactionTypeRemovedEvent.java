package de.nplay.levelbot.events.discord.reaction;

import de.nplay.levelbot.events.discord.DiscordEvent;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.emoji.EmojiUnion;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEmojiEvent;

/// All reactions of an emoji type were removed by a moderator.
public final class ReactionTypeRemovedEvent extends DiscordEvent<MessageReactionRemoveEmojiEvent> {

    public ReactionTypeRemovedEvent(MessageReactionRemoveEmojiEvent event) {
        super(event);
    }

    public MessageReaction reaction() {
        return jdaEvent().getReaction();
    }

    public EmojiUnion emoji() {
        return jdaEvent().getEmoji();
    }
}
