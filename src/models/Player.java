package models;

public class Player {
    String name;
    char color;
    Pawn pawns[];
    private int pawnsInHome;
    boolean hasWon;

    public Player(String name, char color) {
        this.name = name;
        this.color = color;
        this.pawns = new Pawn[4];

        for (int i = 0; i < 4; i++) {
            this.pawns[i] = new Pawn(color);
        }
        this.pawnsInHome = 4;
        this.hasWon = false;
    }
    public int getPawnsInHome() {
        return pawnsInHome;
    }

    public void removeFromHome() {
        if (pawnsInHome > 0) {
            pawnsInHome--;

        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public Pawn[] getPawns() {
        return pawns;
    }

    public void setPawns(Pawn[] pwans) {
        this.pawns = pwans;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }


}
