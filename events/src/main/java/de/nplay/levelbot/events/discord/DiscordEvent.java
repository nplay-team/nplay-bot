package de.nplay.levelbot.events.discord;

import de.nplay.levelbot.events.bus.Event;
import de.nplay.levelbot.events.discord.message.MessageDeletedEvent;
import de.nplay.levelbot.events.discord.message.MessageReceivedEvent;
import de.nplay.levelbot.events.discord.reaction.AllReactionsRemovedEvent;
import de.nplay.levelbot.events.discord.reaction.ReactionAddedEvent;
import de.nplay.levelbot.events.discord.reaction.ReactionTypeRemovedEvent;
import de.nplay.levelbot.events.discord.reaction.SingleReactionRemovedEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;

/// A generic message related discord event. Subtypes are:
///
/// - [MessageReceivedEvent]
/// - [MessageDeletedEvent]
/// - [ReactionAddedEvent]
/// - [SingleReactionRemovedEvent]
/// - [ReactionTypeRemovedEvent]
/// - [AllReactionsRemovedEvent]
///
/// These events are the basis of many bot features. These events are ensured to be from a guild and also not from a
/// webhook. Thus, methods like [#member()] are always safe to call and should never produce an exception.
///
/// Use [#publish(Event)] to publish additional bot events based from this discord event.
///
/// @implNote because all events the bot reacts to are message based, the super type of the event type [T] is
/// [GenericMessageEvent].
@SuppressWarnings("preview")
public abstract sealed class DiscordEvent<T extends GenericMessageEvent>
        extends GenericMessageEvent
        implements Event
        permits MessageDeletedEvent, MessageReceivedEvent, AllReactionsRemovedEvent, ReactionAddedEvent,
        ReactionTypeRemovedEvent, SingleReactionRemovedEvent {

    // allow lazy loading of these fields
    private final StableValue<Member> member = StableValue.of();
    private final StableValue<Message> message = StableValue.of();
    private final T event;

    protected DiscordEvent(T event) {
        // check again, just in case. JDAEventListener should never allow this to throw.
        if (!event.isFromGuild()) {
            throw new IllegalStateException("Invalid event data! Event is not from a guild");
        }
        super(event.getJDA(), event.getResponseNumber(), event.getMessageIdLong(), event.getChannel());
        this.event = event;
    }

    public Guild guild() {
        return jdaEvent().getGuild();
    }

    public GuildMessageChannel channel() {
        return jdaEvent().getChannel().asGuildMessageChannel();
    }

    public Message message() {
        return message.orElseSet(() -> channel.retrieveMessageById(jdaEvent().getMessageId()).complete());
    }

    /// The member that fired the message event
    public Member member() {
        return member.orElseSet(() -> guild().retrieveMember(author()).complete());
    }

    /// The author of the message of this event
    public User author() {
        return message().getAuthor();
    }

    public T jdaEvent() {
        return event;
    }
}
