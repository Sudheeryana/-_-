import java.util.*;

public class QuizApplication {

    static Scanner scanner = new Scanner(System.in);
    static Map<String, String> userData = new HashMap<>(); 
    static Map<String, String> userProfile = new HashMap<>();
    static boolean isLoggedIn = false;
    static String loggedInUser = "";

    public static void main(String[] args) {
        initializeUsers(); 
        mainMenu();
    }

    // Initialize with hardcoded users
    private static void initializeUsers() {
        userData.put("user1", "password1");
        userProfile.put("user1", "Name: John Doe, Email: john@example.com");

        userData.put("user2", "password2");
        userProfile.put("user2", "Name: Jane Doe, Email: jane@example.com");
    }

    // Main Menu
    private static void mainMenu() {
        while (true) {
            System.out.println("\nWelcome to the Quiz Application!");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Login functionality
    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userData.containsKey(username) && userData.get(username).equals(password)) {
            System.out.println("Login successful! Welcome, " + username + "!");
            isLoggedIn = true;
            loggedInUser = username;
            userDashboard();
        } else {
            System.out.println("Invalid username or password. Try again.");
        }
    }

    // User Dashboard
    private static void userDashboard() {
        while (isLoggedIn) {
            System.out.println("\n--- User Dashboard ---");
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. Update Password");
            System.out.println("4. Start Quiz");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    viewProfile();
                    break;
                case 2:
                    updateProfile();
                    break;
                case 3:
                    updatePassword();
                    break;
                case 4:
                    startQuiz();
                    break;
                case 5:
                    logout();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // View Profile
    private static void viewProfile() {
        System.out.println("\n--- Profile Information ---");
        System.out.println(userProfile.getOrDefault(loggedInUser, "No profile information available."));
    }

    // Update Profile
    private static void updateProfile() {
        System.out.print("Enter new profile information (e.g., Name: John, Email: john@example.com): ");
        String newProfile = scanner.nextLine();
        userProfile.put(loggedInUser, newProfile);
        System.out.println("Profile updated successfully!");
    }

    // Update Password
    private static void updatePassword() {
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();

        if (userData.get(loggedInUser).equals(currentPassword)) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            userData.put(loggedInUser, newPassword);
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Incorrect current password. Try again.");
        }
    }

    // Start Quiz
    private static void startQuiz() {
        System.out.println("\n--- Quiz ---");
        String[][] questions = {
            {"What is the capital of France?", "1. Berlin", "2. Paris", "3. Madrid", "4. Rome", "2"},
            {"What is 5 + 3?", "1. 5", "2. 8", "3. 10", "4. 7", "2"},
            {"Which programming language is this project written in?", "1. Python", "2. Java", "3. C++", "4. JavaScript", "2"}
        };
        int timer = 30; 
        int score = 0;

        System.out.println("You have " + timer + " seconds to complete the quiz. Good luck!");

        long startTime = System.currentTimeMillis();

        for (String[] question : questions) {
            if ((System.currentTimeMillis() - startTime) / 1000 > timer) {
                System.out.println("\nTime is up! Auto-submitting your quiz.");
                break;
            }

            System.out.println("\n" + question[0]);
            for (int i = 1; i <= 4; i++) {
                System.out.println(question[i]);
            }
            System.out.print("Your answer (1-4): ");
            String answer = scanner.nextLine();

            if (answer.equals(question[5])) {
                score++;
            }
        }

        System.out.println("\nQuiz completed! Your score: " + score + "/" + questions.length);
    }

    // Logout
    private static void logout() {
        System.out.println("Logging out. Goodbye, " + loggedInUser + "!");
        isLoggedIn = false;
        loggedInUser = "";
    }
}

