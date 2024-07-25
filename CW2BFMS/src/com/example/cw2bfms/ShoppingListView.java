package com.example.cw2bfms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Displays items within a specific shopping list.
 */
public class ShoppingListView implements ActionListener {

    private JFrame frame = new JFrame();
    private JLabel listLabel = new JLabel();
    private JList<String> itemList;
    DefaultListModel<String> itemModel;
    private JButton backButton = new JButton("Back");
    private JButton addItemButton = new JButton("Add Item");
    private JButton completeButton = new JButton("Complete");
    private String userID;
    private String listName;
    private boolean isCompleted;

    public ShoppingListView(String userID, String listName, boolean isCompleted) {
        this.userID = userID;
        this.listName = listName;
        this.isCompleted = isCompleted;

        listLabel.setText(listName);
        listLabel.setBounds(50, 20, 300, 25);
        listLabel.setFont(new Font("Arial", Font.BOLD, 18));

        itemModel = new DefaultListModel<>();

        // Load items based on list name
        ArrayList<String> items = ShoppingLists.pendingLists.get(listName);
        if (items != null) {
            for (String item : items) {
                itemModel.addElement(item);
            }
        } else if (ShoppingLists.completedLists.get(listName) != null) {
            items = ShoppingLists.completedLists.get(listName);
            for (String item : items) {
                itemModel.addElement(item);
            }
        }

        itemList = new JList<>(itemModel);
        itemList.setBounds(50, 60, 300, 200);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        backButton.setBounds(50, 280, 100, 25);
        backButton.addActionListener(this);

        addItemButton.setBounds(170, 280, 100, 25);
        addItemButton.addActionListener(this);
        addItemButton.setEnabled(!isCompleted);

        completeButton.setBounds(290, 280, 100, 25);
        completeButton.addActionListener(this);
        completeButton.setEnabled(!isCompleted);

        frame.add(listLabel);
        frame.add(itemList);
        frame.add(backButton);
        frame.add(addItemButton);
        frame.add(completeButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new ShoppingLists(userID);
            frame.dispose();
        } else if (e.getSource() == addItemButton) {
            String newItem = JOptionPane.showInputDialog(frame, "Enter name for new item:");
            if (newItem != null && !newItem.trim().isEmpty()) {
                itemModel.addElement(newItem.trim());
                if (!isCompleted) {
                    ShoppingLists.pendingLists.get(listName).add(newItem.trim());
                } else {
                    ShoppingLists.completedLists.get(listName).add(newItem.trim());
                }
            }
        } else if (e.getSource() == completeButton) {
            JOptionPane.showMessageDialog(frame, "Shopping list marked as completed.");
            completeButton.setEnabled(false);
            addItemButton.setEnabled(false);
            ShoppingLists.completeList(listName);
        }
    }
}
