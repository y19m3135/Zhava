package expression; 
import java.util.Map;

public class Const implements Unit {
    private final Number value;

    public Const(Number value) {
        this.value = value;
    }


    @Override
    public int evaluate(Map<String, Integer> variables) {
        return value.intValue();
    }

    @Override
    public double evaluate(double var) {
        return value.doubleValue();
    }

    @Override
    public String toString() {
        if (value.intValue() == value.doubleValue()) {
            return String.valueOf((int) value); //oh no
        }
        return String.valueOf(value);
    }

    @Override
    public String toMiniString() {
        return String.valueOf(value);
    }

    @Override
    public int hashCode() {
        return (int) (value.equals(0) ? -1 : value.doubleValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const) {
            return ((Const) obj).value.equals(value);
        }
        return false;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
