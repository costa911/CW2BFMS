package com.example.cw2bfms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WelcomePage implements ActionListener {

    private JFrame frame;
    private JLabel welcomeLabel;
    private JLabel dateTimeLabel;
    private JLabel footerLabel;
    private JButton calendarButton;
    private JButton viewCalendarButton;
    private JButton manageTasksButton;
    private JButton shoppingListsButton;
    private JButton notificationSettingsButton;
    private JButton notificationActivityButton;
    private String userID;
    private NotificationManager notificationManager;

    public WelcomePage(String userID) {
        this.userID = userID;
        this.notificationManager = new NotificationManager();

        frame = new JFrame("Beecham Family Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridBagLayout());

        // Create and style components
        welcomeLabel = new JLabel("Hello " + userID + ", your login was successful!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        dateTimeLabel = new JLabel(getCurrentDateTime(), SwingConstants.CENTER);
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        calendarButton = createButton("Add Event");
        viewCalendarButton = createButton("View Events");
        manageTasksButton = createButton("Manage Tasks");
        shoppingListsButton = createButton("Shopping Lists");
        notificationSettingsButton = createButton("Notification Settings");
        notificationActivityButton = createButton("Notification Activity");

        footerLabel = new JLabel("<html><a href='https://www.example.com'>BFMS by A.Costa</a></html>", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        footerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        footerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/costa911/CW2BFMS"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Set tooltips for better user understanding
        setToolTips();

        // Using GridBagLayout for flexible layout management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Add components to the frame
        frame.add(new JLabel("Welcome to the Beecham Family Management System!", SwingConstants.CENTER), gbc);
        gbc.gridy++;
        frame.add(welcomeLabel, gbc);
        gbc.gridy++;
        frame.add(dateTimeLabel, gbc);
        gbc.gridy++;
        addButtonsToFrame(gbc);
        gbc.gridy++;
        gbc.weighty = 1.0; // Allow the footer to take up space at the bottom
        frame.add(footerLabel, gbc);

        // Timer to update the date and time every second
        Timer timer = new Timer(1000, e -> dateTimeLabel.setText(getCurrentDateTime()));
        timer.start();

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setToolTips() {
        calendarButton.setToolTipText("Click to add a new event to the calendar.");
        viewCalendarButton.setToolTipText("Click to view existing events.");
        manageTasksButton.setToolTipText("Click to manage tasks for family members.");
        shoppingListsButton.setToolTipText("Click to manage shopping lists.");
        notificationSettingsButton.setToolTipText("Click to customize notification preferences.");
        notificationActivityButton.setToolTipText("Click to view notification activity.");
    }

    private void addButtonsToFrame(GridBagConstraints gbc) {
        JButton[] buttons = {calendarButton, viewCalendarButton, manageTasksButton, shoppingListsButton, notificationSettingsButton, notificationActivityButton};
        for (JButton button : buttons) {
            gbc.gridy++;
            frame.add(button, gbc);
            button.addActionListener(this);
            button.setPreferredSize(new Dimension(200, 40)); // Set preferred size for buttons
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.LIGHT_GRAY); // Change color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(UIManager.getColor("Button.background")); // Reset color to default
            }
        });
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        frame.dispose(); // Dispose of the current frame

        // Use SwingWorker for asynchronous processing if

        // Use SwingWorker for asynchronous processing if necessary
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                if (sourceButton == calendarButton) {
                    new CalendarView(userID);
                } else if (sourceButton == viewCalendarButton) {
                    new CalendarDisplay(userID);
                } else if (sourceButton == manageTasksButton) {
                    new TaskView(userID);
                } else if (sourceButton == shoppingListsButton) {
                    new ShoppingLists(userID, notificationManager);
                } else if (sourceButton == notificationSettingsButton) {
                    new NotificationSettings(userID);
                } else if (sourceButton == notificationActivityButton) {
                    new NotificationActivity(notificationManager, userID);
                }
                return null;
            }
        };
        worker.execute();
    }

    private String getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }
}