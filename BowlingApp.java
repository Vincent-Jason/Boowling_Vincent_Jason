import java.util.Scanner;

public class BowlingApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Game game = new Game();

    public static void main(String[] args) {
        System.out.println("=== Bowling Score Tracker ===");
        System.out.println("Enter the number of pins knocked down (0-10) for each roll.");
        System.out.println("Enter 10 for a strike (X). The game will automatically move to the next frame.\n");
        
        game.start();
        
        while (!game.isFinished()) {
            try {
                System.out.printf("Frame %d, Roll %d: ", 
                    game.getCurrentFrameIndex() + 1,
                    game.getCurrentFrameIndex() < 10 ? 1 : 1);
                
                String input = scanner.nextLine().trim();
                int pins = Integer.parseInt(input);
                
                if (pins < 0 || pins > 10) {
                    throw new IllegalArgumentException("Please enter a number between 0 and 10.");
                }
                
                game.roll(pins);
                game.displayScore();
                
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number between 0 and 9.");
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        System.out.printf("Final Score: %d%n", game.getTotalScore());
        scanner.close();
    }
}
