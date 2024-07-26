package com.example.cw2bfms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarView implements ActionListener {

    JFrame frame = new JFrame();
    JTextField dateField = new JTextField();
    JTextField titleField = new JTextField();
    JTextField descriptionField = new JTextField();
    JButton addButton = new JButton("Add Event");
    JTextField deleteField = new JTextField();
    JButton deleteButton = new JButton("Delete Event");
    JTextArea eventListArea = new JTextArea();
    JButton backButton = new JButton("Back");

    private String userID;

    public CalendarView(String userID) {
        this.userID = userID;

        // Set up frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);

        // Set up components
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setBounds(10, 10, 200, 25);
        dateField.setBounds(210, 10, 200, 25);
        
        JLabel titleLabel = new JLabel("Event Title:");
        titleLabel.setBounds(10, 40, 200, 25);
        titleField.setBounds(210, 40, 200, 25);
        
        JLabel descriptionLabel = new JLabel("Event Description:");
        descriptionLabel.setBounds(10, 70, 200, 25);
        descriptionField.setBounds(210, 70, 200, 25);
        
        addButton.setBounds(10, 100, 400, 25);
        addButton.addActionListener(this);
        addButton.setToolTipText("Click to add the event with the provided details.");
        
        JLabel deleteLabel = new JLabel("Event ID to Delete:");
        deleteLabel.setBounds(10, 130, 200, 25);
        deleteField.setBounds(210, 130, 200, 25);
        
        deleteButton.setBounds(10, 160, 400, 25);
        deleteButton.addActionListener(this);
        deleteButton.setToolTipText("Click to delete the event with the specified ID.");
        
        eventListArea.setBounds(10, 200, 760, 300);
        eventListArea.setEditable(false);
        
        backButton.setBounds(10, 510, 100, 25);
        backButton.addActionListener(e -> {
            frame.dispose();
            new WelcomePage(userID); // Pass userID back to WelcomePage
        });
        backButton.setToolTipText("Click to return to the welcome page.");

        // Add components to frame
        frame.add(dateLabel);
        frame.add(dateField);
        frame.add(titleLabel);
        frame.add(titleField);
        frame.add(descriptionLabel);
        frame.add(descriptionField);
        frame.add(addButton);
        frame.add(deleteLabel);
        frame.add(deleteField);
        frame.add(deleteButton);
        frame.add(eventListArea);
        frame.add(backButton);

        frame.setVisible(true);
        updateEventList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EventManager eventManager = EventManager.getInstance();

        if (e.getSource() == addButton) {
            String date = dateField.getText();
            String title = titleField.getText();
            String description = descriptionField.getText();

            if (!date.isEmpty() && !title.isEmpty() && !description.isEmpty()) {
                Event event = new Event(date, title, description);
                eventManager.addEvent(event);
                updateEventList();
                resetFields(); // Clear fields after adding an event
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill all fields");
            }
        }

        if (e.getSource() == deleteButton) {
            String idToDelete = deleteField.getText();
            eventManager.removeEvent(idToDelete);
            updateEventList();
        }
    }

    private void resetFields() {
        dateField.setText("");
        titleField.setText("");
        descriptionField.setText("");
        deleteField.setText("");
    }

    private void updateEventList() {
        EventManager eventManager = EventManager.getInstance();
        StringBuilder eventListText = new StringBuilder();
        for (Event event : eventManager.getEvents()) {
            eventListText.append("ID: ").append(event.getId())
                         .append(", Date: ").append(event.getDate())
                         .append(", Title: ").append(event.getTitle())
                         .append(", Description: ").append(event.getDescription())
                         .append("\n");
        }
        eventListArea.setText(eventListText.toString());
    }
}