/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch16.ex16_11.playersite;

import local.JPL.ch16.ex16_11.Cell;
import local.JPL.ch16.ex16_11.Game;
import local.JPL.ch16.ex16_11.Player;

/**
 * Simple player.
 */
public class StubPlayer implements Player {

    @Override
    public void play(Game game) {
	while (!game.isFinished()) {
	    Cell[][] map = game.getMap();
	    NEXT: for (int i = 0; i < map.length; i++) {
		for (int j = 0; j < map[i].length; j++) {
		    switch (map[i][j]) {
		    case BLANK:
			game.place(i, j);
			break NEXT;
		    case FIRST:
		    case SECOND:
			continue;
		    }
		}
	    }
	}
    }
}
