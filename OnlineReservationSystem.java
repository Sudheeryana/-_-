import java.util.*;

public class OnlineReservationSystem {

    static Scanner scanner = new Scanner(System.in);
    static Map<String, String> userCredentials = new HashMap<>(); 
    static Map<String, Reservation> reservations = new HashMap<>();
    static boolean isLoggedIn = false;
    static String loggedInUser = "";

    public static void main(String[] args) {
        initializeUsers(); 
        mainMenu();
    }

    // Initialize login users
    private static void initializeUsers() {
        userCredentials.put("user1", "password1");
        userCredentials.put("user2", "password2");
    }

    // Main Menu
    private static void mainMenu() {
        while (true) {
            System.out.println("\n--- Online Reservation System ---");
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

        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            System.out.println("Login successful! Welcome, " + username + ".");
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
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    logout();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Make Reservation
    private static void makeReservation() {
        System.out.println("\n--- Make a Reservation ---");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();

        System.out.print("Enter class type (e.g., AC, Sleeper): ");
        String classType = scanner.nextLine();

        System.out.print("Enter date of journey (dd-mm-yyyy): ");
        String journeyDate = scanner.nextLine();

        System.out.print("Enter departure place: ");
        String fromPlace = scanner.nextLine();

        System.out.print("Enter destination place: ");
        String toPlace = scanner.nextLine();

        String pnr = UUID.randomUUID().toString().substring(0, 8); 
        Reservation reservation = new Reservation(name, trainNumber, classType, journeyDate, fromPlace, toPlace, pnr);

        reservations.put(pnr, reservation);
        System.out.println("Reservation successful! Your PNR is: " + pnr);
    }

    // Cancel Reservation
    private static void cancelReservation() {
        System.out.println("\n--- Cancel a Reservation ---");
        System.out.print("Enter your PNR number: ");
        String pnr = scanner.nextLine();

        if (reservations.containsKey(pnr)) {
            Reservation reservation = reservations.get(pnr);
            System.out.println("Reservation Details:");
            System.out.println(reservation);

            System.out.print("Do you want to cancel this reservation? (yes/no): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("No reservation found with the provided PNR.");
        }
    }

    // Logout functionality
    private static void logout() {
        System.out.println("Logging out. Goodbye, " + loggedInUser + ".");
        isLoggedIn = false;
        loggedInUser = "";
    }
}

// Reservation Class to store reservation details
class Reservation {
    private String name;
    private String trainNumber;
    private String classType;
    private String journeyDate;
    private String fromPlace;
    private String toPlace;
    private String pnr;

    public Reservation(String name, String trainNumber, String classType, String journeyDate, String fromPlace, String toPlace, String pnr) {
        this.name = name;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.journeyDate = journeyDate;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.pnr = pnr;
    }

    @Override
    public String toString() {
        return "PNR: " + pnr + "\nName: " + name + "\nTrain Number: " + trainNumber +
               "\nClass Type: " + classType + "\nDate of Journey: " + journeyDate +
               "\nFrom: " + fromPlace + "\nTo: " + toPlace;
    }
}
