package com.example.cw2bfms;

import javax.swing.*;
import java.awt.*;

public class CalendarDisplay {

    JFrame frame = new JFrame();
    JTextArea calendarArea = new JTextArea();
    JButton monthViewButton = new JButton("Upcoming");
    JButton backButton = new JButton("Back");

    private String userID;

    public CalendarDisplay(String userID) {
        this.userID = userID;

        // Set up frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);

        // Set up components
        calendarArea.setBounds(10, 10, 760, 500);
        calendarArea.setEditable(false);
        updateCalendarView();

        monthViewButton.setBounds(10, 520, 100, 25);
        backButton.setBounds(120, 520, 100, 25);

        monthViewButton.addActionListener(e -> updateCalendarView());
        backButton.addActionListener(e -> {
            frame.dispose();
            new WelcomePage(userID); // Pass userID back to WelcomePage
        });

        // Add components to frame
        frame.add(calendarArea);
        frame.add(monthViewButton);
        frame.add(backButton);

        frame.setVisible(true);
    }

    void updateCalendarView() {
        EventManager eventManager = EventManager.getInstance();
        StringBuilder calendarText = new StringBuilder();
        calendarText.append("Upcoming Events\n");
        for (Event event: eventManager.getEvents()) {
            calendarText.append(eventDetails(event));
        }
        calendarArea.setText(calendarText.toString());
    }

    private String eventDetails(Event event) {
        return "Date: " + event.getDate() + ", Title: " + event.getTitle() + ", Description: " + event.getDescription() + "\n";
    }
}