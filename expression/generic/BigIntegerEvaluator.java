package expression.generic;

import expression.exceptions.DivisionByZeroException;

import java.math.BigInteger;

public class BigIntegerEvaluator implements Evaluator<BigInteger> {
    @Override
    public BigInteger add(BigInteger l, BigInteger r) {
        return l.add(r);
    }

    @Override
    public BigInteger subtract(BigInteger l, BigInteger r) {
        return l.subtract(r);
    }

    @Override
    public BigInteger multiply(BigInteger l, BigInteger r) {
        return l.multiply(r);
    }

    @Override
    public BigInteger divide(BigInteger l, BigInteger r) {
        if (r.equals(BigInteger.ZERO)) {
            throw new DivisionByZeroException("At:" + l + " / " + r);
        }
        return l.divide(r);
    }

    @Override
    public BigInteger min(BigInteger l, BigInteger r) {
        return l.compareTo(r) < 0 ? l : r;
    }

    @Override
    public BigInteger max(BigInteger l, BigInteger r) {
        return l.compareTo(r) < 0 ? r : l;
    }

    @Override
    public BigInteger convertInt(int x) {
        return BigInteger.valueOf(x);
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }

    @Override
    public int count(BigInteger x) {
        return x.bitCount();
    }
}
