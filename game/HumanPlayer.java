package game;
import java.io.PrintStream;
import java.util.function.Predicate;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final myScanner in;

    public HumanPlayer(final PrintStream out, final myScanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new myScanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        final Predicate<String> IS_INTEGER = s -> (            //u know, we don't need no floats round here
                (
                    (s.startsWith("+") || s.startsWith("-"))
                    && s.substring(1).chars().allMatch(Character::isDigit)
                ) || s.chars().allMatch(Character::isDigit)
        );

        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int trow, tcol;

            String srow = in.nextToken();
            if (!IS_INTEGER.test(srow)) trow = -1;
            else trow = Integer.parseInt(srow);

            String scol = in.nextToken();
            if (IS_INTEGER.test(scol)) tcol = Integer.parseInt(scol);
            else tcol = -1;             //i love edinoobraznost' but not now

            final Move move = new Move(trow, tcol, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
