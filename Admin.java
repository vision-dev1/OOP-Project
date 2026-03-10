import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an administrator with reporting privileges.
 */
public class Admin extends User implements Reportable {
    private final ArrayList<GreenActivity> allActivities;

    public Admin(int userID, String name, String email, String password,
            ArrayList<GreenActivity> allActivities) {
        super(userID, name, email, "Admin", password);
        this.allActivities = allActivities;
    }

    @Override
    public void displayRole() {
        System.out.println("📌 Role: Admin – Campus Sustainability Manager");
    }

    public void viewAllActivities() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("   📋 ALL CAMPUS ACTIVITIES");
        System.out.println("=".repeat(40));

        if (allActivities.isEmpty()) {
            System.out.println("⚠️  No activities logged in the system.");
            return;
        }

        for (GreenActivity activity : allActivities) {
            System.out.println(activity);
        }
        System.out.println("-".repeat(40));
        System.out.println("Total Records: " + allActivities.size());
    }

    @Override
    public void generateReport() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   🌍 SUSTAINABILITY ANALYTICS            ║");
        System.out.println("╚══════════════════════════════════════════╝");

        if (allActivities.isEmpty()) {
            System.out.println("⚠️  Notification: Insufficient data for reporting.");
            return;
        }

        int totalPoints = 0;
        HashMap<String, Integer> activityCount = new HashMap<>();

        for (GreenActivity activity : allActivities) {
            totalPoints += activity.getPoints();
            String type = activity.getActivityType();
            activityCount.put(type, activityCount.getOrDefault(type, 0) + 1);
        }

        System.out.println("\n📊 Key Metrics:");
        System.out.println("   Total Activities : " + allActivities.size());
        System.out.println("   Total Impact Pts : " + totalPoints);

        System.out.println("\n📂 Activity Distribution:");
        String commonType = "N/A";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : activityCount.entrySet()) {
            System.out.printf("   • %-25s : %d\n", entry.getKey(), entry.getValue());
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                commonType = entry.getKey();
            }
        }

        System.out.println("\n🏆 Top Contributor Type: " + commonType);
        System.out.println("══════════════════════════════════════════");
    }
}
