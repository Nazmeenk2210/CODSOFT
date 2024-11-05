import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        int rounds = 3;
        for (int round = 1; round <= rounds; round++) {
            System.out.println("\nROUND " + round);
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 5;
            while (attempts > 0) {
                System.out.print("Guess a number between 1 and 100: ");
                int guess = scanner.nextInt();
                if (guess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the number.");
                    score++;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }
                attempts--;
            }
            if (attempts == 0) {
                System.out.println("You've run out of attempts. The number was : " + numberToGuess);
            }
        }
        System.out.println("\n The game is over. Your final score is : " + score + "/" + rounds);
    }
}
