package ticTacToe;

import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        final Game game = new Game(true, new HumanMnkPlayer(5, 8), new RandomMnkPlayer(5, 8));
        game.play(new MnkBoard(5, 8, 4));

    }
}