package expression;

public abstract class CommonExpression implements TripleExpression, Expression, DoubleExpression {
    protected int hash;
    protected boolean isConstant;

    @Override
    public int hashCode() {
        return hash;
    }
}

/*
            TripleExpression Expression DoubleExpression
                         \       |        /
                          CommonExpression
                         /                \
                     Term                 Operation
                   /   |   \             /   |    |   \
            Variable Const Negate  Multiply Add Divide Subtract

*/