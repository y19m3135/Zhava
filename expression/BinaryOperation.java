package expression;

public abstract class BinaryOperation extends Expression {
    protected Expression left, right;

    public BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

}