package expression.parser;

import java.util.Set;

public abstract class GeneralParser implements Parser {
    protected StringSource source;
    protected char current;
    private static final Set<Character> operations =
            Set.of('+', '-', '*', '/', '<', '>');

    protected boolean test(char expected) {
        if (current == expected) {
            current = source.next();
            return true;
        }
        return false;
    }

    protected void nextChar(){
        current = source.next();
    }

    protected boolean hasNext() {
        if (current == ')' || current == '\0') {
            return false;
        }
        return true;
    }

    protected boolean isOperation() {
        return operations.contains(current);
    }


    protected void skipWhitespace() {
        while (Character.isWhitespace(current)) {
            nextChar();
        }
    }

    protected void expect(char expected) {
        if (!test(expected)) {
            throw new IllegalStateException(expected + " expected");
        }
    }


}
