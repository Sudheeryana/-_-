import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Guess the Number Game!");
        System.out.println("Try to guess the number the computer has chosen.");
        
        int rounds = 3; // Number of rounds
        int maxAttempts = 5; // Attempts per round
        int totalScore = 0;

        for (int round = 1; round <= rounds; round++) {
            System.out.println("\nRound " + round + " begins!");
            int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
            boolean guessedCorrectly = false;
            int attemptsUsed = 0;

            for (int attempt = 1; attempt <= maxAttempts; attempt++) {
                System.out.print("Attempt " + attempt + " of " + maxAttempts + ": Enter your guess (1-100): ");
                int userGuess = scanner.nextInt();
                attemptsUsed++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the number!");
                    guessedCorrectly = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("The number is higher than " + userGuess + ".");
                } else {
                    System.out.println("The number is lower than " + userGuess + ".");
                }
            }

            if (guessedCorrectly) {
                int roundScore = (maxAttempts - attemptsUsed + 1) * 10; // Points decrease with more attempts
                System.out.println("You scored " + roundScore + " points this round.");
                totalScore += roundScore;
            } else {
                System.out.println("Sorry, you've used all your attempts. The number was " + numberToGuess + ".");
            }
        }

        System.out.println("\nGame over! Your total score is: " + totalScore);
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
