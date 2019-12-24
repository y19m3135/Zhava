package expression; 
import java.util.Map;

public class Divide extends AbstractBinOperation {
    public Divide(Unit left, Unit right) {
        super("/", 5, left, right);
    }

    @Override
    public double evaluate(double var) {
        return left.evaluate(var) / right.evaluate(var);
    }

    @Override
    public int evaluate(Map<String, Integer> variables) {
        return left.evaluate(variables) / right.evaluate(variables);
    }   //don't think i should use (Object var) here, even to avoid copy-pasting
}
