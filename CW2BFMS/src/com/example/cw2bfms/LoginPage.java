package com.example.cw2bfms;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage implements ActionListener {

    JFrame frame = new JFrame();
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

        // Setting bounds for labels and fields
        userIDLabel.setBounds(50, 50, 75, 25);
        userPasswordLabel.setBounds(50, 100, 75, 25);

        userIDField.setBounds(125, 50, 200, 25);
        userPasswordField.setBounds(125, 100, 200, 25);

        loginButton.setBounds(125, 150, 100, 25);
        resetButton.setBounds(225, 150, 100, 25);

        messageLabel.setBounds(125, 200, 250, 35);
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 25));

        footerLabel.setBounds(0, 250, 420, 25);
        footerLabel.setHorizontalAlignment(JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        // Adding components to frame
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(messageLabel);
        frame.add(footerLabel);

        // Frame settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 300);
        frame.setLayout(null);
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
        }

        if (e.getSource() == loginButton) {

            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if (loginfo.containsKey(userID)) {
                if (loginfo.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful");

                    // Generate and send 2FA code
                    String code = generate2FACode();
                    send2FACode(userID, code);

                    frame.dispose();
                    TwoFactorAuthPage twoFactorAuthPage = new TwoFactorAuthPage(userID, code);
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong password");
                }

            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Username not found");
            }
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
