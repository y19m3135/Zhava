package expression;

public class Negate extends Term {
    private CommonExpression expression;

    public Negate(CommonExpression expression) {
        this.expression = expression;
        hash = 53 * expression.hash + 19;
        isConstant = expression.isConstant;
    }

    @Override
    public int evaluate(int x) {
        return -expression.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -expression.evaluate(x, y, z);
    }

    @Override
    public double evaluate(double x) {
        return -expression.evaluate(x);
    }

    @Override
    public boolean equals(Object obj) {
        return expression.equals(obj);
    }

    @Override
    public String toString() {
        return new StringBuilder("-").append(expression.toString()).toString();
    }
}
