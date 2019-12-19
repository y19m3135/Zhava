package expression;

public class Divide extends Operation {
    public Divide(AbstractExpression ex1, AbstractExpression ex2) {
        super(ex1, ex2);
        operator = '/';
        hash += 6;
    }

    @Override
    public int evaluate(int x) {
        return expression1.evaluate(x) / expression2.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return expression1.evaluate(x) / expression2.evaluate(x);
    }
}
