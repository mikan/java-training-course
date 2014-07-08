/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch16.ex16_11;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The game.
 */
public class Game {

    private Cell[][] map;

    public static void main(String[] args) {
	String name; // Class name
	while ((name = getNextPlayer()) != null) {
	    try {
		PlayerLoader loader = new PlayerLoader();
		Class<? extends Player> classOf = loader.loadClass(name).asSubclass(Player.class);
		Player player = classOf.newInstance();
		Game game = new Game();
		player.play(game);
		game.reportScore(name);
	    } catch (Exception e) {
		reportException(name, e);
	    }
	}
    }

    // Suppress warning because stream is standard input.
    @SuppressWarnings("resource")
    private static String getNextPlayer() {
	System.out.print("Input player (StubPlayer or RandomPlayer or HumanPlayer) > ");
	String name = new Scanner(System.in).nextLine();
	if (name.equalsIgnoreCase("quit") || name.equalsIgnoreCase("exit"))
	    System.exit(0);
	return name;
    }

    private static void reportException(String name, Exception e) {
	if (name == null)
	    name = "(null)";
	if (name.isEmpty())
	    name = "(empty)";
	System.err.println("[" + name + "] " + e.toString());
	System.exit(0);
    }

    private Game() {
	map = new Cell[][] { { Cell.BLANK, Cell.BLANK, Cell.BLANK },
		{ Cell.BLANK, Cell.BLANK, Cell.BLANK }, { Cell.BLANK, Cell.BLANK, Cell.BLANK } };
    }

    public Cell[][] getMap() {
	return map.clone();
    }

    public void place(int i, int j) {
	place(i, j, Cell.FIRST);
	if (isFinished())
	    return;
	humanInput();
    }

    public void printMap() {
	System.out.println("-------");
	System.out.println("  1 2 3");
	System.out.println("1 " + map[0][0] + " " + map[0][1] + " " + map[0][2]);
	System.out.println("2 " + map[1][0] + " " + map[1][1] + " " + map[1][2]);
	System.out.println("3 " + map[2][0] + " " + map[2][1] + " " + map[2][2]);
	System.out.println("-------");
    }

    private void place(int i, int j, Cell c) {
	if (map == null)
	    throw new IllegalStateException("Game not started.");
	if (i < 0 || j < 0 || i >= map.length || j >= map[0].length)
	    throw new IllegalArgumentException("Illegal position: " + (i + 1) + ":" + (j + 1));
	if (c != Cell.FIRST && c != Cell.SECOND)
	    throw new IllegalArgumentException("Illegal cell: " + c.name());
	if (map[i][j] != Cell.BLANK)
	    throw new IllegalArgumentException("Already placed: " + (i + 1) + ":" + (j + 1));
	map[i][j] = c;
    }

    private void reportScore(String name) {
	printMap();
	int first = 0, second = 0;
	for (Cell[] row : map) {
	    for (Cell cell : row) {
		switch (cell) {
		case FIRST:
		    first++;
		    break;
		case SECOND:
		    second++;
		    break;
		default:
		    break;
		}
	    }
	}
	System.out.println("1st player (" + Cell.FIRST + ") " + first);
	System.out.println("2nd player (" + Cell.SECOND + ") " + second);
    }

    private void humanInput() {
	printMap();
	@SuppressWarnings("resource")
	// because stream is standard input.
	Scanner scanner = new Scanner(System.in);
	System.out.print("(row col) > ");
	try {
	    StringTokenizer token = new StringTokenizer(scanner.nextLine());
	    int row = Integer.parseInt(token.nextToken());
	    int col = Integer.parseInt(token.nextToken());
	    place(row - 1, col - 1, Cell.SECOND);
	} catch (NoSuchElementException e) {
	    System.err.println("ERROR: " + e.toString());
	    humanInput();
	} catch (NumberFormatException e) {
	    System.err.println("ERROR: " + e.toString());
	    humanInput();
	} catch (IllegalArgumentException e) {
	    System.err.println("ERROR: " + e.toString());
	    humanInput();
	} finally {
	}
    }

    public boolean isFinished() {
	// This is size-dependent if-else statements. It can replace to recursive way.
	Cell temp = map[0][0];
	if (temp != Cell.BLANK) {
	    if (temp == map[0][1] && temp == map[0][2])
		return true;
	    if (temp == map[1][0] && temp == map[2][0])
		return true;
	}
	temp = map[1][1];
	if (temp != Cell.BLANK) {
	    if (temp == map[1][0] && temp == map[1][2])
		return true;
	    if (temp == map[0][1] && temp == map[2][1])
		return true;
	    if (temp == map[0][0] && temp == map[2][2])
		return true;
	    if (temp == map[0][2] && temp == map[2][0])
		return true;
	}
	temp = map[2][2];
	if (temp != Cell.BLANK) {
	    if (temp == map[0][2] && temp == map[1][2])
		return true;
	    if (temp == map[2][0] && temp == map[2][1])
		return true;
	}

	// Search blank space
	for (int i = 0; i < map.length; i++) {
	    for (int j = 0; j < map[i].length; j++) {
		if (map[i][j] == Cell.BLANK)
		    return false;
	    }
	}
	return true;
    }
}
