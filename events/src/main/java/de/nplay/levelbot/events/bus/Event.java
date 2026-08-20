package de.nplay.levelbot.events.bus;

import static de.nplay.levelbot.events.bus.EventBus.EVENT_BUS;

public interface Event {

    default void publish(Event event) {
        EVENT_BUS.get().publish(event);
    }
}
