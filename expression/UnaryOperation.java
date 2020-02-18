package expression;

public abstract class UnaryOperation extends Expression {

    protected Expression expression;

    public UnaryOperation(Expression expression) {
        this.expression = expression;
    }
}
