package expression;
public abstract class AbstractBinOperation implements Unit {
    private final String sign;
    protected final Unit left, right; //well, i made this class extend AbstractMoreThanBinOperation, but changed cause i think i broke one of inheritance principles - parent is about using many, this class is about using two elements.
    private final int priority;

    protected AbstractBinOperation(final String sign, int priority, final Unit left, final Unit right) {
        this.sign = sign;
        this.left = left;
        this.right = right;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "(" + this.left.toString() + " " + this.sign + " " + this.right.toString() + ")";
    }

    @Override
    public String toMiniString() {
        return (
                    left.getPriority() > priority ?
                    "(" + left.toMiniString() + ")"
                    : left.toMiniString()
               )
               + " "
               + sign
               + " "
               + (right.getPriority() >= priority ?
                   "(" + right.toMiniString() + ")"
                   : right.toMiniString());
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractBinOperation) {
            AbstractBinOperation other = (AbstractBinOperation) obj;
            return sign.equals(other.sign) && left.equals(other.left) && right.equals(other.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return sign.chars().sum() * (left.hashCode() + 1) * right.hashCode() % 999043;
    }
}
