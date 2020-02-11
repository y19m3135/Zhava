package expression;

public abstract class Operation extends CommonExpression {
    protected String operator;
    protected CommonExpression expression1, expression2;
    protected Number firstValue, secondValue;

    public Operation(CommonExpression ex1, CommonExpression ex2) {
        expression1 = ex1;
        expression2 = ex2;
        hash = 53 * (53 * expression1.hashCode() + expression2.hashCode());
        isConstant = expression1.isConstant && expression2.isConstant;
    }

    private void compress() {
        if (expression1.isConstant && expression1.getClass() != Const.class) {
            expression1 = new Const(firstValue);
        }
        if (expression2.isConstant && expression2.getClass() != Const.class) {
            expression2 = new Const(secondValue);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && object.getClass() == this.getClass()) {
            Operation operation = (Operation) object;
            return operation.expression1.equals(expression1)
                    && operation.expression2.equals(expression2);
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
    public double evaluate(double x) {
        firstValue = expression1.evaluate(x);
        secondValue = expression2.evaluate(x);
        return 0.0;
    }

    protected void preOperate(int x, int y, int z){
        firstValue = expression1.evaluate(x, y, z);
        secondValue = expression2.evaluate(x, y, z);
        //compress();
    }
}
