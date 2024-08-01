package com.example.cw2bfms;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

class NotificationSettingsTest {

    @Test
    void testLocationNotificationsCheckbox() {
        // Create an instance of NotificationSettings
        NotificationSettings notificationSettings = new NotificationSettings("testUser");

        // Get the location checkbox
        JCheckBox locationCheckbox = (JCheckBox) notificationSettings.frame.getContentPane().getComponent(3);

        // Verify that the location checkbox is initially unchecked
        assertFalse(locationCheckbox.isSelected(), "Location notifications checkbox should be initially unchecked.");

        // Click the location checkbox
        locationCheckbox.doClick();

        // Verify that the checkbox is now checked
        assertTrue(locationCheckbox.isSelected(), "Location notifications checkbox should be checked after clicking.");
    }
}
