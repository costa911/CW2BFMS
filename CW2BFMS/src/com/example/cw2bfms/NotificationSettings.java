package com.example.cw2bfms;

import javax.swing.*;
import java.awt.*;

// NotificationSettings class
public class NotificationSettings {
    JFrame frame;
    private boolean locationNotificationsEnabled = false; // Track if location notifications are enabled

    public NotificationSettings(String userID) {
        frame = new JFrame("Notification Settings");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400); // Increased frame height to accommodate new checkbox
        frame.setLayout(new GridLayout(0, 1));

        // Add checkboxes for notification preferences
        JCheckBox soundCheckbox = new JCheckBox("Enable Sound Notifications");
        soundCheckbox.setToolTipText("Toggle to receive sound alerts for notifications.");
        
        JCheckBox smsCheckbox = new JCheckBox("Enable SMS Notifications");
        smsCheckbox.setToolTipText("Toggle to receive notifications via SMS.");
        
        JCheckBox emailCheckbox = new JCheckBox("Enable Email Notifications");
        emailCheckbox.setToolTipText("Toggle to receive notifications via email.");
        
        // New checkbox for location-based notifications
        JCheckBox locationCheckbox = new JCheckBox("Enable Location-based Notifications");
        locationCheckbox.setToolTipText("Toggle to receive notifications based on your location.");

        // Add components to the frame
        frame.add(soundCheckbox);
        frame.add(smsCheckbox);
        frame.add(emailCheckbox);
        frame.add(locationCheckbox); // Add the new checkbox

        // Save Preferences Button
        JButton saveButton = new JButton("Save Preferences");
        frame.add(saveButton);

        // Back Button
        JButton backButton = new JButton("Back");
        frame.add(backButton);

        // Add action listener for save button
        saveButton.addActionListener(e -> {
            // Logic to save preferences (you can implement saving logic here)
            JOptionPane.showMessageDialog(frame, "Notification preferences saved!");
            // Keep the application open after saving
        });

        // Add action listener for back button
        backButton.addActionListener(e -> {
            frame.dispose(); // Close the settings window
            new WelcomePage(userID); // Open the WelcomePage

            // Show the popup after a delay if location notifications are enabled
            if (locationNotificationsEnabled) {
                Timer timer = new Timer(10000, event -> {
                    JOptionPane.showMessageDialog(null, "You are next to ABC Store! There is a Pending Grocery List!");
                });
                timer.setRepeats(false); // Only execute once
                timer.start(); // Start the timer
            }
        });

        // Add action listener for location checkbox
        locationCheckbox.addActionListener(e -> {
            locationNotificationsEnabled = locationCheckbox.isSelected(); // Update the state
        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
