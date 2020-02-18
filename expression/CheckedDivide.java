package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.IntegerOverflowException;

public class CheckedDivide extends BinaryOperation {

    public CheckedDivide(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int first = left.evaluate(x, y, z), second = right.evaluate(x, y, z),
                res = first / second;
        if (second == 0) {
            throw new DivisionByZeroException("at: " + toString());
        } else if (first == Integer.MIN_VALUE && second == -1) {
            throw new IntegerOverflowException("at: " + toString());
        } else {
            return res;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(').append(left.toString()).append(" / ").append(right.toString())
                .append(')');
        return sb.toString();
    }
}
