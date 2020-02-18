package expression.exceptions;

public class IntegerOverflowException extends EvaluationException {
    public IntegerOverflowException(String message) {
        super(message);
    }
}
