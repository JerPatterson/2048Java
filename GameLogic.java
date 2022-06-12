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

        // Minor tests of the methods
        int testRow = 2;
        int testColumn = 2;
        System.out.printf("\nFrom the position (%d, %d)", testRow, testColumn);

        System.out.println("\n\nNext Up: ");
        System.out.println(getNextValueUp(testRow, testColumn));
        System.out.println("Next Down: ");
        System.out.println(getNextValueDown(testRow, testColumn));
        System.out.println("Next Left: ");
        System.out.println(getNextValueLeft(testRow, testColumn));
        System.out.println("Next Right: ");
        System.out.println(getNextValueRight(testRow, testColumn));

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
                if (isValidHorizontalMove() 
                    || isValidVerticalMove()) {
                    return false;
                }
            }
        }
        
        return true;
    }


    private void makeMove(char move) {
        if (move == RIGHT_MOVE) {
            //TODO
        }

        else if (move == LEFT_MOVE) {
            //TODO
        }

        else if (move == UP_MOVE) {
            //TODO
        }

        else if (move == DOWN_MOVE) {
            //TODO
        }
    }


    private boolean isValidHorizontalMove() {
        for (int i = 0; i < HEIGHT; ++ i) {
            for (int j = 0; j < LENGHT - 1; ++j) {
                if (grid[i][j] == 0) {
                    return true;
                }
                else if (getVeryNextRight(i, j) == grid[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidVerticalMove() {
        for (int i = 0; i < LENGHT; ++ i) {
            for (int j = 0; j < HEIGHT - 1; ++j) {
                if (grid[j][i] == 0) {
                    return true;
                }
                else if (getVeryNextDown(j, i) == grid[j][i]) {
                    return true;
                }
            }
        }
        return false;
    }


    private int getVeryNextLeft(final int row, final int column) {
        return row > 0 ? grid[row][column - 1] : -1;
    }

    private int getVeryNextRight(final int row, final int column) {
        return row < LENGHT ? grid[row][column + 1] : -1;
    }

    private int getVeryNextUp(final int row, final int column) {
        return row > 0 ? grid[row - 1][column] : -1;
    }

    private int getVeryNextDown(final int row, final int column) {
        return row < HEIGHT ? grid[row + 1][column] : -1;
    }


    private int getNextValueLeft(final int row, int column) {
        --column;
        for (int i = column; i >= 0; --i) {
            if (grid[row][i] != 0) {
                return grid[row][i];
            }
        }

        return -1;
    }

    private int getNextValueRight(final int row, int column) {
        ++column;
        for (int i = column; i < LENGHT; ++i) {
            if (grid[row][i] != 0) {
                return grid[row][i];
            }
        }

        return -1;
    }

    private int getNextValueUp(int row, final int column) {
        --row;
        for (int i = row; i >= 0; --i) {
            if (grid[i][column] != 0) {
                return grid[i][column];
            }
        }

        return -1;
    }

    private int getNextValueDown(int row, final int column) {
        ++row;
        for (int i = row; i < HEIGHT; ++i) {
            if (grid[i][column] != 0) {
                return grid[i][column];
            }
        }

        return -1;
    }

}