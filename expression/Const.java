package expression;

public class Const extends Expression {
    public Const(Integer value) {
        this.value = value;
    }


    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}