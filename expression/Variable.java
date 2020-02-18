package expression;

public class Variable extends Expression {
    String variableName;
    public Variable(String name){
        variableName = name;
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
        throw new  IllegalArgumentException("strange variable name");
    }

    @Override
    public String toString() {
        return variableName;
    }
}
