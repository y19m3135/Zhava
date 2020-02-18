package expression;

public abstract class ExpressionElement {
    protected Enum<ElementType> type;

    public Enum getType() {
        return type;
    }
}

