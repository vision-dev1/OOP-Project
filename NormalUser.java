import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a regular participant in the sustainability program.
 */
public class NormalUser extends User {
    private final ArrayList<GreenActivity> allActivities;
    private static int activityCounter = 1;

    public NormalUser(int userID, String name, String email, String password,
            ArrayList<GreenActivity> allActivities) {
        super(userID, name, email, "Normal User", password);
        this.allActivities = allActivities;
    }

    @Override
    public void displayRole() {
        System.out.println("📌 Role: Normal User – Green Campus Participant");
    }

    public void logActivity(Scanner scanner) {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("   🌱 LOG A GREEN ACTIVITY");
        System.out.println("=".repeat(40));

        String[] activities = {
                "Walking instead of driving",
                "Cycling",
                "Saving electricity",
                "Using reusable materials",
                "Planting trees",
                "Reducing plastic usage"
        };

        for (int i = 0; i < activities.length; i++) {
            System.out.printf("  %d. %s\n", i + 1, activities[i]);
        }
        System.out.print("Enter your choice (1-6): ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice < 1 || choice > 6)
                throw new Exception();
        } catch (Exception e) {
            System.out.println("❌ Invalid selection. Returning to menu.");
            return;
        }

        String type = activities[choice - 1];
        System.out.print("Enter the date (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();

        if (date.isEmpty()) {
            System.out.println("❌ Date is required.");
            return;
        }

        GreenActivity newActivity = new GreenActivity(activityCounter++, type, date, getUserID());
        allActivities.add(newActivity);

        System.out.println("\n✅ Activity successfully recorded!");
        System.out.println("   Pts Earned: " + newActivity.getPoints());
        System.out.println("=".repeat(40));
    }

    public void viewMyActivities() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("   📋 MY ACTIVITY HISTORY");
        System.out.println("=".repeat(40));

        int totalPoints = 0;
        boolean hasData = false;

        for (GreenActivity activity : allActivities) {
            if (activity.getUserID() == getUserID()) {
                System.out.println(activity);
                totalPoints += activity.getPoints();
                hasData = true;
            }
        }

        if (!hasData) {
            System.out.println("⚠️  Record empty. Start logging to earn points!");
        } else {
            System.out.println("-".repeat(40));
            System.out.println("🏅 Total Sustainability Points: " + totalPoints);
        }
        System.out.println("=".repeat(40));
    }
}
