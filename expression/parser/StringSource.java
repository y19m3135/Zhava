package expression.parser;

public class StringSource {
    private final String source;
    private int cursor;

    public StringSource(String sauce) {
        this.source = sauce;
    }

    public boolean hasNext() {
        return cursor < source.length();
    }

    public char next() {
        return cursor < source.length() ? source.charAt(cursor++) : '\0';
    }
}
