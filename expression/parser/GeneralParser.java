package expression.parser;

import expression.exceptions.ParseException;

import java.util.Set;

public abstract class GeneralParser implements Parser {
    private static final Set<Character> operations =
            Set.of('+', '-', '*', '/', 'l', 'p');
    protected StringSource sauce;
    protected char current;

    protected boolean test(char expected) {
        if (current == expected) {
            current = sauce.next();
            return true;
        }
        return false;
    }

    protected void nextChar() {
        current = sauce.next();
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

    protected void expect(char expected) throws ParseException {
        if (!test(expected)) {
            throw new ParseException(exceptionMessage(new StringBuilder("'").append(expected).append("' expected")));
        }
    }

    protected void expect(String expected) throws ParseException {
        for (int i = 0; i < expected.length(); i++) {
            expect(expected.charAt(i));
        }
    }

    protected int getCursor() {
        return sauce.getCursor();
    }

    private String getParsedPart() {
        return sauce.getSkippedPart();
    }

    protected String exceptionMessage(StringBuilder errorPart) {
        return new StringBuilder("parsed part: ").append(getParsedPart()).append('\n')
                .append(errorPart).toString();
    }

}
