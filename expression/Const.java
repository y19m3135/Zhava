package expression;

import expression.generic.Evaluator;

public class Const extends Expression {
    public Const(Integer value) {
        this.value = value;
    }


    @Override
    public <T extends Number> T evaluate(int x, int y, int z, Evaluator<T> evaluator) {
        return evaluator.convertInt(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}