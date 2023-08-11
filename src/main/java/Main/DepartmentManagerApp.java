package Main;
import com.DOA.EntityAccessor;
import com.Modal.Department;

import java.util.List;
import java.util.Scanner;

public class DepartmentManagerApp {

    public static void runDepartmentManager(Scanner scanner, EntityAccessor<Department> departmentAccessor) {
        while (true) {
            System.out.println("Department Management");
            System.out.println("1. Add Department");
            System.out.println("2. Update Department");
            System.out.println("3. Delete Department");
            System.out.println("4. View Department");
            System.out.println("5. List All Departments");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addDepartment(scanner, departmentAccessor);
                    break;
                case 2:
                    updateDepartment(scanner, departmentAccessor);
                    break;
                case 3:
                    deleteDepartment(scanner, departmentAccessor);
                    break;
                case 4:
                    viewDepartment(scanner, departmentAccessor);
                    break;
                case 5:
                    listAllDepartments(departmentAccessor);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
//-------------------------------------------Adding the department To the Departments table-------------------
    private static void addDepartment(Scanner scanner, EntityAccessor<Department> departmentAccessor) {
        Department department = new Department();

        System.out.print("Enter department name: ");
        department.setDeptName(scanner.next());

        departmentAccessor.addEntity(department);
        System.out.println("Department added successfully.");
    }
//---------------------------------Updating the department from departments table where ID matches -------------
    private static void updateDepartment(Scanner scanner, EntityAccessor<Department> departmentAccessor) {
        System.out.print("Enter department ID to update: ");
        int deptId = scanner.nextInt();
        Department department = departmentAccessor.getEntityById(deptId);

        if (department != null) {
            System.out.print("Enter new department name: ");
            department.setDeptName(scanner.next());

            departmentAccessor.updateEntity(department);
            System.out.println("Department updated successfully.");
        } else {
            System.out.println("Department with ID " + deptId + " not found.");
        }
    }
//------------------------------deleting department from the departments table where ID matches -----------
    private static void deleteDepartment(Scanner scanner, EntityAccessor<Department> departmentAccessor) {
        System.out.print("Enter department ID to delete: ");
        int deptId = scanner.nextInt();
        departmentAccessor.deleteEntity(deptId);
        System.out.println("Department deleted successfully.");
    }
//-------------------------------displaying department table where the Id matches -------------------------
    private static void viewDepartment(Scanner scanner, EntityAccessor<Department> departmentAccessor) {
        System.out.print("Enter department ID to view: ");
        int deptId = scanner.nextInt();
        Department department = departmentAccessor.getEntityById(deptId);

        if (department != null) {
            System.out.println("Department ID: " + department.getDeptId());
            System.out.println("Department Name: " + department.getDeptName());
        } else {
            System.out.println("Department with ID " + deptId + " not found.");
        }
    }
//---------------------Displaying all the departments from the departments table-----------------------
    private static void listAllDepartments(EntityAccessor<Department> departmentAccessor) {
        List<Department> departments = departmentAccessor.getAllEntities();

        if (departments.isEmpty()) {
            System.out.println("No departments found.");
        } else {
            System.out.println("List of Departments:");
            for (Department department : departments) {
                System.out.println("Department ID: " + department.getDeptId());
                System.out.println("Department Name: " + department.getDeptName());
                System.out.println();
            }
        }
    }
}
