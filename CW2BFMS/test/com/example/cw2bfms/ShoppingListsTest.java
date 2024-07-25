package com.example.cw2bfms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ShoppingListsTest {

    @BeforeEach
    public void setUp() {
        ShoppingLists.pendingLists.clear();
        ShoppingLists.completedLists.clear();

        // Initialize with some data
        ArrayList<String> groceryItems = new ArrayList<>(Arrays.asList("Apples", "Milk", "Bread"));
        ShoppingLists.pendingLists.put("Grocery List", groceryItems);
    }

    @Test
    public void testCompleteList() {
        ShoppingLists.completeList("Grocery List");
        assertTrue(ShoppingLists.completedLists.containsKey("Grocery List"));
        assertFalse(ShoppingLists.pendingLists.containsKey("Grocery List"));
    }
}
