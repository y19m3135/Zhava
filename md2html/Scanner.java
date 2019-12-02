package md2html;

import java.io.*;

public class Scanner implements AutoCloseable {
    private InputStreamReader reader;
    private char next;
    private boolean noEnd = true;
    private IsWord isWord = new IsWord();
    private IsWhite isWhite = new IsWhite();
    private IsInt isInt = new IsInt();
    private IsSeparator isSeparator = new IsSeparator();

    public Scanner(InputStream in) throws IOException {
        this.reader = new InputStreamReader(in);
        readNextChar();
    }

    public Scanner(InputStreamReader in) throws IOException {
        this.reader = in;
        readNextChar();
    }

    private boolean readNextChar() throws IOException {
        int size = this.reader.read();
        if (size > 0) next = (char) size;
        else this.noEnd = false;
        return this.noEnd;
    }

    @Override
    public void close() throws IOException {
        this.reader.close();
    }

    private void skip(IsToken token) throws IOException {
        while (this.noEnd &&
                !token.isToken(this.next) &&
                !this.isSeparator.isToken(this.next)) {
            readNextChar();
        }
    }

    private void skipWhite() throws IOException {
        while (this.noEnd &&
                this.isWhite.isToken(this.next) &&
                !this.isSeparator.isToken(this.next)) {
            readNextChar();
        }
    }

    private void skipSeparator() throws IOException {
        while (this.noEnd &&
                !this.isSeparator.isToken(this.next)) {
            readNextChar();
        }
        readNextChar();
    }

    private String next(IsToken token) throws IOException {
        StringBuilder result = new StringBuilder();
        while (this.noEnd) {
            if (token.isToken(this.next)) {
                result.append(this.next);
            } else if (result.length() != 0) {
                break;
            }
            readNextChar();
        }
        if (!this.noEnd && result.length() == 0) {
            throw new IOException("Ничего не нашел, ты дурак чтоле?");
        }
        return result.toString();
    }

    public String nextLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (this.noEnd &&
                !this.isSeparator.isToken(this.next)) {
            sb.append(this.next);
            readNextChar();
        }
        if(sb.length() < 1 && this.isSeparator.isToken(this.next)) {
            sb.append(this.next);
        }
        readNextChar();
        return sb.toString();
    }

    public boolean hasNextWord() throws IOException {
        skip(isWord);
        return this.noEnd && this.next != '\n';
    }

    public boolean hasNextInt() throws IOException {
        skip(isInt);
        return this.noEnd && this.next != '\n';
    }

    public boolean isEmpty() throws IOException {
        return !this.noEnd;
    }

    public String nextWord() throws IOException {
        return next(this.isWord);
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next(this.isInt));
    }
    public class IsSeparator implements IsToken {
        public boolean isToken(char c) {
            return c == '\n' || c == '\r';
        }
    }
    public interface IsToken {
        boolean isToken(char c);
    }
    public class IsWhite implements IsToken {
        public boolean isToken(char c) {
            return Character.isWhitespace(c);
        }
    }
    public class IsWord implements IsToken {
        public boolean isToken(char c) {
            if (Character.isLetter(c))
                return true;
            if (Character.getType(c) == Character.DASH_PUNCTUATION)
                return true;
            return c == '\'';
        }
    }
    public class IsInt implements IsToken {
        public boolean isToken(char c) {
            return Character.isDigit(c) || c == '-';
        }
    }
}

