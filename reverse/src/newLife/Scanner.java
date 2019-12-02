package reverse.src.newLife;

import java.io.*;

interface IsToken {
    boolean isToken(char c);
}

public class Scanner implements AutoCloseable{
    private char[] buffer;
    private Reader reader;
    private int first = 0;
    private int size = -1;

    public Scanner(Reader reader, int len) {
        this.reader = reader;
        this.first = 0;
        this.buffer = new char[len];
    }

    public Scanner(File f, String cs) throws FileNotFoundException, UnsupportedEncodingException {
        this(new InputStreamReader(new FileInputStream(f), cs), 88888);
    }

    public Scanner(InputStream f) {
        this(new InputStreamReader(f), 88888);
    }

    public Scanner(String s) {
        this(new StringReader(s), 88888);
    }

    private boolean readNext() throws IOException {
        size = reader.read(buffer);
        first = 0;
        return size > 0;
    }

    public boolean hasNext() throws IOException {
        return !(size <= 0 || first >= size)  || readNext();
    }

    private void skip(IsToken checker) throws IOException {
        while (hasNext() && !checker.isToken(buffer[first]) && buffer[first] != '\n') {
            first++;
        }
    }
    public void skipSeparator(IsToken checker) throws IOException {
        while (hasNext()) {
            if (!checker.isToken(buffer[first]) || buffer[first++] == '\n') {
                break;
            }
        }
    }

    public boolean isNextLine(IsToken checker) throws IOException {
        skip(checker);
        return !hasNext() || buffer[first] == '\n';
    }

    public boolean isEmpty(IsToken checker) throws IOException {
        skip(checker);
        return !hasNext();
    }

    public String next(IsToken checker) throws IOException {
        skip(checker);
        StringBuilder result = new StringBuilder();
        while (hasNext()
                && first < size
                && checker.isToken(buffer[first])
        ) {
            result.append(buffer[first++]);
        }
        return result.toString();
    }

    public boolean hasNext(IsToken checker) throws IOException {
        while (hasNext() && !checker.isToken(buffer[first])) {
            first++;
        }
        return hasNext();
    }

    public String nextWord(IsToken checker) throws IOException {
        return next(checker);
    }

    public int nextInt(IsToken checker) throws IOException {
        return Integer.parseInt(next(checker));
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
