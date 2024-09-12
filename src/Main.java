
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static HashMap<String, User> users = new HashMap<>();
    private static User currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to My Todo:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        register(scanner);
                        break;
                    case 2:
                        login(scanner);
                        if (currentUser != null) {
                            userMenu(scanner);
                        }
                        break;
                    case 3:
                        System.out.println("See you again");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please enter a valid option (1-3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists. Try a different one.");
            return;
        }

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        User newUser = new User(username, password);
        users.put(username, newUser);
        System.out.println("Registration successful.");
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        if (!users.containsKey(username)) {
            System.out.println("User not found. Please register first.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = users.get(username);

        if (user.checkPassword(password)) {
            currentUser = user;
            System.out.println("Login successful.");
        } else {
            System.out.println("Incorrect password.");
            currentUser = null;
        }
    }

    private static void userMenu(Scanner scanner) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\nUser Menu:");
            System.out.println("1. Add Activity");
            System.out.println("2. Show Activities (with remove option)");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        addActivity(scanner);
                        break;
                    case 2:
                        showActivities(scanner);
                        break;
                    case 3:
                        loggedIn = false;
                        currentUser = null;
                        System.out.println("Logged out.");
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a valid option (1-3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static void addActivity(Scanner scanner) {
        System.out.print("Enter activity description: ");
        String description = scanner.nextLine();
        currentUser.addActivity(description);
    }

    private static void showActivities(Scanner scanner) {
        currentUser.showActivities();

        System.out.print("Do you want to remove any activity? (yes/no): ");
        String response = scanner.nextLine().toLowerCase();

        if (response.equals("yes")) {
            try {
                System.out.print("Enter the activity number to remove: ");
                int activityNumber = scanner.nextInt();
                scanner.nextLine();

                currentUser.removeActivity(activityNumber - 1);
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }
}
