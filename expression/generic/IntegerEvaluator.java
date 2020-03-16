package expression.generic;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.IntegerOverflowException;

public class IntegerEvaluator implements Evaluator<Integer> {
    @Override
    public Integer add(Integer l, Integer r) {
        int res = l + r;
        if (((res ^ l) & (res ^ r)) < 0) {
            throw new IntegerOverflowException("at: " + l + " + " + r);
        } else {
            return res;
        }
    }

    @Override
    public Integer subtract(Integer l, Integer r) {
        int res = l - r;
        if (((res ^ l) & (l ^ r)) < 0) {
            throw new IntegerOverflowException("at: " + l + " - " + r);
        } else {
            return res;
        }
    }

    @Override
    public Integer multiply(Integer l, Integer r) {
        int res = l * r;
        if ((!(l == 0 || r == 0) && res / r != l) || (l == Integer.MIN_VALUE && r == -1)) {
            throw new IntegerOverflowException("at: " + toString());
        } else {
            return res;
        }
    }

    @Override
    public Integer divide(Integer l, Integer r) {
        if (r == 0) {
            throw new DivisionByZeroException("at: " + toString());
        } else if (l == Integer.MIN_VALUE && r == -1) {
            throw new IntegerOverflowException("at: " + toString());
        } else {
            return l / r;
        }
    }

    @Override
    public Integer min(Integer l, Integer r) {
        return l < r ? l : r;
    }

    @Override
    public Integer max(Integer l, Integer r) {
        return l < r ? r : l;
    }

    @Override
    public Integer convertInt(int x) {
        return x;
    }

    @Override
    public Integer negate(Integer x) {
        if (x == Integer.MIN_VALUE) {
            throw new IntegerOverflowException("at: " + x);
        } else {
            return -x;
        }
    }

    @Override
    public int count(Integer x) {
        return Integer.bitCount(x);
    }
}
