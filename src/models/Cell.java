package models;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private byte row;
    private byte col;
    private boolean isSafe;
    private boolean isFinal;
    private char character;
    private List<Pawn> pawns;

    // Constructor
    public Cell(byte row, byte col, boolean isSafe, boolean isFinal, char character) {
        this.row = row;
        this.col = col;
        this.isSafe = isSafe;
        this.isFinal = isFinal;
        this.character = character;
        this.pawns = new ArrayList<>();
    }

    // Getters
    public byte getRow() {
        return row;
    }

    public byte getCol() {
        return col;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public boolean isFinal() {
        return isSafe;
    }

    public char getCharacter() {
        return character;
    }

    // Setters
    public void setCharacter(char character) {
        this.character = character;
    }
}
