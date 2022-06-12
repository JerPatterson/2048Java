import java.util.Scanner;


public class Player {
    Scanner reader = new Scanner(System.in);
    GameLogic game = new GameLogic();
    Mover mover = new Mover();


    public void play() {
        game.startANewGame();
        waitForMoves();

        reader.close();
    }


    public void waitForMoves() {
        if (!game.isEnd()) {
            reader.useDelimiter("");
            char newMove = reader.nextLine().charAt(0);
            
            mover.makeMove(newMove, game);
            game.generateRandomNewSquares();
        }

    }
}