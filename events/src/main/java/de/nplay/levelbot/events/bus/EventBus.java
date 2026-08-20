package de.nplay.levelbot.events.bus;

import de.nplay.levelbot.events.discord.DiscordEvent;
import de.nplay.levelbot.events.discord.internal.JDAEventListener;
import net.dv8tion.jda.api.JDA;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/// The event bus publishes events to its subscribers. Will automatically register a JDA listener to publish
/// [DiscordEvent]s.
public class EventBus {

    static final ScopedValue<EventBus> EVENT_BUS = ScopedValue.newInstance();
    private final Map<Class<? extends Event>, Set<Subscriber<Event>>> subscriptions
            = new ConcurrentHashMap<>();

    // TODO use Guice
    public EventBus(JDA jda) {
        jda.addEventListener(new JDAEventListener(this));
    }

    @SuppressWarnings("unchecked")
    public <T extends Event> Subscription subscribe(Class<T> eventType, Subscriber<T> subscriber) {
        subscriptions.computeIfAbsent(
                eventType,
                _ -> ConcurrentHashMap.newKeySet()
        ).add((Subscriber<Event>) subscriber);
        return new Subscription(eventType, subscriber, this);
    }

    public void unsubscribe(Subscriber<?> subscriber, Class<? extends Event> eventType) {
        subscriptions.get(eventType).remove(subscriber);
    }

    public void publish(Event event) {
        ScopedValue.where(EVENT_BUS, this).run(() -> {
            subscriptions.keySet().stream()
                    .filter(it -> it.isAssignableFrom(event.getClass()))
                    .map(subscriptions::get)
                    .flatMap(Set::stream)
                    .forEach(it -> it.accept(event));
        });
    }
}
