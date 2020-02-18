package expression;

import expression.exceptions.EvaluationException;
import expression.exceptions.IntegerOverflowException;

public class CheckedSubtract extends BinaryOperation {

    public CheckedSubtract(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int first = left.evaluate(x, y, z), second = right.evaluate(x, y, z),
                res = first - second;
        if (((res ^ first) & (first ^ second)) < 0) {
            throw new IntegerOverflowException("at: " + toString());
        } else {
            return res;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(').append(left.toString()).append(" - ").append(right.toString())
                .append(')');
        return sb.toString();
    }
}
