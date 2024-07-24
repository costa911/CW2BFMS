package com.example.cw2bfms;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private static EventManager instance;
    private final List<Event> events = new ArrayList<>();

    private EventManager() {}

    public static synchronized EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(String eventId) {
        events.removeIf(event -> event.getId().equals(eventId));
    }
}
