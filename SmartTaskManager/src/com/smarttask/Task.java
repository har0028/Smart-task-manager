package com.smarttask;

import java.time.LocalDate;

public class Task {

    private int id;
    private String title;
    private String description;
    private TaskPriority priority;
    private LocalDate dueDate;
    private boolean completed;

    public Task(int id, String title, String description,
                TaskPriority priority, LocalDate dueDate) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = false;
    }

    // ===== GETTERS =====
    public int getId() {
        return id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    // ===== SETTERS =====
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // ===== ACTION =====
    public void markCompleted() {
        this.completed = true;
    }

    // ===== DISPLAY =====
    @Override
    public String toString() {
        return "Task ID: " + id +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nPriority: " + priority +
                "\nDue Date: " + dueDate +
                "\nStatus: " + (completed ? "Completed" : "Pending") +
                "\n----------------------------";
    }
}
