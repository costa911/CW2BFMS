package com.example.cw2bfms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Displays the shopping lists categorized into Completed and Pending.
 */
public class ShoppingLists implements ActionListener {

    private JFrame frame = new JFrame();
    private JLabel pendingLabel = new JLabel("Pending Shopping Lists");
    private JLabel completedLabel = new JLabel("Completed Shopping Lists");
    private JList<String> pendingList;
    private JList<String> completedList;
    private JButton backButton = new JButton("Back");
    private JButton createListButton = new JButton("Create New List");
    private String userID;
    private DefaultListModel<String> pendingModel;
    private DefaultListModel<String> completedModel;

    // Static lists to persist data
    static Map<String, ArrayList<String>> pendingLists = new HashMap<>();
    static Map<String, ArrayList<String>> completedLists = new HashMap<>();

    public ShoppingLists(String userID) {
        this.userID = userID;

        // Initialize mock data for pending lists
        ArrayList<String> groceryItems = new ArrayList<>(Arrays.asList("Apples", "Milk", "Bread"));
        ArrayList<String> cleaningItems = new ArrayList<>(Arrays.asList("Detergent", "Sponges", "Trash bags"));
        pendingLists.put("Grocery List 25-07-24", groceryItems);
        pendingLists.put("Cleaning List 25-07-24", cleaningItems);

        // Initialize mock data for completed lists
        ArrayList<String> completedGroceryItems = new ArrayList<>(Arrays.asList("Oranges", "Eggs", "Cheese"));
        ArrayList<String> completedCleaningItems = new ArrayList<>(Arrays.asList("Broom", "Vacuum", "Mop"));
        completedLists.put("Grocery List 18-07-24", completedGroceryItems);
        completedLists.put("Cleaning List 10-07-24", completedCleaningItems);

        pendingLabel.setBounds(50, 50, 200, 25);
        completedLabel.setBounds(300, 50, 200, 25);

        pendingModel = new DefaultListModel<>();
        completedModel = new DefaultListModel<>();

        // Load items from static lists
        for (String listName : pendingLists.keySet()) {
            pendingModel.addElement(listName);
        }

        for (String listName : completedLists.keySet()) {
            completedModel.addElement(listName);
        }

        pendingList = new JList<>(pendingModel);
        completedList = new JList<>(completedModel);

        pendingList.setBounds(50, 100, 200, 200);
        completedList.setBounds(300, 100, 200, 200);

        pendingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        completedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pendingList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedList = pendingList.getSelectedValue();
                new ShoppingListView(userID, selectedList, false);
                frame.dispose();
            }
        });

        completedList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedList = completedList.getSelectedValue();
                new ShoppingListView(userID, selectedList, true);
                frame.dispose();
            }
        });

        backButton.setBounds(50, 320, 100, 25);
        backButton.addActionListener(this);

        createListButton.setBounds(300, 320, 150, 25);
        createListButton.addActionListener(this);

        frame.add(pendingLabel);
        frame.add(completedLabel);
        frame.add(pendingList);
        frame.add(completedList);
        frame.add(backButton);
        frame.add(createListButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new WelcomePage(userID);
            frame.dispose();
        } else if (e.getSource() == createListButton) {
            String newListName = JOptionPane.showInputDialog(frame, "Enter name for new shopping list:");
            if (newListName != null && !newListName.trim().isEmpty()) {
                pendingModel.addElement(newListName.trim());
                pendingLists.put(newListName.trim(), new ArrayList<>());
            }
        }
    }

    // Method to move list from pending to completed
    public static void completeList(String listName) {
        if (pendingLists.containsKey(listName)) {
            ArrayList<String> items = pendingLists.remove(listName);
            completedLists.put(listName, items);
        }
    }
}