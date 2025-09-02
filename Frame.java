import java.util.ArrayList;
import java.util.List;

public class Frame {
    private final List<Roll> rolls;

    public Frame() {
        this.rolls = new ArrayList<>();
    }

    public void addRoll(Roll roll) {
        if (isComplete()) {
            throw new IllegalStateException("Cannot add roll: frame is already complete");
        }
        
        // Check if adding this roll would make the frame sum >= 10
        if (rolls.size() == 1 && (rolls.get(0).getPins() + roll.getPins() >= 10)) {
            throw new IllegalArgumentException("Sum of rolls in a frame must be less than 10");
        }
        
        rolls.add(roll);
    }

    public boolean isComplete() {
        return rolls.size() >= 2 || (rolls.size() == 1 && rolls.get(0).getPins() == 9);
    }

    public int getScore() {
        return rolls.stream().mapToInt(Roll::getPins).sum();
    }

    public String displayFrame() {
        StringBuilder sb = new StringBuilder();
        for (Roll roll : rolls) {
            int pins = roll.getPins();
            sb.append(pins == 0 ? "-" : pins).append(" ");
        }
        // If only one roll in the frame, add a space for alignment
        if (rolls.size() == 1) {
            sb.append("  ");
        }
        return sb.toString().trim();
    }

    public List<Roll> getRolls() {
        return new ArrayList<>(rolls);
    }
}
