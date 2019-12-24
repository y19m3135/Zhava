package expression; 
import java.util.Map;

public class Variable implements Unit {
    private final String name;
//    private static class NoArgumentException extends IllegalArgumentException {}

    public Variable(final String name) {
        this.name = name;
    }

    @Override
    public int evaluate(Map<String, Integer> variables) {
        return variables.get(name);
    }

    @Override
    public double evaluate(double var) {
        return var;
    }

    @Override
    public String toMiniString() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public int hashCode() {
        return name.chars().sum() * 4001;
    }

    public boolean equals(Object other) {
        if (other instanceof Variable) {
            return name.equals(((Variable) other).name);
        }
        return false;
    }
}
