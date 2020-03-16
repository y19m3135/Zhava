package expression.parser;

import expression.exceptions.ParseException;

import java.util.Set;

public abstract class GeneralParser implements Parser {
    private static final Set<Character> operations =
            Set.of('+', '-', '*', '/', 'm', 'c');
    protected StringSource source;
    protected char current;

    protected boolean test(char expected) {
        if (current == expected) {
            current = source.next();
            return true;
        }
        return false;
    }

    protected void nextChar() {
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
        return source.getCursor();
    }

    private String getParsedPart() {
        return source.getSkippedPart();
    }

    protected String exceptionMessage(StringBuilder errorPart) {
        return new StringBuilder("parsed part: ").append(getParsedPart()).append('\n')
                .append(errorPart).toString();
    }

}
