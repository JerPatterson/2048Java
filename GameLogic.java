import java.util.Random;


public class GameLogic {
    private static final int LENGHT = 4;
    private static final int HEIGHT = 4;

    private int[][] grid = new int[LENGHT][HEIGHT];


    GameLogic() {
        generateRandomStart();
    }


    public void play() {
        generateRandomStart();
        print();

        // TEMPORARY (to test):
        System.out.println("\nNouvelle case (test):\n");
        generateRandomNewSquares();
        print();
    }


    private void print() {
        for (int[] row : grid) {
            for (int square : row) {
                if (square != 0)
                    System.out.print(square);
                else 
                    System.out.print(0);

                System.out.print(" ");
            }

            System.out.print("\n");
        }
    }


    private void generateRandomStart() {
        for (int[] row : grid) {
            for (int i = 0; i < LENGHT; ++i) {
                row[i] = generateRandomSquare(50);
            }
        }

    }

    private void generateRandomNewSquares() {
        for (int[] row : grid) {
            for (int i = 0; i < LENGHT; ++i) {
                row[i] = (row[i] != 0 ? row[i] : generateRandomSquare(28));
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

}