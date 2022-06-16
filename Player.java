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
        while (!game.isEnd()) {
            reader.useDelimiter("");
            char newMove = reader.nextLine().charAt(0);
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n");
            
            mover.makeMove(newMove, game);
            game.generateRandomNewSquares();
            game.print();
        }

    }
}