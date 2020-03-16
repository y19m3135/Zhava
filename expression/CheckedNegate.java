package expression;

import expression.exceptions.EvaluationException;
import expression.exceptions.IntegerOverflowException;
import expression.generic.Evaluator;

public class CheckedNegate extends UnaryOperation {

    public CheckedNegate(Expression expression) {
        super(expression);
    }

    @Override
    public <T extends Number> T evaluate(int x, int y, int z, Evaluator<T> evaluator) {
        return evaluator.negate(expression.evaluate(x, y, z, evaluator));
    }

    @Override
    public String toString() {
        return "-" + expression.toString();
    }
}
