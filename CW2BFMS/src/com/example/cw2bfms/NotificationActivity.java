package com.example.cw2bfms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationActivity {
    private JFrame frame;

    public NotificationActivity(String userID) {
        frame = new JFrame("Notification Activity");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400); // Increased bounds for better visibility
        frame.setLayout(new GridLayout(0, 1));

        // Example notifications
        String[] notifications = {
            "31-7-24: Today at 19:00 you must attend Lisa's Football match",
            "26-7-24: You have been assigned to do the Grocery Shopping",
            "25-7-24: You have been assigned to take out the trash"
        };

        for (String notification : notifications) {
            JPanel notificationPanel = new JPanel();
            notificationPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            // Checkbox for marking complete
            JCheckBox checkBox = new JCheckBox();
            checkBox.setPreferredSize(new Dimension(30, 30)); // Size of the checkbox

            // Label for the notification message
            JLabel notificationLabel = new JLabel(notification + " [Pending]");
            notificationLabel.setPreferredSize(new Dimension(500, 50)); // Width for the label

            // Add checkbox and label to the panel
            notificationPanel.add(checkBox);
            notificationPanel.add(notificationLabel);

            // Add action listener to the checkbox
            checkBox.addActionListener(e -> {
                if (checkBox.isSelected()) {
                    notificationLabel.setText(notification + " - Completed"); // Update label text
                    checkBox.setEnabled(false); // Disable checkbox after marking complete
                } else {
                    notificationLabel.setText(notification + " [Mark Complete]"); // Reset label text
                    checkBox.setEnabled(true); // Enable checkbox again if unchecked
                }
            });

            // Add the notification panel to the frame
            frame.add(notificationPanel);
        }

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(150, 30)); // Size for the back button
        backButton.setFont(new Font("Arial", Font.BOLD, 14)); // Make the back button text bold
        backButton.setBackground(Color.LIGHT_GRAY); // Change background color to light gray
        backButton.setForeground(Color.BLACK); // Set text color to black
        frame.add(backButton);

        // Add action listener for back button
        backButton.addActionListener(e -> {
            frame.dispose(); // Close the notification activity window
            new WelcomePage(userID); // Open the WelcomePage
        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}