package com.example.cw2bfms;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Handles the two-factor authentication interface and process.
 */

public class TwoFactorAuthPage implements ActionListener {

    JFrame frame = new JFrame();
    JLabel codeLabel = new JLabel("Enter 2FA Code:");
    JTextField codeField = new JTextField();
    JButton verifyButton = new JButton("Verify");
    JLabel messageLabel = new JLabel();
    String userID;
    String correctCode;

    public TwoFactorAuthPage(String userID, String correctCode) {
        this.userID = userID;
        this.correctCode = correctCode;

        // Setting bounds for labels and fields
        codeLabel.setBounds(50, 50, 100, 25);
        codeField.setBounds(150, 50, 200, 25);
        verifyButton.setBounds(150, 100, 100, 25);
        messageLabel.setBounds(50, 150, 300, 35);
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 25));

        // Adding components to frame
        frame.add(codeLabel);
        frame.add(codeField);
        frame.add(verifyButton);
        frame.add(messageLabel);

        // Frame settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 250);
        frame.setLayout(null);
        frame.setVisible(true);

        // Add action listener
        verifyButton.setFocusable(false);
        verifyButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == verifyButton) {
            String enteredCode = codeField.getText();

            if (enteredCode.equals(correctCode)) {
                messageLabel.setForeground(Color.green);
                messageLabel.setText("Verification successful");
                frame.dispose();
                WelcomePage welcomePage = new WelcomePage(userID);
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Incorrect code");
            }
        }
    }
}