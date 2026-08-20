package de.nplay.levelbot.events.bus;

/// A [Subscription] can be used to remove a [Subscriber] from an [Event] type.
public final class Subscription {

    private final Class<? extends Event> eventType;
    private final EventBus eventBus;
    private final Subscriber<?> subscriber;

    Subscription(Class<? extends Event> eventType, Subscriber<?> subscriber, EventBus eventBus) {
        this.eventType = eventType;
        this.eventBus = eventBus;
        this.subscriber = subscriber;
    }

    /// Terminates (unsubscribes) this [Subscription]. The associated [Subscriber] won't be called in the future.
    public void unsubscribe() {
        eventBus.unsubscribe(subscriber, eventType);
    }
}
