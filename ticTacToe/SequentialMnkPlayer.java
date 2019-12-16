package ticTacToe;

public class SequentialMnkPlayer implements Player {
    private final int m, n;

    public SequentialMnkPlayer(int m, int n) {
        this.m = m;
        this.n = n;
    }

    @Override
    public Move move(Position position, Cell cell) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Move move = new Move(i, j, cell);
                if (position.isValid(move)){
                    return move;
                }
            }
        }
        throw new IllegalStateException("No more moves");
    }
}
