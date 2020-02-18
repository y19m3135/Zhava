package expression;

public abstract class Expression extends ExpressionElement implements TripleExpression {
    protected int value;

    {
        type = ElementType.Expression;
    }
}
