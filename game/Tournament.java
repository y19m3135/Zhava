package game;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.function.Predicate;

public class Tournament {
//    static Player[][][] translate(int N, Player[] players)  {
//        int[][][] games = GamesCalendar.gen(N);
//        Player[][][] t = new Player[N - 1][N / 2][2];
//        for (int i = 0; i < N - 1; i++) {
//            for (int j = 0; j < N / 2; j++) {
//                t[i][j][0] = players[games[i][j][0] - 1];
//                t[i][j][1] = players[games[i][j][1] - 1];
//            }
//        }
//        return t;
//    }
    public static void main(String[] args) {
        myScanner in = new myScanner(System.in);
        final int c;
        while (true) {
            System.out.print("enter c: ");
            String s = in.nextToken();
            if (s.chars().allMatch(Character::isDigit)) {
                c = Integer.parseInt(s);
                break;
            }
        }
        final int N;
        while (true) {
            System.out.print("Number of players: ");
            String s = in.nextToken();
            if (s.chars().allMatch(Character::isDigit)) {
                N = Integer.parseInt(s);
                break;
            }
        }
        Player[] players = new Player[N];
        Map<Character, Class<? extends Player>> playerCodes = Map.of(
                'H', HumanPlayer.class,
                'D', Drawer.class,
                'S', SequentialPlayer.class,
                'R', RandomPlayer.class
        );
        for (int i = 0; i < N; i++) {
            while (true) {
                System.out.println("enter char for player: \nH - for humanplayer \nS - sequential \nR - random \nD - drawer");
                String s = in.nextToken();
                if (s.length() == 1 && playerCodes.containsKey(s.charAt(0))) {
                    try {
                        players[i] = playerCodes.get(s.charAt(0)).getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        int[][][] gamesnums = GamesCalendar.gen(N);
        Predicate<String> ISNUM = (st) -> st.chars().allMatch(Character::isDigit);
        String sn, sm, sk;
        int m, n, k;
        while (true) {
            System.out.print("What about m,n,k now? ");
            sm = in.nextToken();
            sn = in.nextToken();
            sk = in.nextToken();
            if (ISNUM.test(sm) && ISNUM.test(sn) && ISNUM.test(sk)) {
                n = Integer.parseInt(sn);
                m = Integer.parseInt(sm);
                k = Integer.parseInt(sk);
                if (n > 0 && k > 0 && m > 0) {
                    break;
                }
            }
        }
        int[][][] scores = new int[N][N][2*c];
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N / 2; j++) {
                int first = gamesnums[i][j][0] - 1;
                int second = gamesnums[i][j][1] - 1;
                for (int l = 0; l < c; l++) {
                    Game game = new Game(false, players[first], players[second]);
                    int res = game.play(new NMKBoard(m,n,k));
                    switch (res) {
                        case 0:
                            scores[first][second][l] = 1;
                            scores[second][first][l] = 1;
                            break;
                        case 1:
                            scores[first][second][l] = 3;
                            scores[second][first][l] = 0;
                            break;
                        case 2:
                            scores[first][second][l] = 0;
                            scores[second][first][l] = 3;
                            break;
                    }
                    game = new Game(false, players[second], players[first]);
                    res = game.play(new NMKBoard(m,n,k));
                    switch (res) {
                        case 0:
                            scores[first][second][l+c] = 1;
                            scores[second][first][l+c] = 1;
                            break;
                        case 1:
                            scores[first][second][l+c] = 0;
                            scores[second][first][l+c] = 3;
                            break;
                        case 2:
                            scores[first][second][l+c] = 3;
                            scores[second][first][l+c] = 0;
                            break;
                    }
                }
            }
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N/2; j++) {
                int first = gamesnums[i][j][0] - 1;
                int second = gamesnums[i][j][1] - 1;
                System.out.print(first + ": ");
                for (int l = 0; l < 2 * c; l++) {
                    System.out.print(scores[first][second][l] + " ");
                }
                System.out.print("\n" + second + ": ");
                for (int l = 0; l < 2 * c; l++) {
                    System.out.print(scores[second][first][l] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }


//
//
//        for (int i = 0; i < 2 * c; i += 2) {
//
//            Game game = new Game(false, first, second);
//            int res = game.play(new NMKBoard(m,n,k));
//            switch (res) {
//                case 0:
//                    scores[i][0] = 1;
//                    scores[i][1] = 1;
//                    break;
//                case 1:
//                    scores[i][0] = 3;
//                    scores[i][1] = 0;
//                    break;
//                case 2:
//                    scores[i][0] = 0;
//                    scores[i][1] = 3;
//                    break;
//            }
//            game = new Game(false, second, first);
//            int res2 = game.play(new NMKBoard(n, m, k));
//            switch (res2) {         //oh no
//                case 0:
//                    scores[i+1][1] = 1;
//                    scores[i+1][0] = 1;
//                    break;
//                case 1:
//                    scores[i+1][1] = 3;
//                    scores[i+1][0] = 0;
//                    break;
//                case 2:
//                    scores[i+1][1] = 0;
//                    scores[i+1][0] = 3;
//                    break;
//            }
//        }
//        System.out.print("\nScores:\n1: ");
//        int sum = 0;
//        for (int i = 0; i < 2 * c; i++) {
//            sum += scores[i][0];
//            System.out.print(scores[i][0] + " ");
//        }
//        System.out.print(" - " + sum + "\n2: ");
//        sum = 0;
//        for (int i = 0; i < 2 * c; i++) {
//            sum += scores[i][1];
//            System.out.print(scores[i][1] + " ");
//        }
//        System.out.print(" - " + sum);
    }
}
