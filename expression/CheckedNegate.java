package expression;

import expression.exceptions.EvaluationException;
import expression.exceptions.IntegerOverflowException;

public class CheckedNegate extends UnaryOperation {

    public CheckedNegate(Expression expression) {
        super(expression);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int res = expression.evaluate(x, y, z);
        if (res == Integer.MIN_VALUE) {
            throw new IntegerOverflowException("at: " + toString());
        } else {
            return -res;
        }
    }

    @Override
    public String toString() {
        return "-" + expression.toString();
    }
}
