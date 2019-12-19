package expression;

public abstract class Operation implements AbstractExpression {
    protected char operator;
    protected AbstractExpression expression1, expression2;
    protected int hash;

    public Operation(AbstractExpression ex1, AbstractExpression ex2) {
        expression1 = ex1;
        expression2 = ex2;
        hash = 53 * (53 * expression1.hashCode() + expression2.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && object.getClass() == this.getClass()) {
            Operation oper = (Operation) object;
            return oper.expression1.equals(expression1)
                    && oper.expression2.equals(expression2);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(').append(expression1.toString()).append(' ')
                .append(operator).append(' ').append(expression2.toString())
                .append(')');

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return hash;
    }
}
