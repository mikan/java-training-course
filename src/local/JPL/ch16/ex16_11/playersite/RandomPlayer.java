/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch16.ex16_11.playersite;

import java.util.Random;

import local.JPL.ch16.ex16_11.Cell;
import local.JPL.ch16.ex16_11.Game;
import local.JPL.ch16.ex16_11.Player;

/**
 * Random player.
 */
public class RandomPlayer implements Player {

    @Override
    public void play(Game game) {
	while (!game.isFinished()) {
	    Cell[][] map = game.getMap();
	    while (true) {
		int row = nextValue(map.length);
		int col = nextValue(map[row].length);
		// System.out.print("trying " + row + ":" + col + "...");
		if (map[row][col] == Cell.BLANK) {
		    // System.out.println("OK.");
		    game.place(row, col);
		    break;
		} else {
		    // System.out.println("NG.");
		}
	    }
	}
    }

    private int nextValue(int limit) {
	Random random = new Random();
	return random.nextInt(limit);
    }
}
