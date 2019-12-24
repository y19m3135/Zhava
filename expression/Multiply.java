package expression; 
import java.util.Map;

public class Multiply extends AbstractMoreThanBinOperation{
    public Multiply(Unit... els) {
        super("*", 5, els);
    }

    @Override
    public double evaluate(double var) {
        double t = 1;
        for (Unit unit : elements) {
            t *= unit.evaluate(var);
        }
        return t;
    }

    @Override
    public int evaluate(Map<String, Integer> variables) {
        int t = 1;
        for (Unit unit : elements) {
            t *= unit.evaluate(variables);
        }
        return t;
    }
}
