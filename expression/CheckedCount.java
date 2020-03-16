package expression;

import expression.generic.Evaluator;

public class CheckedCount extends UnaryOperation {

    public CheckedCount(Expression expression) {
        super(expression);
    }

    @Override
    public <T extends Number> T evaluate(int x, int y, int z, Evaluator<T> evaluator) {
        return evaluator.convertInt(evaluator.count(expression.evaluate(x, y, z, evaluator)));
    }
}
