package expression;

public class Multiply extends Operation {
    public Multiply(CommonExpression ex1, CommonExpression ex2) {
        super(ex1, ex2);
        operator = "*";
        hash += 7;
    }

    @Override
    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }

    @Override
    public double evaluate(double x) {
        super.evaluate(x);
        return firstValue.doubleValue() * secondValue.doubleValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        super.preOperate(x, y, z);
        return firstValue.intValue() * secondValue.intValue();
    }
}
