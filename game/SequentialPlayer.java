package game;
/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SequentialPlayer implements Player {
    @Override
    public Move move(final Position position, final Cell cell) {
//        Board board = (Board) position;
//        board.makeMove()
        for (int r = 0; r < position.dimensions()[0]; r++) {
            for (int c = 0; c < position.dimensions()[1]; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
