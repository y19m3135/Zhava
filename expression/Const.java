package expression;

public class Const extends Term {

    public Const(Number someValue){
        value = someValue.intValue();
        doubleValue = someValue.doubleValue();
        hash = value;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Const
                && ((Const)object).value == value;
    }

    @Override
    public String toString() {
        return value == doubleValue ? Integer.toString(value) : Double.toString(doubleValue);
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public double evaluate(double x) {
        return doubleValue;
    }
}
