package Main;

import com.DOA.EntityAccessor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.Modal.Task;
import com.Modal.Department;

import java.util.List;
import java.util.Scanner;

public class TaskManagerApp {

    public static void runTaskManager(Scanner scanner, EntityAccessor<Task> taskAccessor, EntityAccessor<Department> departmentAccessor) {
        while (true) {
            System.out.println("Task Management");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. View Task");
            System.out.println("5. List All Tasks");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addTask(scanner, taskAccessor, departmentAccessor);
                    break;
                case 2:
                    updateTask(scanner, taskAccessor, departmentAccessor);
                    break;
                case 3:
                    deleteTask(scanner, taskAccessor);
                    break;
                case 4:
                    viewTask(scanner, taskAccessor);
                    break;
                case 5:
                    listAllTasks(taskAccessor);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
//------------------------------------------adding task to the task table ---------------------------------------
    private static void addTask(Scanner scanner, EntityAccessor<Task> taskAccessor, EntityAccessor<Department> departmentAccessor) {
        Task task = new Task();

        System.out.print("Enter task name: ");
        task.setName(scanner.next());

        System.out.print("Enter task description: ");
        task.setDescription(scanner.next());

        System.out.print("Enter task priority: ");
        task.setPriority(scanner.next());

        System.out.print("Enter due date (yyyy-MM-dd): ");
        String dueDateStr = scanner.next();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dueDate = dateFormat.parse(dueDateStr);
            task.setDueDate(dueDate);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Task not added.");
            return;
        }
        // Parse due date and set it to the task

        System.out.print("Is the task completed? (true/false): ");
        task.setCompleted(scanner.nextBoolean());

        //-------------------------------------- Display list of departments------------------------------------
        List<Department> departments = departmentAccessor.getAllEntities();
        System.out.println("Available Departments:");
        for (Department department : departments) {
            System.out.println(department.getDeptId() + ". " + department.getDeptName());
        }
        //---------------------------selecting the Department-------------------------------------------------
        System.out.print("Enter department ID for the task: ");
        int deptId = scanner.nextInt();
        task.setDepartment(departmentAccessor.getEntityById(deptId));

        taskAccessor.addEntity(task);
        System.out.println("Task added successfully.");
    }
//---------------------------------------updating the tasks-----------------------------------------------------
    private static void updateTask(Scanner scanner, EntityAccessor<Task> taskAccessor, EntityAccessor<Department> departmentAccessor) {
        System.out.print("Enter task ID to update: ");
        int taskId = scanner.nextInt();
        Task task = taskAccessor.getEntityById(taskId);

        if (task != null) {
            System.out.print("Enter new task name: ");
            task.setName(scanner.next());

            System.out.print("Enter new task description: ");
            task.setDescription(scanner.next());

            System.out.print("Enter new task priority: ");
            task.setPriority(scanner.next());

            System.out.print("Enter new due date (yyyy-MM-dd): ");
            System.out.print("Enter due date (yyyy-MM-dd): ");
            String dueDateStr = scanner.next();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dueDate = dateFormat.parse(dueDateStr);
                task.setDueDate(dueDate);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Task not added.");
                return;
            }

            System.out.print("Is the task completed? (true/false): ");
            task.setCompleted(scanner.nextBoolean());

            // -----------------------------------------Display list of departments----------------------------
            List<Department> departments = departmentAccessor.getAllEntities();
            System.out.println("Available Departments:");
            for (Department department : departments) {
                System.out.println(department.getDeptId() + ". " + department.getDeptName());
            }

            System.out.print("Enter new department ID for the task: ");
            int deptId = scanner.nextInt();
            task.setDepartment(departmentAccessor.getEntityById(deptId));

            taskAccessor.updateEntity(task);
            System.out.println("Task updated successfully.");
        } else {
            System.out.println("Task with ID " + taskId + " not found.");
        }
    }
//------------------------------Deleting a Task from the Tasks table --------------------------
    private static void deleteTask(Scanner scanner, EntityAccessor<Task> taskAccessor) {
        System.out.print("Enter task ID to delete: ");
        int taskId = scanner.nextInt();
        taskAccessor.deleteEntity(taskId);
        System.out.println("Task deleted successfully.");
    }
//------------------------------------View Task by where the ID is matches -----------------------
    private static void viewTask(Scanner scanner, EntityAccessor<Task> taskAccessor) {
        System.out.print("Enter task ID to view: ");
        int taskId = scanner.nextInt();
        Task task = taskAccessor.getEntityById(taskId);

        if (task != null) {
            System.out.println("Task ID: " + task.getId());
            System.out.println("Task Name: " + task.getName());
            System.out.println("Task Description: " + task.getDescription());
            System.out.println("Task Priority: " + task.getPriority());
            System.out.println("Due Date: " + task.getDueDate());
            System.out.println("Completed: " + task.isCompleted());
            System.out.println("Department ID: " + task.getDepartment().getDeptId());
            System.out.println("Department Name: " + task.getDepartment().getDeptName());
        } else {
            System.out.println("Task with ID " + taskId + " not found.");
        }
    }
// -------------------------Displaying the all the tasks from the Tasks Table------------------------
    private static void listAllTasks(EntityAccessor<Task> taskAccessor) {
        List<Task> tasks = taskAccessor.getAllEntities();

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("List of Tasks:");
            for (Task task : tasks) {
                System.out.println("Task ID: " + task.getId());
                System.out.println("Task Name: " + task.getName());
                System.out.println("Task Description: " + task.getDescription());
                System.out.println("Task Priority: " + task.getPriority());
                System.out.println("Due Date: " + task.getDueDate());
                System.out.println("Completed: " + task.isCompleted());
                System.out.println("Department ID: " + task.getDepartment().getDeptId());
                System.out.println("Department Name: " + task.getDepartment().getDeptName());
                System.out.println();
            }
        }
    }
}
