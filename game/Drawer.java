package game;

import java.util.Comparator;
import java.util.List;

public class Drawer implements Player {     //i'm not sure if this player is winner enough
    @Override                               //UPD: not a winner at all. Not even a drawer
    public Move move(Position position, Cell cell) {
        final int n = position.dimensions()[0];
        final int m = position.dimensions()[1];
        int[] best = new NMKBoard.count(position, new Move(0, 0, cell)) {
            int emptysAround(int[] pos) {
                return
                        (position.isValid(new Move(N.apply(pos), move.getValue())) ? 1 : 0)
                        + (position.isValid(new Move(NW.apply(pos), move.getValue())) ? 1 : 0)
                        + (position.isValid(new Move(W. apply(pos), move.getValue())) ? 1 : 0)
                        + (position.isValid(new Move(SW.apply(pos), move.getValue())) ? 1 : 0)
                        + (position.isValid(new Move(S.apply(pos), move.getValue())) ? 1 : 0)
                        + (position.isValid(new Move(SE.apply(pos), move.getValue())) ? 1 : 0)
                        + (position.isValid(new Move(E.apply(pos), move.getValue())) ? 1 : 0)
                        + (position.isValid(new Move(NE.apply(pos), move.getValue())) ? 1 : 0); //oh no
            }
            int[] getMostDangerousPlace() {
                int[] res = new int[2];
                int maxPriority = 0;
                for (int i = 0; i<n; i++) {
                    for (int j = 0; j<m; j++) {
                        if (position.getCell(i, j) == Cell.E) {
                            int[] p = new int[]{i,j};
                            int priority = List.of(
                                    cou(N, N.apply(p), Cell.O) + cou(S, S.apply(p), Cell.O) +
                                    cou(N, N.apply(p), Cell.X) + cou(S, S.apply(p), Cell.X),

                                    cou(W, W.apply(p), Cell.O) + cou(E, E.apply(p), Cell.O) +
                                    cou(W, W.apply(p), Cell.X) + cou(E, E.apply(p), Cell.X),

                                    cou(SW, SW.apply(p), Cell.O) + cou(NE, NE.apply(p), Cell.O) +
                                    cou(SW, SW.apply(p), Cell.X) + cou(NE, NE.apply(p), Cell.X),    //PRAISE THE COPY_PASTE

                                    cou(NW, NW.apply(p), Cell.O) + cou(SE, SE.apply(p), Cell.O) +
                                    cou(NW, NW.apply(p), Cell.X) + cou(SE, SE.apply(p), Cell.X)
                            ).stream().map((c) -> c + emptysAround(p)).max(Comparator.comparingInt(k -> k)).get();
                            if (priority > maxPriority) {
                                maxPriority = priority;
                                res[0] = i; res[1] = j;
                            }
                        }
                    }
                }
                return res;
            }
        }.getMostDangerousPlace();
        return new Move(best[0], best[1], cell);
    }
}
