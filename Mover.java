public class Mover {
    private static final char RIGHT_MOVE = 'f';
    private static final char LEFT_MOVE = 's';
    private static final char UP_MOVE = 'e';
    private static final char DOWN_MOVE = 'd';


    public void makeMove(char move, GameLogic game) {
        if (move == RIGHT_MOVE) {
            game.makeRightShift();
        }

        else if (move == LEFT_MOVE) {
            game.makeLeftShift();
        }

        else if (move == UP_MOVE) {
            game.makeUpShift();
        }

        else if (move == DOWN_MOVE) {
            game.makeDownShift();
        }
    }
 
}
