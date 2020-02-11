package expression;

public class Variable extends Term {
    String variableName;
    public Variable(String name){
        variableName = name;
        hash = variableName.hashCode();
        isConstant = false;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if(variableName.equals("x")){
            return x;
        } else if(variableName.equals("y")){
            return y;
        } else if(variableName.equals("z")){
            return z;
        }
        throw new  IllegalArgumentException("unexpected variable name");
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toString() {
        return variableName;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && variableName.equals(((Variable)obj).variableName);
    }
}
