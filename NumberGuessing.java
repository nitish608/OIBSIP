import java.util.Random;
import java.util.Scanner;

public class NumberGuessing{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        int round = 1;
        boolean playAgain = true;

        System.out.println("-------------Welcome to Guess the Number!-----------");

        while (playAgain) {
            int targetNumber = random.nextInt(100) + 1;
            int attempts = 0;
            int guess;

            System.out.println("\nRound " + round + ":");
            System.out.println("I have selected a number between 1 and 100. Can you guess it?");

            while (true) {
                System.out.print("Enter your guess: ");
                guess = scanner.nextInt();
                attempts++;

                if (guess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number correctly.");
                    System.out.println("Number of attempts: " + attempts);

                    // Calculate score based on the number of attempts
                    int roundScore = 100 - (attempts - 1) * 10;
                    System.out.println("Round score: " + roundScore);

                    score += roundScore;
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                    }

                // Limit the number of attempts to 5
                if (attempts >= 5) {
                    System.out.println("You have reached the maximum number of attempts.");
                    System.out.println("The number was: " + targetNumber);
                    System.out.println("----GAME OVER----");
                    break;
                }
            }

            // Ask the user if they want to play again
            System.out.print("\nDo you want to play again? (yes/no): ");
            String playAgainInput = scanner.next();

            if (playAgainInput.equalsIgnoreCase("no")) {
                playAgain = false;
            }

            round++;
        }

        System.out.println("\nGame Over!");
        System.out.println("Your final score: " + score);

        scanner.close();
    }
}
