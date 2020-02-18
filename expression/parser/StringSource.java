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

    public int getCursor() {
        return cursor;
    }

    public String getSkippedPart() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cursor; i++) {
            sb.append(source.charAt(i));
        }

        return sb.toString();
    }
}
