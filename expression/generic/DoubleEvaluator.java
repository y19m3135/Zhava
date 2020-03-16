package expression.generic;

public class DoubleEvaluator implements Evaluator<Double> {
    @Override
    public Double add(Double l, Double r) {
        return l + r;
    }

    @Override
    public Double subtract(Double l, Double r) {
        return l - r;
    }

    @Override
    public Double multiply(Double l, Double r) {
        return l * r;
    }

    @Override
    public Double divide(Double l, Double r) {
        return l / r;
    }

    @Override
    public Double min(Double l, Double r) {
        return l < r ? l : r;
    }

    @Override
    public Double max(Double l, Double r) {
        return l < r ? r : l;
    }

    @Override
    public Double convertInt(int x) {
        return (double) x;
    }

    @Override
    public Double negate(Double x) {
        return -x;
    }

    @Override
    public int count(Double x) {
        return Long.bitCount(Double.doubleToRawLongBits(x));
    }
}
