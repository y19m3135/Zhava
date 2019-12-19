package expression;

public abstract class Term implements AbstractExpression{
    protected int value;
    protected double doubleValue;
    protected int hash;

    @Override
    public int hashCode() {
        return hash;
    }
}
