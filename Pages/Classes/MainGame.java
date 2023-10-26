// Maybe we change this file into something else, not sure yet:
package Classes;

public class MainGame {
    private GameSetup gameSetup;

    public MainGame() {
        gameSetup = new GameSetup();
    }

    void run() {
        System.out.println("Start of the game:");
        System.out.println();
        gameSetup.printGrid();

        // gameSetup.moveTiles("down");
        // gameSetup.printGrid();

        gameSetup.moveTiles("right");
        gameSetup.printGrid();

        
        gameSetup.bombATile(2, 3, 3, 3);
        gameSetup.printGrid();

        gameSetup.moveTiles("left");
        gameSetup.printGrid();

        gameSetup.bombATile(2, 0, 1, 0);
        gameSetup.printGrid();

        gameSetup.bombATile(2, 0, 0, 0);
        gameSetup.printGrid();

    }

    public static void main(String[] args) {
        new MainGame().run();
    }
}
