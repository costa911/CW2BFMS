package com.example.cw2bfms;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WelcomePageTest {

    private WelcomePage welcomePage;

    @BeforeEach
    public void setUp() {
        // Initialize the WelcomePage with a test user ID
        welcomePage = new WelcomePage("TestUser");
    }

    @Test
    public void testWelcomeLabel() {
        // Verify that the welcome label is set correctly
        assertEquals("Hello TestUser, your login was successful!", welcomePage.welcomeLabel.getText());
    }

    @Test
    public void testButtonTooltips() {
        // Verify that tooltips are set correctly
        assertEquals("Click to add a new event to the calendar.", welcomePage.calendarButton.getToolTipText());
        assertEquals("Click to view existing events.", welcomePage.viewCalendarButton.getToolTipText());
        assertEquals("Click to manage tasks for family members.", welcomePage.manageTasksButton.getToolTipText());
        assertEquals("Click to manage shopping lists.", welcomePage.shoppingListsButton.getToolTipText());
    }
}