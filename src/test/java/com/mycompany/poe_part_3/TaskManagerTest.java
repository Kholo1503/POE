/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poe_part_3;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author south
 */

public class TaskManagerTest {
    private TaskManager taskManager;
    private Task task1, task2, task3, task4;

    @BeforeEach
    public void setUp() {
        taskManager = new TaskManager();
        
        // Test data
        task1 = new Task();
        task1.setTaskDetails("Create Login", "Implement user login", "Mike Smith", 5, "To Do", 0);

        task2 = new Task();
        task2.setTaskDetails("Add Features", "Add additional features", "Edward Harrington", 8, "Doing", 1);

        task3 = new Task();
        task3.setTaskDetails("Create Reports", "Generate reports", "Samantha Paulson", 2, "Done", 2);

        task4 = new Task();
        task4.setTaskDetails("Add Arrays", "Implement array handling", "Glenda Oberholzer", 11, "To Do", 3);

        // Add tasks to TaskManager
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addTask(task4);
    }

    @Test
    public void testDisplayCompletedTasks() {
        String completedTasks = taskManager.displayCompletedTasks();
        assertTrue(completedTasks.contains("Samantha Paulson"), "Completed task should include Samantha Paulson.");
        assertTrue(completedTasks.contains("2 hours"), "Completed task duration should be 2 hours.");
    }

    @Test
    public void testDisplayLongestTask() {
        String longestTask = taskManager.displayLongestTask();
        assertTrue(longestTask.contains("Glenda Oberholzer"), "Longest task should be assigned to Glenda Oberholzer.");
        assertTrue(longestTask.contains("11 hours"), "Longest task duration should be 11 hours.");
    }

    @Test
    public void testSearchTaskByName() {
        String searchResult = taskManager.searchTaskByName("Create Login");
        assertTrue(searchResult.contains("Mike Smith"), "Search should return the correct task for Mike Smith.");
        assertTrue(searchResult.contains("To Do"), "Task status should be 'To Do'.");
    }

    @Test
    public void testSearchTasksByDeveloper() {
        String developerTasks = taskManager.searchTasksByDeveloper("Edward Harrington");
        assertTrue(developerTasks.contains("Add Features"), "Should find tasks assigned to Edward Harrington.");
        assertTrue(developerTasks.contains("Doing"), "Task status should be 'Doing'.");
    }

    @Test
    public void testDeleteTaskByName() {
        String deleteMessage = taskManager.deleteTaskByName("Create Reports");
        assertEquals("Task 'Create Reports' deleted successfully.", deleteMessage, "Task deletion should be successful.");
        String allTasks = taskManager.displayAllTasks();
        assertFalse(allTasks.contains("Create Reports"), "Deleted task should not be in the task list.");
    }

    @Test
    public void testDisplayAllTasks() {
        String allTasks = taskManager.displayAllTasks();
        assertTrue(allTasks.contains("Create Login"), "Task list should contain 'Create Login'.");
        assertTrue(allTasks.contains("Add Features"), "Task list should contain 'Add Features'.");
        assertTrue(allTasks.contains("Create Reports"), "Task list should contain 'Create Reports'.");
        assertTrue(allTasks.contains("Add Arrays"), "Task list should contain 'Add Arrays'.");
    }
}
