package de.nplay.levelbot.events.bus;

/// Functional interface for subscribing to [Event]s of the bot internal event system.
@FunctionalInterface
public interface Subscriber<T extends Event> {

    void accept(T event);

}
