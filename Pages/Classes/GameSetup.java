package Classes;

import java.util.Arrays;

public class GameSetup {
    private Tile[][] grid;
    private int score = 0;

    public GameSetup() {
        grid = new Tile[4][4];
        score = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = new Tile(0);
            }
        }

        grid [0] [0].setValue(4);
        grid [1] [0].setValue(2);
        grid [0] [1].setValue(4);


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(grid [i][j].getValue() + " ");
            }
            System.out.println();
        }

        // Maybe add here methods for the first Tiles to spawn
    }

    public void spawnNewTile() {
        
    }

    public void moveTiles(String direction) { // not sure what data type to assign
                                            // to the direction
    }

    public void combineTiles() {

    }

    public void bombATile(Tile superTile, Tile tileToBomb) {

    } 

    // Method to check if there are any available moves left
    public boolean hasAvailableMoves() {
        return false; // change later
    }

    // Method to check if there is a tile with value "2048"
    public boolean checkVictory() {
        return false; // change later
    }

    public Tile[][] getGrid(){

        return grid;
    }

}