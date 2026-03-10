import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main application class for Green Campus Helper.
 */
public class GreenCampusSystem {
    private static final ArrayList<User> userList = new ArrayList<>();
    private static final ArrayList<GreenActivity> activityList = new ArrayList<>();
    private static int userIDCounter = 1;

    public static void main(String[] args) {
        // Initializing default admin
        userList.add(new Admin(userIDCounter++, "Admin", "admin@greencampus.com", "admin123", activityList));

        Scanner scanner = new Scanner(System.in);
        System.out.println("╔" + "═".repeat(42) + "╗");
        System.out.println("║   🌿 GREEN CAMPUS HELPER                 ║");
        System.out.println("║   Climate Action Tracking System         ║");
        System.out.println("╚" + "═".repeat(42) + "╝");

        boolean running = true;
        while (running) {
            System.out.println("\n" + "=".repeat(15) + " MAIN MENU " + "=".repeat(15));
            System.out.println("  1. Register");
            System.out.println("  2. Login");
            System.out.println("  3. Exit");
            System.out.println("=".repeat(41));
            System.out.print("Choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> registerUser(scanner);
                    case 2 -> loginUser(scanner);
                    case 3 -> {
                        running = false;
                        System.out.println("\n🌍 Thank you for your contribution. Goodbye!");
                    }
                    default -> System.out.println("⚠️ Selection out of range (1-3).");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Numerical input expected.");
            }
        }
        scanner.close();
    }

    private static void registerUser(Scanner scanner) {
        System.out.println("\n" + "-".repeat(36));
        System.out.println("   📝 ACCOUNT REGISTRATION");
        System.out.println("-".repeat(36));

        System.out.print("Name     : ");
        String name = scanner.nextLine().trim();
        System.out.print("Email    : ");
        String email = scanner.nextLine().trim();
        System.out.print("Password : ");
        String password = scanner.nextLine().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("❌ All fields are mandatory.");
            return;
        }

        for (User user : userList) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("❌ Email already registered.");
                return;
            }
        }

        userList.add(new NormalUser(userIDCounter++, name, email, password, activityList));
        System.out.println("\n✅ Account created. You may now login.");
    }

    private static void loginUser(Scanner scanner) {
        System.out.println("\n" + "-".repeat(36));
        System.out.println("   🔑 SECURE LOGIN");
        System.out.println("-".repeat(36));

        System.out.print("Email    : ");
        String email = scanner.nextLine().trim();
        System.out.print("Password : ");
        String password = scanner.nextLine().trim();

        User loggedInUser = null;
        for (User user : userList) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                loggedInUser = user;
                break;
            }
        }

        if (loggedInUser == null) {
            System.out.println("❌ Authentication failed. Incorrect credentials.");
            return;
        }

        loggedInUser.login();
        loggedInUser.displayRole();

        if (loggedInUser instanceof Admin admin) {
            adminMenu(scanner, admin);
        } else if (loggedInUser instanceof NormalUser user) {
            userMenu(scanner, user);
        }
    }

    private static void adminMenu(Scanner scanner, Admin admin) {
        boolean active = true;
        while (active) {
            System.out.println("\n" + "=".repeat(15) + " ADMIN DASHBOARD " + "=".repeat(15));
            System.out.println("  1. View All Activities");
            System.out.println("  2. Generate Analytics Report");
            System.out.println("  3. Logout");
            System.out.println("=".repeat(47));
            System.out.print("Choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> admin.viewAllActivities();
                    case 2 -> admin.generateReport();
                    case 3 -> {
                        admin.logout();
                        active = false;
                    }
                    default -> System.out.println("⚠️ Unexpected choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Enter a valid number.");
            }
        }
    }

    private static void userMenu(Scanner scanner, NormalUser normalUser) {
        boolean active = true;
        while (active) {
            System.out.println("\n" + "=".repeat(15) + " USER DASHBOARD " + "=".repeat(15));
            System.out.println("  1. Record Activity");
            System.out.println("  2. View My History");
            System.out.println("  3. Logout");
            System.out.println("=".repeat(46));
            System.out.print("Choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> normalUser.logActivity(scanner);
                    case 2 -> normalUser.viewMyActivities();
                    case 3 -> {
                        normalUser.logout();
                        active = false;
                    }
                    default -> System.out.println("⚠️ Unexpected choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Enter a valid number.");
            }
        }
    }
}
