package expression;

import expression.generic.Evaluator;

public class CheckedMin extends BinaryOperation {
    public CheckedMin(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T extends Number> T evaluate(int x, int y, int z, Evaluator<T> evaluator) {
        return evaluator.min(left.evaluate(x, y, z, evaluator), right.evaluate(x, y, z, evaluator));
    }
}
