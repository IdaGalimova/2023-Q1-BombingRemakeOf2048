// Maybe we change this file into something else, not sure yet:
package Classes;
public class MainGame {
    private GameSetup gameSetup;

    public MainGame() {
        gameSetup = new GameSetup();
    }

    void run() { 
        System.out.println(); // change this later
    }

    public static void main(String[] args) {
        new MainGame().run();
    }
}
