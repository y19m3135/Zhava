package expression; 
import java.util.Map;

public class Add extends AbstractMoreThanBinOperation {
    public Add(Unit... els) {
        super("+", 10, els);
    }

    @Override
    public double evaluate(double var) {
        double t = 0;
        for (Unit unit : elements) {
            t += unit.evaluate(var);
        }
        return t;
    }

    @Override
    public int evaluate(Map<String, Integer> variables) {
        int t = 0;
        for (Unit unit : elements) {
            t += unit.evaluate(variables);
        }
        return t;
    }
}
