import java.util.Objects;

public class Roll {
    private final int pins;

    public Roll(int pins) {
        if (pins < 0 || pins > 9) {
            throw new IllegalArgumentException("Number of pins must be between 0 and 9");
        }
        this.pins = pins;
    }

    public int getPins() {
        return pins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roll roll = (Roll) o;
        return pins == roll.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
