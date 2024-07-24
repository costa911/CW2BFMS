package com.example.cw2bfms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

public class IDandPasswordsTest {

    @Test
    public void testGetLoginInfo() {
        // Arrange
        IDandPasswords idAndPasswords = new IDandPasswords();

        // Act
        HashMap<String, String> loginInfo = idAndPasswords.getLoginInfo();

        // Assert
        assertEquals(6, loginInfo.size()); // Check if the size of the HashMap is 6

        // Verify each entry in the HashMap
        assertTrue(loginInfo.containsKey("Mr.Beecham"));
        assertEquals("password1", loginInfo.get("Mr.Beecham"));

        assertTrue(loginInfo.containsKey("Mrs.Beecham"));
        assertEquals("password2", loginInfo.get("Mrs.Beecham"));

        assertTrue(loginInfo.containsKey("John Beecham"));
        assertEquals("password3", loginInfo.get("John Beecham"));

        assertTrue(loginInfo.containsKey("Anna Beecham"));
        assertEquals("password4", loginInfo.get("Anna Beecham"));

        assertTrue(loginInfo.containsKey("Thomas Beecham"));
        assertEquals("password5", loginInfo.get("Thomas Beecham"));

        assertTrue(loginInfo.containsKey("Lisa Beecham"));
        assertEquals("password6", loginInfo.get("Lisa Beecham"));
       
    }
    
}