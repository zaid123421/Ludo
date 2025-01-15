import models.*;

public class Main {
    public static void main(String[] args) {
        Dice dice = new Dice();
        Board board = new Board(dice);
        Game game = new Game();
        game.startGame();


    }
}
