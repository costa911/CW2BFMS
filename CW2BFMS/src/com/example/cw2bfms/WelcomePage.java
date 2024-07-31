package com.example.cw2bfms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage implements ActionListener {

    private JFrame frame;
    JLabel welcomeLabel;
    JButton calendarButton;
    JButton viewCalendarButton;
    JButton manageTasksButton;
    JButton shoppingListsButton;
    JButton notificationSettingsButton; // New button for Notification Settings
    JButton notificationActivityButton; // New button for Notification Activity
    private String userID;
    private NotificationManager notificationManager; // Add NotificationManager instance

    public WelcomePage(String userID) {
        this.userID = userID;
        this.notificationManager = new NotificationManager(); // Initialize NotificationManager

        frame = new JFrame("Beecham Family Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridBagLayout());

        // Create and style components
        welcomeLabel = new JLabel("Hello " + userID + ", your login was successful!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        calendarButton = new JButton("Add Event");
        viewCalendarButton = new JButton("View Events");
        manageTasksButton = new JButton("Manage Tasks");
        shoppingListsButton = new JButton("Shopping Lists");
        notificationSettingsButton = new JButton("Notification Settings"); // Initialize new button
        notificationActivityButton = new JButton("Notification Activity"); // Initialize new button

        // Set tooltips for better user understanding
        calendarButton.setToolTipText("Click to add a new event to the calendar.");
        viewCalendarButton.setToolTipText("Click to view existing events.");
        manageTasksButton.setToolTipText("Click to manage tasks for family members.");
        shoppingListsButton.setToolTipText("Click to manage shopping lists.");
        notificationSettingsButton.setToolTipText("Click to customize notification preferences."); // Tooltip for new button
        notificationActivityButton.setToolTipText("Click to view notification activity."); // Tooltip for new button

        // Using GridBagLayout for flexible layout management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center components

        // Add components to the frame
        frame.add(new JLabel("Welcome to the Beecham Family Management System!", SwingConstants.CENTER), gbc);
        gbc.gridy++;
        frame.add(welcomeLabel, gbc);
        gbc.gridy++;
        frame.add(calendarButton, gbc);
        gbc.gridy++;
        frame.add(viewCalendarButton, gbc);
        gbc.gridy++;
        frame.add(manageTasksButton, gbc);
        gbc.gridy++;
        frame.add(shoppingListsButton, gbc);
        gbc.gridy++;
        frame.add(notificationSettingsButton, gbc); // Add new button to frame
        gbc.gridy++;
        frame.add(notificationActivityButton, gbc); // Add new button to frame

        // Add action listeners
        calendarButton.addActionListener(this);
        viewCalendarButton.addActionListener(this);
        manageTasksButton.addActionListener(this);
        shoppingListsButton.addActionListener(this);
        notificationSettingsButton.addActionListener(this); // Action listener for new button
        notificationActivityButton.addActionListener(this); // Action listener for new button

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calendarButton) {
            frame.dispose();
            new CalendarView(userID); // Pass NotificationManager to CalendarView
        } else if (e.getSource() == viewCalendarButton) {
            frame.dispose();
            new CalendarDisplay(userID);
        } else if (e.getSource() == manageTasksButton) {
            frame.dispose();
            new TaskView(userID);
        } else if (e.getSource() == shoppingListsButton) {
            frame.dispose();
            new ShoppingLists(userID, notificationManager);
        } else if (e.getSource() == notificationSettingsButton) {
            frame.dispose();
            new NotificationSettings(userID); // Open Notification Settings
        } else if (e.getSource() == notificationActivityButton) {
            frame.dispose();
            new NotificationActivity(notificationManager, userID); // Pass NotificationManager to NotificationActivity
        }
    }
}