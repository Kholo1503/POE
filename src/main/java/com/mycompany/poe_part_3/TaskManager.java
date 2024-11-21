/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_part_3;

import java.util.ArrayList;

/**
 *
 * @author south
 */

public class TaskManager {
    // Lists to store various task attributes
    private ArrayList<String> developers = new ArrayList<>(); // Developer details for each task
    private ArrayList<String> taskNames = new ArrayList<>();   // Task names
    private ArrayList<String> taskIDs = new ArrayList<>();     // Task IDs
    private ArrayList<Integer> taskDurations = new ArrayList<>(); // Task durations in hours
    private ArrayList<String> taskStatuses = new ArrayList<>(); // Task statuses (e.g., "To Do", "Doing", "Done")

    // Adds a task to the manager
    public void addTask(Task task) {
        developers.add(task.getDeveloperDetails());                // Add developer's details
        taskNames.add(task.getTaskName());                           // Add task name
        taskIDs.add(task.createTaskID(task.getTaskName(), taskNames.size() - 1, task.getDeveloperDetails())); // Generate and add task ID
        taskDurations.add(task.getTaskDuration());                  // Add task duration
        taskStatuses.add(task.getTaskStatus());                     // Add task status
    }

    // Returns a string displaying all completed tasks (tasks with "Done" status)
    public String displayCompletedTasks() {
        StringBuilder completedTasks = new StringBuilder("Completed Tasks:\n");
        for (int i = 0; i < taskStatuses.size(); i++) {
            if ("Done".equalsIgnoreCase(taskStatuses.get(i))) {
                completedTasks.append("Developer: ").append(developers.get(i))
                              .append(", Task Name: ").append(taskNames.get(i))
                              .append(", Duration: ").append(taskDurations.get(i)).append(" hours\n");
            }
        }
        return completedTasks.toString();
    }

    // Finds and returns the task with the longest duration
    public String displayLongestTask() {
        int maxIndex = 0;
        for (int i = 1; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > taskDurations.get(maxIndex)) {
                maxIndex = i; // Update maxIndex if a longer task is found
            }
        }
        return "Longest Task:\nDeveloper: " + developers.get(maxIndex) +
               ", Task Name: " + taskNames.get(maxIndex) +
               ", Duration: " + taskDurations.get(maxIndex) + " hours";
    }

    // Searches for a task by name and returns its details if found
    public String searchTaskByName(String taskName) {
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                return "Task Found:\nDeveloper: " + developers.get(i) +
                       ", Task Name: " + taskNames.get(i) +
                       ", Status: " + taskStatuses.get(i);
            }
        }
        return "Task not found."; // Return message if task is not found
    }

    // Searches for tasks by a specific developer and returns the list of tasks assigned to them
    public String searchTasksByDeveloper(String developer) {
        StringBuilder tasksByDeveloper = new StringBuilder("Tasks for Developer: " + developer + "\n");
        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).equalsIgnoreCase(developer)) {
                tasksByDeveloper.append("Task Name: ").append(taskNames.get(i))
                                .append(", Status: ").append(taskStatuses.get(i)).append("\n");
            }
        }
        return tasksByDeveloper.length() > 0 ? tasksByDeveloper.toString() : "No tasks found for developer."; // Return tasks or message if none found
    }

    // Deletes a task by its name and removes it from all the lists
    public String deleteTaskByName(String taskName) {
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                // Remove task details from all lists
                developers.remove(i);
                taskNames.remove(i);
                taskIDs.remove(i);
                taskDurations.remove(i);
                taskStatuses.remove(i);
                return "Task '" + taskName + "' deleted successfully."; // Return success message
            }
        }
        return "Task not found."; // Return message if task is not found
    }

    // Displays all tasks in the system with their details
    public String displayAllTasks() {
        StringBuilder allTasks = new StringBuilder("All Tasks:\n");
        for (int i = 0; i < taskNames.size(); i++) {
            allTasks.append("Developer: ").append(developers.get(i))
                    .append(", Task Name: ").append(taskNames.get(i))
                    .append(", Task ID: ").append(taskIDs.get(i))
                    .append(", Duration: ").append(taskDurations.get(i)).append(" hours")
                    .append(", Status: ").append(taskStatuses.get(i)).append("\n");
        }
        return allTasks.toString(); // Return all task details as a string
    }
}
