package expression;

import expression.exceptions.EvaluationException;
import expression.exceptions.LogarithmEvaluationException;


public class CheckedLog extends BinaryOperation {
    public CheckedLog(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int first = left.evaluate(x, y, z), second = right.evaluate(x, y, z);

        int pow = 1, result = 0;
        if (second < 2) {
            throw new LogarithmEvaluationException("illegal stepen' of log at: " + toString());
        }
        if (first < 1) {
            throw new LogarithmEvaluationException("argument less than 1 at: " + toString());
        }

        while (pow * second <= first && (pow * second) / second == pow) {
            pow *= second;
            result++;
        }
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("(log(").append(left.toString()).append(", ").append(right.toString()).append("))").toString();
    }
}
