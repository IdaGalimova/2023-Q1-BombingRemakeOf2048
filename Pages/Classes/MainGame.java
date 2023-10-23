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
        System.out.println(gameSetup.getScore());


        gameSetup.moveTiles("up");
        gameSetup.printGrid();

        System.out.println(gameSetup.getScore());


        gameSetup.moveTiles("right");
        gameSetup.printGrid();

        System.out.println(gameSetup.getScore());

        gameSetup.moveTiles("down");
        gameSetup.printGrid();

        System.out.println(gameSetup.getScore());

        // gameSetup.moveTiles("left");
        // gameSetup.printGrid();
    }

    public static void main(String[] args) {
        new MainGame().run();
    }
}
