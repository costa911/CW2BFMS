package com.example.cw2bfms;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TaskTest {

    private Task task;

    @Before
    public void setUp() {
        task = new Task("2024-07-24", "Test Task", "This is a test task", "user1");
    }

    @Test
    public void testTaskCreation() {
        assertEquals("2024-07-01", task.getDate());
        assertEquals("Test Task", task.getTitle());
        assertEquals("This is a test task", task.getDescription());
        assertEquals("user1", task.getAssignedUser());
    }
}
