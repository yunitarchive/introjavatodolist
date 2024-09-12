import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Activity> activities;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.activities = new ArrayList<>();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void addActivity(String description) {
        Activity activity = new Activity(description);
        this.activities.add(activity);
        System.out.println("Activity has been added: " + description);
    }

    public void showActivities() {
        if (this.activities.isEmpty()) {
            System.out.println("No activities to show.");
        } else {
            System.out.println("\nYour Activities:");
            for (int i = 0; i < this.activities.size(); i++) {
                System.out.println((i + 1) + ". " + this.activities.get(i).getDescription());
            }
        }
    }

    public void removeActivity(int activityIndex) {
        if (activityIndex >= 0 && activityIndex < activities.size()) {
            Activity removed = activities.remove(activityIndex);
            System.out.println("Removed activity: " + removed.getDescription());
        } else {
            System.out.println("Invalid activity index to remove activity.");
        }
    }

    public ArrayList<Activity> getActivities() {
        return this.activities;
    }
}
