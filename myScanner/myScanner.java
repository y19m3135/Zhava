package game;
import java.io.*;
import java.util.InputMismatchException;
import java.util.function.Function;
import java.util.function.Predicate;

public class myScanner {
    private final BufferedReader inputFile;
    private char last = 0;
    private StringBuilder queue = new StringBuilder();

    /* kinda mention to myself
        to use for next_.
        next_ method used to check every symbol in a queue -
        - though these symbols were added by hasNext_ method.
        i want hasNext_ to indicate if there are already parsed
        things next_ can get from the queue and where to find them.
        next_ will clear this after used.

        {code, startIndex, endIndex}
        code = 0 - nothing
               1 - line
               2 - int/long
               3 - word
               4 - any
        startIndex, endIndex - in queue
    */
    private int[] isThereCache = new int[3];

    private final static Predicate<Character> CHECK_FOR_INT = (c) -> (
            c == '-'
                    || Character.isDigit(c)
                    || c == '+'
    );
    private final static Predicate<Character> CHECK_FOR_WORD = (c) -> (
            c == '\''
                    || Character.getType(c) == Character.DASH_PUNCTUATION
                    || Character.isLetter(c)
    );
    private final static Predicate<Character> CHECK_FOR_LINE = (c) -> (
            c != '\n' && c != (char) -1
    );
    private final static Predicate<Character> CHECK_FOR_TOKEN = (c) -> (
            !Character.isWhitespace(c) && c != (char) -1
    );

    private boolean isClosed = false;

    public myScanner(File in) throws FileNotFoundException {
        inputFile = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(in)
                )
        );
    }
    public myScanner(InputStream in) {
        inputFile = new BufferedReader(
                new InputStreamReader(in)
        );
    }
    public myScanner(String in) {
        inputFile = new BufferedReader(
                new StringReader(in)
        );
    }

    /** Method returning a char from queue or file.
     also closes input when reached eof. or just tries to*/
    private char get() {
        if (isClosed && queue.length() == 0) {
            return (char) -1;
        }
        if (queue.length() == 0) {
            try {
                last = (char) inputFile.read();
            } catch (IOException e) {
                last = (char) -1;
            }
            if (last == (char) -1) {
                isClosed = true;
                try { inputFile.close(); } catch (IOException ignored) {}
            }
            return last;
        } else {
            last = queue.charAt(0);
            queue.deleteCharAt(0);
            return last;
        }
    }

    /**Main method for determining if there is smth either in line or in file.
     searches for first sequence of chars satisfying given Predicate. Returns true if there is any

     searches one-char-at-once in file or in queue using get()*/
    private boolean hasNext(Predicate<Character> allowed, int whatSearchingFor, boolean searchInLine) {
        String res = "";
        String bufferedWhitespaces = "";
        int startIndex = 0;
        int endIndex = -1;

        do {
            last = get();
            if (allowed.test(last)) {
                if (endIndex == -1) endIndex = startIndex;
                else endIndex++;
                res += String.valueOf(last);
            } else {
//                 if (disallowed.test(last)) return false;
                if (!res.isEmpty()) {
                    break;
                } else {
                    if (searchInLine) {
                        if (!CHECK_FOR_LINE.test(last)) return false;
                    } else {
                        if (last == (char) -1) return false;
                    }
                    bufferedWhitespaces += String.valueOf(last);
                    startIndex++;
                }
            }
        } while (true);

        isThereCache[0] = whatSearchingFor;
        isThereCache[1] = startIndex;
        isThereCache[2] = endIndex;
        if (last != (char) -1) queue.insert(0, last);
        queue.insert(0, res)
                .insert(0, bufferedWhitespaces);
        last = 0;
        return true;
    }
    /**Methods for determining if there is smth. Calling main method, sending Predicate, num for cache, boolean for in-line-searching*/
    public boolean hasNextInt()   { return hasNext(CHECK_FOR_INT,   2, false); }
    public boolean hasNextLong()  { return hasNext(CHECK_FOR_INT,   2, false); }
    public boolean hasNextWord()  { return hasNext(CHECK_FOR_WORD,  3, false); }
    public boolean hasNextLine()  { return hasNext(CHECK_FOR_LINE,  1, false); }
    public boolean hasNextToken() { return hasNext(CHECK_FOR_TOKEN, 4, false); }

    public boolean hasNextIntInLine()  { return hasNext(CHECK_FOR_INT,  2, true); }
    public boolean hasNextLongInLine() { return hasNext(CHECK_FOR_INT,  2, true); }
    public boolean hasNextWordInLine() { return hasNext(CHECK_FOR_WORD, 3, true); }

    /** Main method to get things.
     if in cache there is something that caller method is looking for, splits queue
     else: searches for first sequence of chars satisfying Predicate and returns it*/
    private String next(Predicate<Character> allowed, int whatSearchingFor) {
        String res = "";

        if (whatSearchingFor == isThereCache[0]) {
            res = queue.substring(isThereCache[1], isThereCache[2] + 1);
            queue.delete(0, isThereCache[2] + 1);
            isThereCache[0] = 0;
            return res;
        }

        do {
            last = get();
            if (last == (char) -1) break;

            if (allowed.test(last)) {
                res += String.valueOf(last);
            } else {
                if (res.isEmpty()) { continue; }
                else {
                    queue.insert(0, last);
                    break;
                }
            }
        } while(true);
        last = 0;
        return res;
    }

    /** Methods for getting things. Send Predicates and num to look for in cache to main method, parse if needed*/
    public int nextInt() {
        return Integer.parseInt(next(CHECK_FOR_INT, 2));
    }
    public long nextLong() {
        return Long.parseLong(next(CHECK_FOR_INT, 2));
    }
    public String nextWord() {
        return next(CHECK_FOR_WORD, 3);
    }
    public char nextChar() {
        return get();
    }
    public String nextToken() {
        return next(CHECK_FOR_TOKEN, 4);
    }

    public String nextLine() {
        String res = "";
        char c;
        do {
            c = get();
            res += String.valueOf(c);
        } while (CHECK_FOR_LINE.test(c));
        return res;
    }
}
