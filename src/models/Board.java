package models;

public class Board {
    Object[][] board = new Object[15][15];
    Dice dice;

    public Board(Dice dice) {
        this.dice = dice;
    }

    public void initializeBoard() {
        for (byte i = 0; i < 15; i++) {
            for (byte j = 0; j < 15; j++) {
                if (i >= 0 && i <= 5 && (j >= 0 && j <= 5 || j >= 9) ||
                        i >= 9 && (j >= 0 && j <= 5 || j >= 9)
                        || i >= 6 && i <= 8 && j >= 6 && j <= 8)
                    board[i][j] = "";
                else if ((i >= 9 && i <= 13) && j == 7 || (i == 13 && j == 6))
                    board[i][j] = new Cell(i, j, false, true, 'y');
                else if ((i >= 1 && i <= 5) && j == 7 || (i == 1 && j == 8))
                    board[i][j] = new Cell(i, j, false, true, 'r');
                else if ((j >= 1 && j <= 5) && i == 7 || (i == 6 && j == 1))
                    board[i][j] = new Cell(i, j, false, true, 'b');
                else if ((j >= 9 && j <= 13) && i == 7 || (i == 8 && j == 13))
                    board[i][j] = new Cell(i, j, false, true, 'g');
                else if ((i == 13 && j == 8) || (i == 8 && j == 1) || (i == 1 && j == 6) || (i == 6 && j == 13))
                    board[i][j] = new Cell(i, j, true, false, 'x');
                else board[i][j] = new Cell(i, j, false, false, '.');
            }
        }
    }

    public void placePawnsInHome(Pawn[] pawns, Position[] homePositions) {
        for (int i = 0; i < 4; i++) {
            pawns[i].setPosition(homePositions[i]);
            board[homePositions[i].getX()][homePositions[i].getY()] = pawns[i];
        }
    }

    // Print Without Emojis
    public void print() {
        System.out.println();
        for (byte i = 0; i < 15; i++) {
            for (byte j = 0; j < 15; j++) {
                if (board[i][j] instanceof Cell) {
                    System.out.print(((Cell) board[i][j]).getCharacter());
                    System.out.print("\t");
                } else System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printWithEmojis() {
        System.out.println();
        for (byte i = 0; i < 15; i++) {
            for (byte j = 0; j < 15; j++) {
                if (board[i][j] instanceof Cell) {
                    if (((Cell) board[i][j]).getCharacter() == 'y')
                        System.out.print("🟨");
                    else if (((Cell) board[i][j]).getCharacter() == 'b')
                        System.out.print("🟦");
                    else if (((Cell) board[i][j]).getCharacter() == 'r')
                        System.out.print("🟥");
                    else if (((Cell) board[i][j]).getCharacter() == 'g')
                        System.out.print("🟩");
                    else if (((Cell) board[i][j]).getCharacter() == '.')
                        System.out.print("⬜");
                    else if (((Cell) board[i][j]).getCharacter() == '-')
                        System.out.print(" ");
                    else if (((Cell) board[i][j]).isSafe())
                        System.out.print("❌");
                    System.out.print("\t");

                } else if (board[i][j] instanceof Pawn pawn) {
                    switch (pawn.getOwner()) {
                        case 'y' -> System.out.print("🟡");
                        case 'b' -> System.out.print("🔵");
                        case 'r' -> System.out.print("🔴");
                        case 'g' -> System.out.print("🟢");
                    }
                    System.out.print("\t");
                } else System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
    }


    public void movePawn(Pawn pawn, Position newPosition) {
        Position currentPosition = pawn.getPosition();
        board[currentPosition.getX()][currentPosition.getY()] = new Cell(currentPosition.getX(), currentPosition.getY(), false, false, '.');
        pawn.setPosition(newPosition);
        board[newPosition.getX()][newPosition.getY()] = pawn;
    }

    public void placePawn(Pawn pawn, Position startPosition) {
        Position currentPosition = pawn.getPosition();
        board[currentPosition.getX()][currentPosition.getY()] = new Cell(currentPosition.getX(), currentPosition.getY(), false, false, '-');
        pawn.setActive(startPosition);
        board[startPosition.getX()][startPosition.getY()] = pawn;
    }


}

