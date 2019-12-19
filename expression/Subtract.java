package expression;

public class Subtract extends Operation {

    public Subtract(AbstractExpression ex1, AbstractExpression ex2) {
        super(ex1, ex2);
        operator = '-';
        hash += 4;
    }

    @Override
    public int evaluate(int x) {
        return expression1.evaluate(x) - expression2.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return expression1.evaluate(x) - expression2.evaluate(x);
    }
}
