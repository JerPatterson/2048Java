import java.util.Random;
import java.util.Scanner;


public class GameLogic {
    private static final int LENGHT = 4;
    private static final int HEIGHT = 4;

    private static final char RIGHT_MOVE = 'f';
    private static final char LEFT_MOVE = 's';
    private static final char UP_MOVE = 'e';
    private static final char DOWN_MOVE = 'd';
    

    Scanner reader = new Scanner(System.in);
    private int[][] grid = new int[LENGHT][HEIGHT];


    GameLogic() {
        generateRandomStart();
    }


    public void play() {
        generateRandomStart();
        print();

        // TEMPORARY (to test):
        waitForMove();
        print();
        waitForMove();
        print();

        reader.close();
    }


    private void print() {
        for (int[] row : grid) {
            for (int square : row) {
                if (square != 0)
                    System.out.print(square);
                else 
                    System.out.print("_");

                System.out.print(" ");
            }

            System.out.print("\n");
        }
    }


    private void generateRandomStart() {
        for (int[] row : grid) {
            for (int i = 0; i < LENGHT; ++i) {
                row[i] = generateRandomSquare(46);
            }
        }

    }

    private void generateRandomNewSquares() {
        for (int[] row : grid) {
            for (int i = 0; i < LENGHT; ++i) {
                row[i] = (row[i] != 0 ? row[i] : generateRandomSquare(26));
            }
        }
    }


    private int generateRandomSquare(int newSquarePercentage) {
        Random random = new Random();

        final int randomNumber = random.nextInt(100);

        if (randomNumber < newSquarePercentage * 2 / 3)
            return 2;
        else if (randomNumber < newSquarePercentage)
            return 4;

        return 0;
    }


    public void waitForMove() {
        if (!isEnd()) {
            reader.useDelimiter("");
            char newMove = reader.nextLine().charAt(0);
            
            makeMove(newMove);
            generateRandomNewSquares();
        }

    }

    private boolean isEnd() {
        for (int i = 0; i < LENGHT; ++i) {
            for (int j = 0; j < HEIGHT; ++j) {
                if (isValidHorizontalMove(i, j) 
                    || isValidVerticalMove(i, j)) {
                    return false;
                }
            }
        }
        
        return true;
    }


    private void makeMove(char move) {
        if (move == RIGHT_MOVE) {

        }

        else if (move == LEFT_MOVE) {

        }

        else if (move == UP_MOVE) {

        }

        else if (move == DOWN_MOVE) {

        }
    }


    private boolean isValidHorizontalMove(int row, int column) {
        if (grid[row][column] == 0) {
            return true;
        }
        else if (getNextInRow(row, column) == grid[row][column]) {
            return true;
        }

        return false;
    }

    private boolean isValidVerticalMove(int row, int column) {
        if (grid[row][column] == 0) {
            return true;
        }
        else if (getNextInColumn(row, column) == grid[row][column]) {
            return true;
        }

        return false;
    }


    private int getNextInRow(int row, int column) {
        for (int i = column; i < LENGHT; ++i) {
            if (grid[row][i] != 0) {
                return grid[row][i];
            }
        }

        return 0;
    }

    private int getNextInColumn(int row, int column) {
        for (int i = row; i < HEIGHT; ++i) {
            if (grid[i][column] != 0) {
                return grid[i][column];
            }
        }

        return 0;
    }

}