package com.example.cw2bfms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class LoginPage implements ActionListener {

    JFrame frame = new JFrame("Login Page");
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("User ID:");
    JLabel userPasswordLabel = new JLabel("Password:");
    JLabel messageLabel = new JLabel();
    JLabel footerLabel = new JLabel("BFMS by A.Costa");
    private HashMap<String, String> loginfo;

    public LoginPage(HashMap<String, String> loginInfoOriginal) {
        loginfo = loginInfoOriginal;

        // Set layout manager for responsive design
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally

        // Center the components
        gbc.anchor = GridBagConstraints.CENTER;

        // Adding components with GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5; // Allow the label to grow
        frame.add(userIDLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Expand the width of the user ID field
        frame.add(userIDField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(userPasswordLabel, gbc);
        
        gbc.gridx = 1;
        frame.add(userPasswordField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.5; // Allow the button to grow
        frame.add(loginButton, gbc);
        
        gbc.gridx = 1;
        frame.add(resetButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 25));
        frame.add(messageLabel, gbc);
        
        gbc.gridy = 4;
        footerLabel.setHorizontalAlignment(JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        frame.add(footerLabel, gbc);

        // Frame settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 300);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);

        // Add action listeners
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
            messageLabel.setText("");
        }

        if (e.getSource() == loginButton) {
            String userID = userIDField.getText().trim();
            String password = String.valueOf(userPasswordField.getPassword()).trim();

            // Input validation
            if (userID.isEmpty() || password.isEmpty()) {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Please enter both User ID and Password.");
                return;
            }

            // Use a SwingWorker for asynchronous processing
            new SwingWorker<Void, String>() {
                @Override
                protected Void doInBackground() {
                    if (loginfo.containsKey(userID)) {
                        if (loginfo.get(userID).equals(password)) {
                            publish("Login successful");
                            String code = generate2FACode();
                            send2FACode(userID, code);
                            frame.dispose();
                            new TwoFactorAuthPage(userID, code);
                        } else {
                            publish("Wrong password");
                        }
                    } else {
                        publish("Username not found");
                    }
                    return null;
                }

                @Override
                protected void process(java.util.List<String> chunks) {
                    // Update the message label with the result
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText(chunks.get(chunks.size() - 1));
                }
            }.execute();
        }
    }

    private String generate2FACode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Generate a 6-digit code
        return String.valueOf(code);
    }

    private void send2FACode(String userID, String code) {
        // Mock email sending - replace with actual email sending logic
        System.out.println("2FA code sent to " + userID + ": " + code);
    }
}