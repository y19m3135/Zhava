package expression;

import expression.exceptions.EvaluationException;
import expression.exceptions.LogarithmEvaluationException;

public class CheckedLog2 extends UnaryOperation {
    public CheckedLog2(Expression expression) {
        super(expression);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int arg = expression.evaluate(x, y, z);
        if (arg == 0) {
            throw new LogarithmEvaluationException("zero argument at: " + toString());
        }
        if (arg < 0) {
            throw new LogarithmEvaluationException("negative argument at: " + toString());
        }
        for (int i = 31; i > -1; i--) {
            if ((arg & (1 << i)) != 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return new StringBuilder("(log(2, ").append(expression.toString()).append("))").toString();
    }
}
