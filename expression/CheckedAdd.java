package expression;

import expression.generic.Evaluator;

public class CheckedAdd extends BinaryOperation {

    public CheckedAdd(Expression left, Expression right) {
        super(left, right);
    }


    @Override
    public <T extends Number> T evaluate(int x, int y, int z, Evaluator<T> evaluator) {
        return evaluator.add(left.evaluate(x, y, z, evaluator), right.evaluate(x, y, z, evaluator));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(').append(left.toString()).append(" + ").append(right.toString())
                .append(')');
        return sb.toString();
    }
}
