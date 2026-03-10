/**
 * Represents an eco-friendly activity performed by a user.
 */
public class GreenActivity {
    private final int activityID;
    private final String activityType;
    private final String date;
    private final int points;
    private final int userID;

    public GreenActivity(int activityID, String activityType, String date, int userID) {
        this.activityID = activityID;
        this.activityType = activityType;
        this.date = date;
        this.userID = userID;
        this.points = calculatePoints(activityType);
    }

    public int getActivityID() {
        return activityID;
    }

    public String getActivityType() {
        return activityType;
    }

    public String getDate() {
        return date;
    }

    public int getPoints() {
        return points;
    }

    public int getUserID() {
        return userID;
    }

    private int calculatePoints(String type) {
        switch (type.toLowerCase()) {
            case "walking instead of driving":
            case "cycling":
                return 10;
            case "saving electricity":
                return 15;
            case "using reusable materials":
                return 12;
            case "planting trees":
                return 20;
            case "reducing plastic usage":
                return 8;
            default:
                return 5;
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Type: %-25s | Date: %s | Points: %d",
                activityID, activityType, date, points);
    }
}
