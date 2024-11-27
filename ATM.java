import java.util.*;

public class ATM {

    public static void main(String[] args) {
        ATMSystem atmSystem = new ATMSystem();
        atmSystem.start();
    }
}

// Class to manage ATM functionalities
class ATMSystem {
    private Scanner scanner = new Scanner(System.in);
    private Map<String, UserAccount> accounts = new HashMap<>();
    private UserAccount loggedInAccount;

    public ATMSystem() {
        // Adding demo users
        accounts.put("user1", new UserAccount("user1", "1234", 1000.0));
        accounts.put("user2", new UserAccount("user2", "5678", 2000.0));
    }

    // Start the ATM system
    public void start() {
        System.out.println("Welcome to the ATM System!");
        while (true) {
            System.out.print("\nEnter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String userPin = scanner.nextLine();

            if (authenticate(userId, userPin)) {
                System.out.println("Login successful! Welcome, " + userId + "!");
                mainMenu();
            } else {
                System.out.println("Invalid User ID or PIN. Try again.");
            }
        }
    }

    // Authenticate user credentials
    private boolean authenticate(String userId, String userPin) {
        if (accounts.containsKey(userId) && accounts.get(userId).getPin().equals(userPin)) {
            loggedInAccount = accounts.get(userId);
            return true;
        }
        return false;
    }

    // Display main menu and handle options
    private void mainMenu() {
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    quit();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Show transaction history
    private void showTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        loggedInAccount.printTransactionHistory();
    }

    // Handle withdraw functionality
    private void withdraw() {
        System.out.print("\nEnter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (loggedInAccount.withdraw(amount)) {
            System.out.println("Withdrawal successful! Remaining balance: " + loggedInAccount.getBalance());
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    // Handle deposit functionality
    private void deposit() {
        System.out.print("\nEnter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (loggedInAccount.deposit(amount)) {
            System.out.println("Deposit successful! New balance: " + loggedInAccount.getBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }

    // Handle transfer functionality
    private void transfer() {
        System.out.print("\nEnter recipient User ID: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        if (!accounts.containsKey(recipientId)) {
            System.out.println("Recipient account not found.");
        } else if (loggedInAccount.transfer(accounts.get(recipientId), amount)) {
            System.out.println("Transfer successful! Remaining balance: " + loggedInAccount.getBalance());
        } else {
            System.out.println("Transfer failed. Insufficient balance or invalid amount.");
        }
    }

    // Quit the application
    private void quit() {
        System.out.println("Thank you for using the ATM. Goodbye!");
        loggedInAccount = null;
    }
}

// Class to represent a user account
class UserAccount {
    private String userId;
    private String pin;
    private double balance;
    private List<String> transactionHistory;

    public UserAccount(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial balance: " + initialBalance);
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: " + amount + ", Remaining balance: " + balance);
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount + ", New balance: " + balance);
            return true;
        }
        return false;
    }

    public boolean transfer(UserAccount recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add("Transferred: " + amount + " to " + recipient.userId);
            return true;
        }
        return false;
    }

    public void printTransactionHistory() {
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}
