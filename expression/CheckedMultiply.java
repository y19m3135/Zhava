package expression;

import expression.exceptions.EvaluationException;
import expression.exceptions.IntegerOverflowException;

public class CheckedMultiply extends BinaryOperation {

    public CheckedMultiply(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int first = left.evaluate(x, y, z), second = right.evaluate(x, y, z),
                res = first * second;
        if ((!(first == 0 || second == 0) && res / second != first) || (first == Integer.MIN_VALUE && second == -1)) {
            throw new IntegerOverflowException("at: " + toString());
        } else {
            return res;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(').append(left.toString()).append(" * ").append(right.toString())
                .append(')');
        return sb.toString();
    }
}
