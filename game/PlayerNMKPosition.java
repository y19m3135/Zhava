package game;
public final class PlayerNMKPosition implements Position {
    private final Position wrap;

    public PlayerNMKPosition(Position wrap) {
        this.wrap = wrap;
    }
    @Override
    public boolean isValid(Move move) {
        return wrap.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return wrap.getCell(r, c);
    }

    @Override
    public int[] dimensions() {
        return wrap.dimensions();
    }

    public String toString() {
        return wrap.toString();
    }
}
