package ticTacToe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HumanMnkPlayer implements Player {
    private final int m, n;
    private final BufferedReader br;

    public HumanMnkPlayer(int m, int n) {
        this.m = m;
        this.n = n;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    private List<String> splitBySpace(String string) {
        List<String> list = new ArrayList<>();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < string.length()) {
            if (Character.isWhitespace(string.charAt(i))) {
                list.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(string.charAt(i));
            }
            i++;
        }
        list.add(sb.toString());
        return list;
    }

    @Override
    public Move move(Position position, Cell cell) {
        try {
            List<Integer> args;
            while (true) {
                try {
                    args = splitBySpace(br.readLine()).stream().map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
                } catch (NumberFormatException e) {
                    System.out.println("Enter numbers");
                    continue;
                }

                if (args.size() != 2) {
                    System.out.println("Enter 2 numbers");
                    continue;
                }
                int r = args.get(0), c = args.get(1);
                final Move move = new Move(r, c, cell);
                if(position.isValid(move)){
                    return move;
                } else {
                    System.out.println("Ching chong your move is wrong");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Move(-1, -1, Cell.E);
        }
    }
}
