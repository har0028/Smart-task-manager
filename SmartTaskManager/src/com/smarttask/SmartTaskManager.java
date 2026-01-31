package com.smarttask;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class SmartTaskManager {

    // ===== GLOBAL DATA =====
    static ArrayList<Task> taskList = new ArrayList<>();
    static int taskIdCounter = 1;

    // ===== MAIN METHOD =====
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n===== SMART TASK MANAGER =====");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Update Task");
            System.out.println("5. Delete Task");
            System.out.println("6. Sort Tasks");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("❌ Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {

                case 1:
                    addTask(scanner);
                    break;

                case 2:
                    viewAllTasks();
                    break;

                case 3:
                    markTaskAsCompleted(scanner);
                    break;

                case 4:
                    updateTask(scanner);
                    break;

                case 5:
                    deleteTask(scanner);
                    break;

                case 6:
                    sortTasks(scanner);
                    break;

                case 0:
                    running = false;
                    System.out.println("Exiting Smart Task Manager. Bye!");
                    break;

                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

    // ===== ADD TASK =====
    private static void addTask(Scanner scanner) {

        scanner.nextLine(); // clear buffer

        System.out.print("Enter Task Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Task Description: ");
        String description = scanner.nextLine();

        TaskPriority priority;
        try {
            System.out.print("Enter Priority (HIGH/MEDIUM/LOW): ");
            priority = TaskPriority.valueOf(scanner.nextLine().toUpperCase());
        } catch (Exception e) {
            System.out.println("❌ Invalid priority.");
            return;
        }

        LocalDate dueDate;
        try {
            System.out.print("Enter Due Date (YYYY-MM-DD): ");
            dueDate = LocalDate.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("❌ Invalid date format.");
            return;
        }

        Task task = new Task(
                taskIdCounter++,
                title,
                description,
                priority,
                dueDate
        );

        taskList.add(task);
        System.out.println("✅ Task added successfully!");
    }

    // ===== VIEW TASKS =====
    private static void viewAllTasks() {

        if (taskList.isEmpty()) {
            System.out.println("⚠️ No tasks available.");
            return;
        }

        System.out.println("\n----- TASK LIST -----");
        for (Task task : taskList) {
            System.out.println(task);
        }
    }

    // ===== MARK COMPLETED =====
    private static void markTaskAsCompleted(Scanner scanner) {

        if (taskList.isEmpty()) {
            System.out.println("⚠️ No tasks available.");
            return;
        }

        System.out.print("Enter Task ID: ");
        int id;

        try {
            id = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("❌ Invalid ID.");
            scanner.nextLine();
            return;
        }

        for (Task task : taskList) {
            if (task.getId() == id) {
                task.markCompleted();
                System.out.println("✅ Task marked as completed!");
                return;
            }
        }

        System.out.println("❌ Task ID not found.");
    }

    // ===== UPDATE TASK =====
    private static void updateTask(Scanner scanner) {

        if (taskList.isEmpty()) {
            System.out.println("⚠️ No tasks available.");
            return;
        }

        System.out.print("Enter Task ID to update: ");
        int id;

        try {
            id = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("❌ Invalid ID.");
            scanner.nextLine();
            return;
        }

        scanner.nextLine(); // clear buffer

        for (Task task : taskList) {

            if (task.getId() == id) {

                System.out.println("Leave empty to keep old value");

                System.out.print("New Title: ");
                String title = scanner.nextLine();
                if (!title.isEmpty()) task.setTitle(title);

                System.out.print("New Description: ");
                String description = scanner.nextLine();
                if (!description.isEmpty()) task.setDescription(description);

                System.out.print("New Priority (HIGH/MEDIUM/LOW): ");
                String p = scanner.nextLine();
                if (!p.isEmpty()) {
                    try {
                        task.setPriority(TaskPriority.valueOf(p.toUpperCase()));
                    } catch (Exception e) {
                        System.out.println("❌ Invalid priority skipped.");
                    }
                }

                System.out.print("New Due Date (YYYY-MM-DD): ");
                String d = scanner.nextLine();
                if (!d.isEmpty()) {
                    try {
                        task.setDueDate(LocalDate.parse(d));
                    } catch (Exception e) {
                        System.out.println("❌ Invalid date skipped.");
                    }
                }

                System.out.println("✅ Task updated successfully!");
                return;
            }
        }

        System.out.println("❌ Task ID not found.");
    }

    // ===== DELETE TASK =====
    private static void deleteTask(Scanner scanner) {

        if (taskList.isEmpty()) {
            System.out.println("⚠️ No tasks available.");
            return;
        }

        System.out.print("Enter Task ID to delete: ");
        int id;

        try {
            id = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("❌ Invalid ID.");
            scanner.nextLine();
            return;
        }

        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getId() == id) {
                taskList.remove(i);
                System.out.println("🗑️ Task deleted successfully!");
                return;
            }
        }

        System.out.println("❌ Task ID not found.");
    }

    // ===== SORT TASKS =====
    private static void sortTasks(Scanner scanner) {

        if (taskList.isEmpty()) {
            System.out.println("⚠️ No tasks available.");
            return;
        }

        System.out.println("1. Sort by Due Date");
        System.out.println("2. Sort by Priority");
        System.out.print("Choose option: ");

        int option;

        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("❌ Invalid input.");
            scanner.nextLine();
            return;
        }

        switch (option) {

            case 1:
                taskList.sort((t1, t2) ->
                        t1.getDueDate().compareTo(t2.getDueDate())
                );
                System.out.println("✅ Tasks sorted by Due Date.");
                break;

            case 2:
                taskList.sort((t1, t2) ->
                        t1.getPriority().compareTo(t2.getPriority())
                );
                System.out.println("✅ Tasks sorted by Priority.");
                break;

            default:
                System.out.println("❌ Invalid option.");
                return;
        }

        viewAllTasks();
    }
}
