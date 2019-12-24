package game;
import java.util.Random;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class RandomPlayer implements Player {
    private final Random random;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        int[] dims = position.dimensions();
        while (true) {
            int r = random.nextInt(dims[0]);
            int c = random.nextInt(dims[1]);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
