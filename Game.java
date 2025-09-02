import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Frame> frames;
    private int currentFrameIndex;

    public Game() {
        this.frames = new ArrayList<>();
        this.currentFrameIndex = 0;
    }

    public void start() {
        frames.clear();
        for (int i = 0; i < 10; i++) {
            frames.add(new Frame());
        }
        currentFrameIndex = 0;
    }

    public void roll(int pins) {
        if (isFinished()) {
            throw new IllegalStateException("Game is already finished");
        }

        Frame currentFrame = frames.get(currentFrameIndex);
        currentFrame.addRoll(new Roll(pins));

        if (currentFrame.isComplete() && currentFrameIndex < 9) {
            currentFrameIndex++;
        }
    }

    public boolean isFinished() {
        return currentFrameIndex == 9 && frames.get(9).isComplete();
    }

    public int getTotalScore() {
        int total = 0;
        for (int i = 0; i <= currentFrameIndex && i < 10; i++) {
            total += frames.get(i).getScore();
        }
        return total;
    }

    public void displayScore() {
        // Display rolls
        System.out.print("|");
        for (int i = 0; i < 10; i++) {
            System.out.print(" " + frames.get(i).displayFrame() + " |");
        }
        System.out.println();

        // Display cumulative scores
        System.out.print("|");
        int runningTotal = 0;
        for (int i = 0; i <= currentFrameIndex && i < 10; i++) {
            runningTotal += frames.get(i).getScore();
            System.out.print(" " + runningTotal + (runningTotal < 10 ? "  " : " ") + "|");
        }
        System.out.println("\n");
    }

    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }
}
