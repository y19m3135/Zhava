package expression;

import expression.exceptions.EvaluationException;
import expression.exceptions.IntegerOverflowException;
import expression.exceptions.PowerEvaluationException;

public class CheckedPow2 extends UnaryOperation {
    public CheckedPow2(Expression expression) {
        super(expression);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int arg = expression.evaluate(x, y, z);
        if (arg > 31) {
            throw new IntegerOverflowException("at: " + toString());
        }
        if (arg < 0) {
            throw new PowerEvaluationException("negative argument at: " + toString());
        }
        return 1 << arg;
    }

    @Override
    public String toString() {
        return new StringBuilder("(2 ^ ").append(expression.toString()).append(')').toString();
    }
}
