package Classes;

/**
 * This class is used for testing if the back-end is working correctly.
 * It is not part of the main game. To run the game, you have to execute 
 * the file Main.java.
 * 
 * @author Ida Galimova
 * @id 1958895
 * @author Skaiste Liutkute
 * @id 2004119
 */
public class TestingBackend {
    private GameSetup gameSetup;

    public TestingBackend() {
        gameSetup = new GameSetup();
    }

    void run() {
        System.out.println("Start of the game:");
        System.out.println();
        gameSetup.printGrid();

        gameSetup.moveTiles("right");
        gameSetup.printGrid();

    }

    public static void main(String[] args) {
        new TestingBackend().run();
    }
}
