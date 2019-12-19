package expression;

public class Variable extends Term {

    private String variableName;

    public Variable(String name){
        variableName = name;
        hash = variableName.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Variable){
            Variable var = (Variable)object;
            return variableName.equals(var.variableName);
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return variableName;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public double evaluate(double x) {
        return x;
    }
}
