package expression;

public class Add extends Operation {

    public Add(AbstractExpression ex1, AbstractExpression ex2) {
        super(ex1, ex2);
        operator = '+';
        hash += 3;
    }

    @Override
    public int evaluate(int x) {
        return expression1.evaluate(x) + expression2.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return expression1.evaluate(x) + expression2.evaluate(x);
    }
}
