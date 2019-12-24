package game;

import java.util.Scanner;

public class GamesCalendar {
    public static int[][][] gen(int N) {
        int[][][] plays = new int[N - 1][N / 2][2];
        for (int i = 0; i < N - 1; i++) {
            plays[i][0][(1 + i) % 2] = N;
        }
        int s = 1;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N / 2; j++) {
                if (plays[i][j][0] != 0) {
                    plays[i][j][1] = s;
                } else {
                    plays[i][j][0] = s;
                }
                s = s == N - 1 ? 1 : s + 1;
            }
        }
        s = N - 1;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 1; j < N / 2; j++) {
                plays[i][j][1] = s;
                s = s == 1 ? N - 1 : s - 1;
            }
        }
        return plays;
    }
    public static void main(String []args) {
        Scanner in  = new Scanner(System.in);
        final int N = in.nextInt();
        int[][][] plays = gen(N);
        for (int i = 0; i < N-1; i++) {
            for (int j = 0; j < N / 2; j++) {
                System.out.print(plays[i][j][0] + " " + plays[i][j][1] + "  ");
            }
            System.out.println();
        }
    }
}
