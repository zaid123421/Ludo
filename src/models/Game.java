package models;

import java.util.Objects;
import java.util.Scanner;

public class Game {
    Board board;
    private Dice dice;
    Player[] players;
    private Scanner scanner;
    int roll;

    public Game() {
        board = new Board(dice);
        dice = new Dice();
        scanner = new Scanner(System.in);
        players = new Player[4];
        players[0] = new Player("Yellow Player", 'y');
        players[1] = new Player("Red Player", 'r');
        players[2] = new Player("Blue Player", 'b');
        players[3] = new Player("Green Player", 'g');
    }

    public void startGame() {
        board.initializeBoard();
        board.placePawnsInHome(players[0].getPawns(), new Position[]{new Position((byte) 12, (byte) 0), new Position((byte) 13, (byte) 0), new Position((byte) 12, (byte) 1), new Position((byte) 13, (byte) 1)});
        board.placePawnsInHome(players[1].getPawns(), new Position[]{new Position((byte) 0, (byte) 12), new Position((byte) 0, (byte) 13), new Position((byte) 1, (byte) 12), new Position((byte) 1, (byte) 13)});
        board.placePawnsInHome(players[2].getPawns(), new Position[]{new Position((byte) 0, (byte) 0), new Position((byte) 0, (byte) 1), new Position((byte) 1, (byte) 0), new Position((byte) 1, (byte) 1)});
        board.placePawnsInHome(players[3].getPawns(), new Position[]{new Position((byte) 12, (byte) 12), new Position((byte) 12, (byte) 13), new Position((byte) 13, (byte) 12), new Position((byte) 13, (byte) 13)});
        board.printWithEmojis();
        System.out.println("Welcome to Ludo!");
        play();
    }

    void play() {
        boolean gameRunning = true;
        int currentPlayer = 0;

        while (gameRunning) {
            Player player = players[currentPlayer];
            System.out.println();
            System.out.println(player.getName() + "'s turn!");
            boolean turnActive = true;
             roll = 0;

            while (turnActive) {
                System.out.println("Press D to roll the dice.");
                String input = scanner.next();
                if (input.equalsIgnoreCase("D")) {
                    roll = dice.roll();
                    dice.displayRoll(roll);

                    if (player.getPawnsInHome() > 0) {
                        if (roll == 6) {
                            System.out.println("You rolled a 6! You can activate a pawn.");
                            handleMove(player, true);
                        } else {
                            System.out.println("You need a 6 to activate a pawn. Try again on your next turn.");
                            turnActive = false;
                        }
                    } else {
                        if (roll == 6) {
                            System.out.println("You rolled a 6! You can move a pawn and roll again.");
                            handleMove(player, true);
                        } else {
                            System.out.println("Move your pawn " + roll + " steps.");
                            handleMove(player, false);
                            turnActive = false;
                        }
                    }
                    if (roll != 6) {
                        turnActive = false;
                    }
                } else {
                    System.out.println("Invalid input. Please press D to roll the dice.");
                }
            }
            currentPlayer = (currentPlayer + 1) % 4;
        }
    }

    void handleMove(Player player, boolean canActivate) {
        System.out.println("Select a pawn to move (1-4):");
        int pawnIndex = scanner.nextInt() - 1;
        if (pawnIndex < 0 || pawnIndex >= 4) {
            System.out.println("Invalid choice, try again.");
            return;
        }
        Pawn selectedPawn = player.getPawns()[pawnIndex];
        if (!selectedPawn.isActive() && canActivate) {
            board.placePawn(selectedPawn, getStartingPosition(player.getColor()));
            player.removeFromHome();
            board.printWithEmojis();
        } else if (selectedPawn.isActive()) {
            System.out.println("Enter the number of steps to move:");
            movePawn(selectedPawn, (byte) roll);
        }
    }

    private Position getStartingPosition(char color) {
        return switch (color) {
            case 'y' -> new Position((byte) 13, (byte) 6);
            case 'r' -> new Position((byte) 1, (byte) 8);
            case 'b' -> new Position((byte) 6, (byte) 1);
            case 'g' -> new Position((byte) 8, (byte) 13);
            default -> null;
        };
    }

    private void movePawn(Pawn pawn, byte steps) {
        Position currentPosition = pawn.getPosition();
        Position newPosition = new Position((byte) (currentPosition.getX() + steps), currentPosition.getY());
        board.movePawn(pawn, newPosition);
    }
}
