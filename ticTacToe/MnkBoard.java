package ticTacToe;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MnkBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private Cell[][] cells;
    private Cell turn;
    private final int m, n, k;
    private int freeCells;

    public MnkBoard(int m, int n, int k) {
        cells = new Cell[m][n];
        freeCells = m * n;
        this.m = m;
        this.n = n;
        this.k = k;
        turn = Cell.X;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
    }

    private Result checkWin(Move move) {
        int cnt = 0;
        int r = move.getRow();
        int c = move.getColumn();
        Cell val = move.getValue();

        //Горизонтально
        while (c > -1 && cells[r][c] == val) {
            cnt++;
            c--;
        }
        c = move.getColumn() + 1;
        while (c < n && cells[r][c] == val) {
            cnt++;
            c++;
        }

        if (cnt >= k) {
            return Result.WIN;
        }

        //Вертикально
        c = move.getColumn();
        cnt = 0;
        while (r > -1 && cells[r][c] == val) {
            cnt++;
            r--;
        }
        r = move.getRow() + 1;
        while (r < m && cells[r][c] == val) {
            cnt++;
            r++;
        }

        if (cnt >= k) {
            return Result.WIN;
        }

        //Главнодиагонально
        r = move.getRow();
        c = move.getColumn();
        cnt = 0;
        while (r > -1 && c > -1 && cells[r][c] == val) {
            cnt++;
            r--;
            c--;
        }
        r = move.getRow() + 1;
        c = move.getColumn() + 1;
        while (r < m && c < n && cells[r][c] == val) {
            cnt++;
            r++;
            c++;
        }
        if (cnt >= k) {
            return Result.WIN;
        }

        //Побочнодиаганально
        r = move.getRow();
        c = move.getColumn();
        cnt = 0;
        while (c > -1 && r < m && cells[r][c] == val) {
            cnt++;
            c--;
            r++;
        }
        r = move.getRow() - 1;
        c = move.getColumn() + 1;
        while (c < n && r > -1 && cells[r][c] == val) {
            cnt++;
            c++;
            r--;
        }
        return cnt >= k ? Result.WIN : Result.UNKNOWN;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        turn = turn == Cell.X ? Cell.O : Cell.X;
        freeCells--;

        if (checkWin(move) == Result.UNKNOWN) {
            return freeCells == 0 ? Result.DRAW : Result.UNKNOWN;
        } else {
            return Result.WIN;
        }
    }

    @Override
    public boolean isValid(Move move) {
        return move.getColumn() > -1 && move.getColumn() < n
                && move.getRow() > -1 && move.getRow() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E;
    }

    @Override
    public Cell getCell(int r, int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("  ");
        for (int i = 0; i < n; i++) {
            sb.append(i);
        }
        for(int i=0; i<m; i++){
            sb.append('\n');
            sb.append(i).append(' ');
            for(int j=0; j<n; j++){
                sb.append(SYMBOLS.get(cells[i][j]));
            }
        }
        return sb.toString();
    }
}
