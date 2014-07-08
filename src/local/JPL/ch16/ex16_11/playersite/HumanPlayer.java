/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch16.ex16_11.playersite;

import java.util.Scanner;

import local.JPL.ch16.ex16_11.Cell;
import local.JPL.ch16.ex16_11.Game;
import local.JPL.ch16.ex16_11.Player;

/**
 * Random player.
 */
public class HumanPlayer implements Player {

    @Override
    public void play(Game game) {
	@SuppressWarnings("resource")
	Scanner scanner = new Scanner(System.in);
	while (!game.isFinished()) {
	    Cell[][] map = game.getMap();
	    while (true) {
		game.printMap();
		System.out.print("[HumanPlayer] (row col) > ");
		int row = scanner.nextInt();
		int col = scanner.nextInt();
		try {
		    if (map[row - 1][col - 1] == Cell.BLANK) {
			    game.place(row - 1, col - 1);
			    break;
			} else {
			    System.err.println("ERROR: Already placed.");
			    continue;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		    System.err.println("ERROR: " + e.toString());
		    continue;
		}
		
	    }
	}
    }
}
