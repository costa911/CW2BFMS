package com.example.cw2bfms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CalendarDisplayTest {

    private CalendarDisplay calendarDisplay;
    private JTextArea calendarArea;

    @BeforeEach
    public void setUp() {
        // Initialize CalendarDisplay with a test userID
        calendarDisplay = new CalendarDisplay("testUser");

        // Get the JTextArea component from the CalendarDisplay instance
        calendarArea = findTextArea(calendarDisplay.frame);
    }

    @Test
    public void testCalendarDisplayInitialization() {
        // Verify if JTextArea is initialized
        assertEquals(760, calendarArea.getWidth());
        assertEquals(500, calendarArea.getHeight());
        assertNotEquals(500, calendarArea.getHeight());
    }

    @Test
    public void testUpdateCalendarView() {
        // Add test events
        EventManager.getInstance().addEvent(new Event("2024-07-23", "Meeting", "Project meeting"));
        EventManager.getInstance().addEvent(new Event("2024-07-24", "Deadline", "Submit report"));

        // Trigger update
        calendarDisplay.updateCalendarView();

        // Expected text
        String expectedText = "Upcoming Events\n" +
                "Date: 2024-07-23, Title: Meeting, Description: Project meeting\n" +
                "Date: 2024-07-24, Title: Deadline, Description: Submit report\n";

        // Verify the text in JTextArea
        assertEquals(expectedText, calendarArea.getText());
    }

    // Helper method to find JTextArea in the frame
    private JTextArea findTextArea(JFrame frame) {
        for (java.awt.Component component : frame.getContentPane().getComponents()) {
            if (component instanceof JTextArea) {
                return (JTextArea) component;
            }
        }
        return null;
    }
}
