package com.example.cw2bfms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationActivityTest {
    private NotificationManager notificationManager;
    private String userID;

    @BeforeEach
    public void setUp() {
        // Create a new instance of NotificationManager
        notificationManager = new NotificationManager();
        userID = "testUser";

        // Add some test notifications
        notificationManager.addNotification("Test Notification 1");
        notificationManager.addNotification("Test Notification 2");
    }

    @Test
    public void testNotificationActivityInitialization() {
        // Create the NotificationActivity
        NotificationActivity notificationActivity = new NotificationActivity(notificationManager, userID);

        // Check if the frame is initialized correctly
        assertNotNull(notificationActivity);
        assertEquals("Notification Activity", notificationActivity.frame.getTitle());
        assertEquals(600, notificationActivity.frame.getWidth());
        assertEquals(400, notificationActivity.frame.getHeight());
    }

    @Test
    public void testDisplayNotifications() {
        NotificationActivity notificationActivity = new NotificationActivity(notificationManager, userID);

        // Check if the first notification is displayed
        JPanel notificationPanel = (JPanel) notificationActivity.frame.getContentPane().getComponent(0);
        JLabel notificationLabel = (JLabel) notificationPanel.getComponent(1); // Assuming the label is the second component

        // Purposefully change the expected output to make the test fail
        assertEquals("Incorrect Notification Text", notificationLabel.getText()); // This will fail
    }

    @Test
    public void testCheckboxFunctionality() {
        NotificationActivity notificationActivity = new NotificationActivity(notificationManager, userID);

        // Simulate checkbox selection
        JPanel notificationPanel = (JPanel) notificationActivity.frame.getContentPane().getComponent(0);
        JCheckBox checkBox = (JCheckBox) notificationPanel.getComponent(0); // Checkbox is the first component
        JLabel notificationLabel = (JLabel) notificationPanel.getComponent(1); // Label is the second component

        // Simulate checking the checkbox
        checkBox.setSelected(true);
        checkBox.getActionListeners()[0].actionPerformed(null); // Trigger the checkbox action

        // Verify the label text changes to completed
        assertEquals("Test Notification 1 - Completed", notificationLabel.getText());
    }
}