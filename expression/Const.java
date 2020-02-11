package expression;

public class Const extends Term {

    public Const(Number value) {
        this.value = value;
        hash = value.hashCode();
        isConstant = true;
    }

    @Override
    public double evaluate(double x) {
        return value.doubleValue();
    }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && value.equals(((Const) obj).value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
