package com.example.cw2bfms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskView implements ActionListener {

    JFrame frame = new JFrame();
    JTextField dateField = new JTextField();
    JTextField titleField = new JTextField();
    JTextField descriptionField = new JTextField();
    JTextField userField = new JTextField(); // Field for user assignment
    JButton addButton = new JButton("Add Task");
    JTextField deleteField = new JTextField();
    JButton deleteButton = new JButton("Delete Task");
    JButton completeButton = new JButton("Complete Task");
    JTextArea taskListArea = new JTextArea();
    JButton backButton = new JButton("Back");

    private String userID;

    public TaskView(String userID) {
        this.userID = userID;

        // Set up frame and components
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800); // Increased frame size
        frame.setLayout(null);

        // Set up components
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setBounds(20, 20, 200, 25);
        dateField.setBounds(220, 20, 650, 25);
        dateField.setToolTipText("Enter the due date for the task in YYYY-MM-DD format.");

        JLabel titleLabel = new JLabel("Task Title:");
        titleLabel.setBounds(20, 60, 200, 25);
        titleField.setBounds(220, 60, 650, 25);
        titleField.setToolTipText("Enter the title of the task.");

        JLabel descriptionLabel = new JLabel("Task Description:");
        descriptionLabel.setBounds(20, 100, 200, 25);
        descriptionField.setBounds(220, 100, 650, 25);
        descriptionField.setToolTipText("Enter a description for the task.");

        JLabel userLabel = new JLabel("Assign To:");
        userLabel.setBounds(20, 140, 200, 25);
        userField.setBounds(220, 140, 650, 25);
        userField.setToolTipText("Enter the name of the user to whom the task is assigned.");

        addButton.setBounds(20, 180, 850, 30);
        addButton.addActionListener(this);
        addButton.setToolTipText("Click to add the task with the provided details.");

        JLabel deleteLabel = new JLabel("Task ID to Delete:");
        deleteLabel.setBounds(20, 220, 200, 25);
        deleteField.setBounds(220, 220, 650, 25);
        deleteField.setToolTipText("Enter the ID of the task you want to delete.");

        deleteButton.setBounds(20, 260, 850, 30);
        deleteButton.addActionListener(this);
        deleteButton.setToolTipText("Click to delete the task with the specified ID.");

        JLabel completeLabel = new JLabel("Task ID to Complete:");
        completeLabel.setBounds(20, 300, 200, 25);
        deleteField.setBounds(220, 300, 650, 25); // Reuse deleteField for completing tasks

        completeButton.setBounds(20, 340, 850, 30);
        completeButton.addActionListener(this);
        completeButton.setToolTipText("Click to mark the task with the specified ID as complete.");

        taskListArea.setBounds(20, 380, 850, 250); // Adjusted height for space
        taskListArea.setEditable(false);

        backButton.setBounds(20, 650, 100, 30); // Positioned at bottom-left
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
        frame.add(userLabel);
        frame.add(userField);
        frame.add(addButton);
        frame.add(deleteLabel);
        frame.add(deleteField);
        frame.add(deleteButton);
        frame.add(completeLabel);
        frame.add(completeButton);
        frame.add(taskListArea);
        frame.add(backButton);

        frame.setVisible(true);

        updateTaskList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TaskManager taskManager = TaskManager.getInstance();

        if (e.getSource() == addButton) {
            String date = dateField.getText();
            String title = titleField.getText();
            String description = descriptionField.getText();
            String assignedUser = userField.getText(); // Get assigned user

            if (!date.isEmpty() && !title.isEmpty() && !description.isEmpty() && !assignedUser.isEmpty()) {
                Task task = new Task(date, title, description, assignedUser); // Pass assigned user
                taskManager.addTask(task);
                updateTaskList();
                resetFields(); // Clear fields after adding a task
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill all fields");
            }
        }

        if (e.getSource() == deleteButton) {
            String idToDelete = deleteField.getText();
            taskManager.removeTask(idToDelete);
            updateTaskList();
        }

        if (e.getSource() == completeButton) {
            String idToComplete = deleteField.getText(); // Reuse deleteField for completing tasks
            taskManager.removeTask(idToComplete);
            updateTaskList();
        }
    }

    private void resetFields() {
        dateField.setText("");
        titleField.setText("");
        descriptionField.setText("");
        userField.setText("");
        deleteField.setText("");
    }

    private void updateTaskList() {
        TaskManager taskManager = TaskManager.getInstance();
        StringBuilder taskListText = new StringBuilder();
        for (Task task : taskManager.getTasks()) {
            taskListText.append(task.toString()).append("\n");
        }
        taskListArea.setText(taskListText.toString());
    }
}