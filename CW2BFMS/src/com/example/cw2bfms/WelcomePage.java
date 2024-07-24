package com.example.cw2bfms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage implements ActionListener {

    JFrame frame = new JFrame();
    JLabel welcomeLabel = new JLabel();
    JButton calendarButton = new JButton("Add Event");
    JButton viewCalendarButton = new JButton("View Events");
    JButton manageTasksButton = new JButton("Manage Tasks");

    private String userID;

    WelcomePage(String userID) {
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

        frame.add(greetingLabel);
        frame.add(welcomeLabel);
        frame.add(calendarButton);
        frame.add(viewCalendarButton);
        frame.add(manageTasksButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calendarButton) {
            frame.dispose();
            new CalendarView(userID); // Pass userID to CalendarView
        } else if (e.getSource() == viewCalendarButton) {
            frame.dispose();
            new CalendarDisplay(userID); // Pass userID to CalendarDisplay
        } else if (e.getSource() == manageTasksButton) {
            frame.dispose();
            new TaskView(userID); // Pass userID to TaskView
        }
    }
}
