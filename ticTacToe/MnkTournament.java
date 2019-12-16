package ticTacToe;

import ticTacToe.Game;
import ticTacToe.MnkBoard;
import ticTacToe.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MnkTournament {

    private final List<Player> participants;
    private int m, n, k;
    private int[][] results, gameTable;

    public MnkTournament(List<Player> list, int m, int n, int k) {
        participants = List.copyOf(list);

        results = new int[participants.size()][participants.size()];
        gameTable = new int[participants.size()][participants.size()];
        for (int[] row : results) {
            Arrays.fill(row, 0);
        }
        for (int[] row : gameTable){
            Arrays.fill(row, 0);
        }

        this.m = m;
        this.n = n;
        this.k = k;
    }

    public void playTournament() {
        for (int day = 1; day < participants.size(); day++) {
            boolean[] hasPlayed = new boolean[participants.size()];
            System.out.println("Day " + day + ":");
            Arrays.fill(hasPlayed, false);
            for (int j = 0; j < participants.size(); j++) {
                if ((day + j) % participants.size() == j || hasPlayed[j] || hasPlayed[(day + j) % participants.size()]) {
                    continue;
                }
                Game game = new Game(false, participants.get(j), participants.get((j + day) % participants.size()));
                int result = game.play(new MnkBoard(m, n, k));
                int winner = -1;
                switch (result) {
                    case 0:
                        results[day][j] = results[day][(j + day) % participants.size()] = 1;
                        winner = -1;
                        break;
                    case 1:
                        results[day][j] = 3;
                        results[day][(j + day) % participants.size()] = 0;
                        winner = j;
                        break;
                    case 2:
                        results[day][j] = 0;
                        results[day][(j + day) % participants.size()] = 3;
                        winner = (j+day)%participants.size();
                        break;
                }
                hasPlayed[j] = hasPlayed[(day + j) % participants.size()] = true;
                gameTable[j][(day+j)%participants.size()] = results[day][j];
                gameTable[(day+j)%participants.size()][j] = results[day][(day+j)%participants.size()];
                System.out.println(" " + j + " : " + (day + j) % participants.size() + (winner == -1 ? " Draw" : " Player " + winner + " wins"));
            }

        }

    }

    public void findWinner(){
        for(int day = 1; day < participants.size(); day++){
            for (int player = 0; player<participants.size(); player++){
                results[day][player] += results[day-1][player];
            }
        }

        int[] finalResults = results[participants.size()-1];
        int max=0;
        for (int points : finalResults){
            if (points>max){
                max = points;
            }
        }
        List<Integer> winners = new ArrayList<>();
        for(int i=0; i<participants.size(); i++){
            if (finalResults[i]==max){
                winners.add(i);
            }
        }
        if(winners.size()==1){
            System.out.println("Winner is " + winners.get(0));
        } else {
            System.out.print("Winners are ");
            for(int ind : winners){
                System.out.print(ind + " ");
            }
        }
    }

    public void printGameTable(){
        for (int[] row : gameTable){
            for(int res : row){
                System.out.print(res + " ");
            }
            System.out.println();
        }
    }
}
