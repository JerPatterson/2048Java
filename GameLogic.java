import java.util.Random;


public class GameLogic {
    private static final int LENGHT = 4;
    private static final int HEIGHT = 4;

    private int[][] grid = new int[LENGHT][HEIGHT];


    GameLogic() {
        generateRandomStart();
    }


    public void print() {
        for (int[] row : grid) {
            for (int square : row) {
                System.out.print(square);
                System.out.print(" ");
            }

            System.out.print("\n");
        }
    }

    public void generateRandomStart() {
        for (int[] row : grid) {
            for (int i = 0; i < LENGHT; ++i) {
                row[i] = generateRandomSquare();
            }
        }

    }

    private int generateRandomSquare() {
        Random random = new Random();

        final int randomNumber = random.nextInt(100);

        if (randomNumber < 34)
            return 4;
        else if (randomNumber < 67)
            return 2;

        return 0;
    }

}