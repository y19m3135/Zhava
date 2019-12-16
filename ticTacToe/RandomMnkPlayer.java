package ticTacToe;

import java.util.Random;

public class RandomMnkPlayer implements Player {
    private final int m, n;
    private final Random random;

    public RandomMnkPlayer(int m, int n){
        this.m = m;
        this.n = n;
        random = new Random();
    }


    @Override
    public Move move(Position position, Cell cell) {
        while (true){
            int r = random.nextInt(m);
            int c = random.nextInt(n);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)){
                return move;
            }
        }
    }
}
