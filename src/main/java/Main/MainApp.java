package Main;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.DOA.TaskDAO;
import com.DOA.DepartmentDAO;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        TaskDAO taskDAO = new TaskDAO(sessionFactory);
        DepartmentDAO departmentDAO = new DepartmentDAO(sessionFactory);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Task and Department Manager App");
            System.out.println("1. Task Management");
            System.out.println("2. Department Management");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    TaskManagerApp.runTaskManager(scanner, taskDAO, departmentDAO);
                    break;
                case 2:
                    DepartmentManagerApp.runDepartmentManager(scanner, departmentDAO);
                    break;
                case 3:
                    sessionFactory.close();
                    scanner.close();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
