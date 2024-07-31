package com.example.cw2bfms;

import java.util.ArrayList;
import java.util.List;

public class NotificationManager {
    private List<String> notifications;

    public NotificationManager() {
        notifications = new ArrayList<>();
    }

    // Method to add a notification
    public void addNotification(String message) {
        notifications.add(message);
        System.out.println("Notification: " + message); // Print to console for debugging
    }

    // Method to get all notifications
    public List<String> getNotifications() {
        return notifications;
    }
}