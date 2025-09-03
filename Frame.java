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
        
        // Si c'est le premier lancer et qu'il fait 10, on l'ajoute directement (strike)
        if (rolls.isEmpty() && roll.getPins() == 10) {
            rolls.add(roll);
            return;
        }
        
        // Pour les autres cas, on vérifie que la somme ne dépasse pas 10
        if (!rolls.isEmpty() && (rolls.get(0).getPins() + roll.getPins() > 10)) {
            throw new IllegalArgumentException("La somme des quilles dans une frame ne doit pas dépasser 10");
        }
        
        rolls.add(roll);
    }

    public boolean isComplete() {
        // Une frame est complète si on a fait 2 lancers, ou si on a fait un strike (10 quilles au premier lancer)
        return rolls.size() >= 2 || (rolls.size() == 1 && rolls.get(0).getPins() == 10);
    }

    public int getScore() {
        return rolls.stream().mapToInt(Roll::getPins).sum();
    }

    public String displayFrame() {
        StringBuilder sb = new StringBuilder();
        if (!rolls.isEmpty() && rolls.get(0).getPins() == 10) {
            // Strike case
            sb.append("X   ");
        } else {
            // Normal case
            for (Roll roll : rolls) {
                int pins = roll.getPins();
                sb.append(pins == 0 ? "-" : pins).append(" ");
            }
            // If only one roll in the frame, add spaces for alignment
            if (rolls.size() == 1) {
                sb.append("  ");
            }
        }
        return sb.toString().trim();
    }

    public List<Roll> getRolls() {
        return new ArrayList<>(rolls);
    }
}
