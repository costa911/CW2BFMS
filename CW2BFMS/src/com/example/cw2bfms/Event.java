package com.example.cw2bfms;

import java.util.Random;

public class Event {

    private String id;
    private String date;
    private String title;
    private String description;

    public Event(String date, String title, String description) {
        this.id = generateRandomId();
        this.date = date;
        this.title = title;
        this.description = description;
    }

    private String generateRandomId() {
        Random random = new Random();
        int id = random.nextInt(90000) + 10000; // Generates a random 5-digit number
        return String.valueOf(id);
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Date: " + date + ", Title: " + title + ", Description: " + description;
    }
}