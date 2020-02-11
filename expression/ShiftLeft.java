package expression;

public class ShiftLeft extends Operation {

    public ShiftLeft(CommonExpression ex1, CommonExpression ex2) {
        super(ex1, ex2);
        operator = "<<";
        hash += 13;
    }

    @Override
    public double evaluate(double x) {
        throw new ArithmeticException("impossible operation");
    }

    @Override
    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        super.preOperate(x, y, z);
        return firstValue.intValue() << secondValue.intValue();
    }
}
