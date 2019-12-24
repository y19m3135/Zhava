package expression; 
import java.util.Map;

public class Subtract extends AbstractBinOperation {
    public Subtract(Unit left, Unit right) {
        super("-", 10, left, right);
    }

    @Override
    public int evaluate(Map<String, Integer> variables) {
        return left.evaluate(variables) - right.evaluate(variables);
    }

    @Override
    public double evaluate(double var) {
        return left.evaluate(var) - right.evaluate(var);
    }
}
