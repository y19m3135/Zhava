package expression;

import expression.generic.Evaluator;

public class Variable extends Expression {
    String variableName;

    public Variable(String name) {
        variableName = name;
    }

    @Override
    public <T extends Number> T evaluate(int x, int y, int z, Evaluator<T> evaluator) {
        if (variableName.equals("x")) {
            return evaluator.convertInt(x);
        } else if (variableName.equals("y")) {
            return evaluator.convertInt(y);
        } else if (variableName.equals("z")) {
            return evaluator.convertInt(z);
        }
        throw new IllegalArgumentException("strange variable name");
    }

    @Override
    public String toString() {
        return variableName;
    }
}
