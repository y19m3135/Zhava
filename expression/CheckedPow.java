package expression;

import expression.exceptions.EvaluationException;
import expression.exceptions.IntegerOverflowException;
import expression.exceptions.PowerEvaluationException;

public class CheckedPow extends BinaryOperation {
    public CheckedPow(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int first = left.evaluate(x, y, z), second = right.evaluate(x, y, z);
        if (second < 0) {
            throw new PowerEvaluationException("negative power at: " + toString());
        } else if (first == 0) {
            if (second == 0) {
                throw new PowerEvaluationException("0 ^ 0 at: " + toString());
            } else {
                return 0;
            }
        }
        int result = 1;
        while (true) {
            if ((second & 1) == 1) {
                if ((result * first) / first != result) {
                    throw new IntegerOverflowException("at: " + toString());
                }
                result *= first;
            }
            second >>>= 1;
            if (second == 0) {
                break;
            }

            if ((first * first) / first != first) {
                throw new EvaluationException("overflow");
            }
            first *= first;
        }
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder("(").append(left.toString()).append(" ^ ").append(right.toString()).append(')').toString();
    }
}