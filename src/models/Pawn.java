package models;

public class Pawn {
    private Position position;
    private char owner;
    private boolean isActive;

    public Pawn(char owner) {
        this.owner = owner;
        this.position = null;
        this.isActive = false;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public char getOwner() {
        return owner;
    }

    public void setOwner(char owner) {
        this.owner = owner;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(Position startPosition) {
        this.isActive = true;
        this.position = startPosition;
    }

    public void deactivate() {
        this.isActive = false;
        this.position = null;
    }
}
