import java.util.Scanner;

public class BowlingApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Game game = new Game();

    public static void main(String[] args) {
        System.out.println("=== Bowling Score Tracker ===");
        System.out.println("Enter the number of pins knocked down (0-9) for each roll.");
        System.out.println("The game will automatically move to the next frame when needed.\n");
        
        game.start();
        
        while (!game.isFinished()) {
            try {
                System.out.printf("Frame %d, Roll %d: ", 
                    game.getCurrentFrameIndex() + 1,
                    game.getCurrentFrameIndex() < 10 ? 1 : 1);
                
                String input = scanner.nextLine().trim();
                int pins = Integer.parseInt(input);
                
                game.roll(pins);
                game.displayScore();
                
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number between 0 and 9.");
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        System.out.printf("Game Over! Final Score: %d%n", game.getTotalScore());
        scanner.close();
    }
}
