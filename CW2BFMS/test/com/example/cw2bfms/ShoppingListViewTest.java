package com.example.cw2bfms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingListViewTest {

    private ShoppingListView shoppingListView;
    private String userID = "testUser";
    private String listName = "Test List";

    @BeforeEach
    public void setUp() {
        shoppingListView = new ShoppingListView(userID, listName, false);
    }

    @Test
    public void testAddItem() {
        shoppingListView.itemModel.addElement("New Item");
        assertTrue(shoppingListView.itemModel.contains("New Item"));
    }
}

