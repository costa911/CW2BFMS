package com.example.cw2bfms;

import javax.swing.*;
import java.awt.*;

public class NotificationSettings {
    private JFrame frame;

    public NotificationSettings(String userID) {
        frame = new JFrame("Notification Settings");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1));

        // Add checkboxes for notification preferences
        JCheckBox soundCheckbox = new JCheckBox("Enable Sound Notifications");
        soundCheckbox.setToolTipText("Toggle to receive sound alerts for notifications.");
        
        JCheckBox smsCheckbox = new JCheckBox("Enable SMS Notifications");
        smsCheckbox.setToolTipText("Toggle to receive notifications via SMS.");
        
        JCheckBox emailCheckbox = new JCheckBox("Enable Email Notifications");
        emailCheckbox.setToolTipText("Toggle to receive notifications via email.");

        // Add components to the frame
        frame.add(soundCheckbox);
        frame.add(smsCheckbox);
        frame.add(emailCheckbox);

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
        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}