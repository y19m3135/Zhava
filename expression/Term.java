package expression;

public abstract class Term extends CommonExpression {
    protected Number value;

    @Override
    public boolean equals(Object obj) {
        return obj!=null && obj.getClass() == this.getClass();
    }
}
