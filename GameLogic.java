import java.util.Random;
import java.util.Scanner;


public class GameLogic {
    private static final int LENGHT = 4;
    private static final int HEIGHT = 4;
    
    private int[][] grid = new int[LENGHT][HEIGHT];


    GameLogic() {
        // Nothing to do
    }


    public void startANewGame() {
        generateRandomStart();
        print();
    }

    public void print() {
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

    public void generateRandomNewSquares() {
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


    public boolean isEnd() {
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

    
    public void makeLeftShift() {
        if (isValidHorizontalMove()) {
            int currentSquare;
            boolean nextValueUsed = false;

            for (int i = 0; i < HEIGHT; ++i) {
                for (int j = 0; j < LENGHT; ++j) {
                    currentSquare = grid[i][j];

                    if (nextValueUsed) {
                        if (currentSquare != 0) {
                            grid[i][j] = 0;
                            nextValueUsed = false;
                        }
                    }
                    else {
                        if (currentSquare == getNextValueRight(i, j)) {
                            grid[i][j] += getNextValueRight(i, j);
                            grid[i][j + 1] = 0;
                        }
                        else if (currentSquare == 0) {
                            grid[i][j] = getNextValueRight(i, j);
                            nextValueUsed = true;
                        }
                    }
                }
            }
        }
    }

    public void makeRightShift() {
        if (isValidHorizontalMove()) {
            int currentSquare;
            boolean nextValueUsed = false;

            for (int i = 0; i < HEIGHT; ++i) {
                for (int j = LENGHT - 1; j >= 0; --j) {
                    currentSquare = grid[i][j];

                    if (nextValueUsed) {
                        if (currentSquare != 0) {
                            grid[i][j] = 0;
                            nextValueUsed = false;
                        }
                    }
                    else {
                        if (currentSquare == 0) {
                            grid[i][j] = getNextValueLeft(i, j);
                            nextValueUsed = true;
                        }
                        if (currentSquare == getNextValueLeft(i, j)) {
                            grid[i][j] += getNextValueLeft(i, j);
                            grid[i][j - 1] = 0;
                        }
                    }
                }
            }
        }
    }

    public void makeUpShift() {
        if (isValidVerticalMove()) {
            int currentSquare;
            int resetHeight;
            boolean nextValueUsed;

            for (int i = 0; i < LENGHT; ++i) {
                resetHeight = 0;
                nextValueUsed = false;
                
                for (int j = 0; j < HEIGHT; ++j) {
                    currentSquare = grid[j][i];

                    if (nextValueUsed) {
                        if (currentSquare != 0) {
                            grid[j][i] = 0;
                            j = resetHeight;
                            nextValueUsed = false;
                        }
                    }
                    else {
                        if (currentSquare == 0) {
                            if (getNextValueDown(j, i) != -1) {
                                grid[j][i] = getNextValueDown(j, i);
                                resetHeight = (j - 1 >= 0 ? j - 1 : 0);
                                nextValueUsed = true;
                            }
                        }
                        else if (currentSquare == getNextValueDown(j, i)) {
                            grid[j][i] += getNextValueDown(j, i);
                            resetHeight = j;
                            nextValueUsed = true;
                        }
                    }
                }
            }
        }
    }

    public void makeDownShift() {
        if (isValidVerticalMove()) {
            int currentSquare;
            int resetHeight;
            boolean nextValueUsed;

            for (int i = 0; i < LENGHT; ++i) {
                resetHeight = HEIGHT - 1;
                nextValueUsed = false;
                
                for (int j = HEIGHT - 1; j >= 0; --j) {
                    currentSquare = grid[j][i];

                    if (nextValueUsed) {
                        if (currentSquare != 0) {
                            grid[j][i] = 0;
                            j = resetHeight;
                            nextValueUsed = false;
                        }
                    }
                    else {
                        if (currentSquare == 0) {
                            if (getNextValueUp(j, i) != -1) {
                                grid[j][i] = getNextValueUp(j, i);
                                resetHeight = (j + 1 < HEIGHT ? j + 1 : HEIGHT - 1);
                                nextValueUsed = true;
                            }
                        }
                        else if (currentSquare == getNextValueUp(j, i)) {
                            grid[j][i] += getNextValueUp(j, i);
                            resetHeight = j;
                            nextValueUsed = true;
                        }
                    }
                }
            }
        }
    }
}