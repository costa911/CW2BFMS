package com.example.cw2bfms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Displays a welcome message and navigates to different management functions.
 */
public class WelcomePage implements ActionListener {

    private JFrame frame = new JFrame();
    private JLabel welcomeLabel = new JLabel();
    private JButton calendarButton = new JButton("Add Event");
    private JButton viewCalendarButton = new JButton("View Events");
    private JButton manageTasksButton = new JButton("Manage Tasks");
    private JButton shoppingListsButton = new JButton("Shopping Lists");
    private String userID;

    public WelcomePage(String userID) {
        this.userID = userID;

        String welcomeMessage = "Hello, Welcome to the Beecham Family Management System!";
        String userGreeting = "Hello " + userID + ", your login was successful!";

        welcomeLabel.setText(userGreeting);
        welcomeLabel.setBounds(50, 50, 600, 35);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        JLabel greetingLabel = new JLabel(welcomeMessage);
        greetingLabel.setBounds(50, 100, 600, 35);
        greetingLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        calendarButton.setBounds(50, 150, 200, 35);
        calendarButton.addActionListener(this);

        viewCalendarButton.setBounds(50, 200, 200, 35);
        viewCalendarButton.addActionListener(this);

        manageTasksButton.setBounds(50, 250, 200, 35);
        manageTasksButton.addActionListener(this);

        shoppingListsButton.setBounds(50, 300, 200, 35);
        shoppingListsButton.addActionListener(this);

        frame.add(greetingLabel);
        frame.add(welcomeLabel);
        frame.add(calendarButton);
        frame.add(viewCalendarButton);
        frame.add(manageTasksButton);
        frame.add(shoppingListsButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calendarButton) {
            frame.dispose();
            new CalendarView(userID); 
        } else if (e.getSource() == viewCalendarButton) {
            frame.dispose();
            new CalendarDisplay(userID); 
        } else if (e.getSource() == manageTasksButton) {
            frame.dispose();
            new TaskView(userID); 
        } else if (e.getSource() == shoppingListsButton) {
            frame.dispose();
            new ShoppingLists(userID);
        }
    }
}
