package expression;

public interface AbstractExpression extends DoubleExpression, Expression {
}

/*
            Expression          DoubleExpression
                    \              /
                   AbstractExpression
                   /                \
                Term             Operation
               /    \           /   |   |   \
        Variable   Const  Divide  Add  Sub  Multiply
 */