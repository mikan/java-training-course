/*
 * Copyright(C) 2014 Yutaka Kato
 */
package local.JPL.ch16.ex16_11;

public enum Cell {
    BLANK('_'), FIRST('#'), SECOND('$');

    private final char character;

    Cell(char character) {
	this.character = character;
    }

    @Override
    public String toString() {
	return Character.toString(character);
    }
}
