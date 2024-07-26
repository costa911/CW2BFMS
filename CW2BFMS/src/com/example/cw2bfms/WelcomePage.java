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
    private String userID;

    public WelcomePage(String userID) {
        this.userID = userID;

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

        // Set tooltips for better user understanding
        calendarButton.setToolTipText("Click to add a new event to the calendar.");
        viewCalendarButton.setToolTipText("Click to view existing events.");
        manageTasksButton.setToolTipText("Click to manage tasks for family members.");
        shoppingListsButton.setToolTipText("Click to manage shopping lists.");

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

        // Add action listeners
        calendarButton.addActionListener(this);
        viewCalendarButton.addActionListener(this);
        manageTasksButton.addActionListener(this);
        shoppingListsButton.addActionListener(this);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        if (e.getSource() == calendarButton) {
            new CalendarView(userID);
        } else if (e.getSource() == viewCalendarButton) {
            new CalendarDisplay(userID);
        } else if (e.getSource() == manageTasksButton) {
            new TaskView(userID);
        } else if (e.getSource() == shoppingListsButton) {
            new ShoppingLists(userID);
        }
    }
}