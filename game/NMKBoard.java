package game;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class NMKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int n,m,k;


    public NMKBoard(int n, int m, int k) {
        this.cells = new Cell[n][m];
        this.n = n;
        this.m = m;
        this.k = k;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    public static class count {
        protected final Position position;
        protected final int[] place;
        protected final Move move;
        protected final int n,m;

        protected static final Function<int[], int[]> N  = (int[] a) -> new int[]{a[0] - 1, a[1]    }; //this place is cursed, avoid being here
        protected static final Function<int[], int[]> NE = (int[] a) -> new int[]{a[0] - 1, a[1] + 1};                                     //GLORY TO COPY-PASTE!
        protected static final Function<int[], int[]> E  = (int[] a) -> new int[]{a[0],     a[1] + 1};
        protected static final Function<int[], int[]> SE = (int[] a) -> new int[]{a[0] + 1, a[1] + 1};
        protected static final Function<int[], int[]> S  = (int[] a) -> new int[]{a[0] + 1, a[1]    };
        protected static final Function<int[], int[]> SW = (int[] a) -> new int[]{a[0] + 1, a[1] - 1};
        protected static final Function<int[], int[]> W  = (int[] a) -> new int[]{a[0],     a[1] - 1};
        protected static final Function<int[], int[]> NW = (int[] a) -> new int[]{a[0] - 1, a[1] - 1};

        count(final Position position, final Move move) {
            this.position = position;
            this.place = new int[]{move.getRow(),move.getColumn()};
            this.move = move;
            this.n = position.dimensions()[0];
            this.m = position.dimensions()[1];
        }
                                                                                                                //FINAL EVERYTHING
        protected int cou(Function<int[], int[]> where, int[] pos, Cell whom) {
            if (0 <= pos[0] && pos[0] < n
                    && 0 <= pos[1] && pos[1] < m
                    && position.getCell(pos[0], pos[1]) == whom) {
                return 1 + cou(where, where.apply(pos), whom);
            } else { return 0; }
        }
        int getScore() {
            Cell who = move.getValue();
            return List.of(
                    cou(N,  place, who) + cou(S,  place, who) - 1,
                    cou(NW, place, who) + cou(SE, place, who) - 1,
                    cou(E,  place, who) + cou(W,  place, who) - 1,
                    cou(SW, place, who) + cou(NE, place, who) - 1     // -1 because (place)'s being checked twice
            ).stream().max(Comparator.comparingInt(i -> i)).get();
        }
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) return Result.LOSE;
        cells[move.getRow()][move.getColumn()] = move.getValue();
        count c = new count(this, move);
        if (c.getScore() == this.k) return Result.WIN;
        if (Arrays.stream(cells).flatMap(Arrays::stream).noneMatch(i -> i == Cell.E)) return Result.DRAW;
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
//        if (!isValid(move)) {
//            return Result.LOSE;
//        }
//
//        cells[move.getRow()][move.getColumn()] = move.getValue();
//
//        int inDiag1 = 0;
//        int inDiag2 = 0;
//        int empty = 0;
//        for (int u = 0; u < this.n; u++) {
//            int inRow = 0;
//            int inColumn = 0;
//            for (int v = 0; v < this.m; v++) {
//                if (cells[u][v] == turn) {
//                    inRow++;
//                }
//                if (cells[v][u] == turn) {
//                    inColumn++;
//                }
//                if (cells[u][v] == Cell.E) {
//                    empty++;
//                }
//            }
//            if (inRow == this.k || inColumn == this.k) {
//                return Result.WIN;
//            }
//            if (cells[u][u] == turn) {
//                inDiag1++;
//            }
//            if (cells[u][2 - u] == turn) {
//                inDiag2++;
//            }
//        }
//        if (inDiag1 == this.k || inDiag2 == this.k) {
//            return Result.WIN;
//        }
//        if (empty == 0) {
//            return Result.DRAW;
//        }
//
//        turn = turn == Cell.X ? Cell.O : Cell.X;
//        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (int r = 0; r < n; r++) {
            sb.append("\n");
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }

    public int[] dimensions() {
        return new int[]{this.n, this.m, this.k};
    }
}
