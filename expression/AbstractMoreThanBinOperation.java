package expression; 
public abstract class AbstractMoreThanBinOperation implements Unit {
    private final String sign;
    private final int priority;
    protected Unit[] elements;

    protected AbstractMoreThanBinOperation(final String sign, int priority, Unit... els) {
        assert els.length > 1;
        this.sign = sign;
        this.priority = priority;
        this.elements = els;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(elements[0].toString());
        for (int i = 1; i < elements.length; i++) {
            sb.append(" ").append(sign).append(" ").append(elements[i].toString());
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                elements[0].getPriority() > priority
                ? "(" + elements[0].toMiniString() + ")"
                : elements[0].toMiniString()
        );
        for (int i = 1; i < elements.length; i++) {
            sb.append(" ").append(sign).append(" ");
            sb.append(
                    elements[i].getPriority() > priority
                    ? "(" + elements[i].toMiniString() + ")"
                    : elements[i].toMiniString()
            );
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractMoreThanBinOperation) {
            AbstractMoreThanBinOperation other = (AbstractMoreThanBinOperation) obj;
            if (sign.equals(other.sign) && other.elements.length == elements.length) {
                for (int i = 0; i < elements.length; i++) {
                    if (!elements[i].equals(other.elements[i])) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hs = sign.chars().sum();
        for (int i = 0; i < elements.length; i++) {
            hs *= elements[i].hashCode() + (i+1);
        }
        return hs % 1019129;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
