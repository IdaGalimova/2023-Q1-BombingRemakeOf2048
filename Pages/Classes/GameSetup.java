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

<<<<<<< HEAD
        grid[0][0].setValue(2); // FOR TESTING ONLY, DELETE LATER
        grid[3][0].setValue(2); // FOR TESTING ONLY, DELETE LATER
        grid[2][1].setValue(4); // FOR TESTING ONLY, DELETE LATER
        grid[3][1].setValue(4); // FOR TESTING ONLY, DELETE LATER
        // grid[1][1].setValue(2); // delete later!!! only for testing
        // grid[1][2].setValue(4); // delete later!!! only for testing
        // grid[0][1].setValue(8); // delete later!!! only for testing
=======
        grid [0] [0].setValue(4);
        grid [1] [0].setValue(2);
        grid [0] [1].setValue(4);


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(grid [i][j].getValue() + " ");
            }
            System.out.println();
        }
>>>>>>> SwingBoard

        // Maybe add here methods for the first Tiles to spawn
    }

    public void spawnNewTile() {

    }

    public void printGrid() {
        String splitterLine = "-----------------";
        String endHorizontal = "|";
        System.out.println(splitterLine);
        for (int i = 0; i < 4; i++) {
            String rowLine = endHorizontal + " ";
            for (int j = 0; j < 4; j++) {
                rowLine += grid[i][j].getValue();
                rowLine += " ";
            }
            rowLine += endHorizontal;
            System.out.println(rowLine);
        }
        System.out.println(splitterLine);
    }

    public Tile[][] copyGrid() { // probably dont need this one
        return grid;
    }

    public void moveTiles(String direction) {
        if (direction == "up") {
            moveTilesUp();
        } else if (direction == "down") {
            moveTilesDown();
        } else if (direction == "right") {
            moveTilesRight();
        } else if (direction == "left") {
            moveTilesLeft();
        }
    }

    public void moveTilesUp() {
        for (int col = 0; col < 4; col++) {
            int count = 0;

            for (int row = 0; row < 4; row++) {
                if (grid[row][col].hasValue()) {
                    grid[0 + count][col].setValue(grid[row][col].getValue());

                    // Setting original tile as empty after the tile is moved
                    if (count != row) {
                        grid[row][col].setValue(0);
                    }
                    count++;
                }
            }
        }

        combineTilesUp();
    }

    public void moveTilesDown() {
        for (int col = 0; col < 4; col++) {
            int count = 3;

            for (int row = 3; row >= 0; row--) {
                if (grid[row][col].hasValue()) {
                    grid[count][col].setValue(grid[row][col].getValue());

                    // Setting original tile as empty after the tile is moved
                    if (count != row) {
                        grid[row][col].setValue(0);
                    }
                    count--;
                }

            }
        }
    }

    public void moveTilesRight() {
        for (int row = 0; row < 4; row++) {
            int count = 3;
            for (int col = 3; col >= 0; col--) {
                if (grid[row][col].hasValue()) {
                    grid[row][count].setValue(grid[row][col].getValue());

                    // Setting original tile as empty after the tile is moved
                    if (count != col) {
                        grid[row][col].setValue(0);
                    }
                    count--;
                }
            }
        }
    }

    public void moveTilesLeft() {
        for (int row = 0; row < 4; row++) {
            int count = 0;
            for (int col = 0; col < 4; col++) {
                if (grid[row][col].hasValue()) {
                    grid[row][count].setValue(grid[row][col].getValue());

                    // Setting original tile as empty after the tile is moved
                    if (count != col) {
                        grid[row][col].setValue(0);
                    }
                    count++;
                }
            }
        }
    }

    public void combineTiles() {

    }

    // 2 0 0 0
    // 2 0 0 0
    // 0 0 0 0
    // 0 0 0 0
    public void combineTilesUp() {
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 4; row++) {
                // Tile nextTile = new Tile(0);
                // if (row != 3) {
                // nextTilev = grid[row][col];
                // }

                if (grid[row][col].hasValue()) {
                    if (grid[row][col].getValue() == grid[row + 1][col].getValue()) {
                        grid[row][col].setValue(grid[row + 1][col].getValue() * 2);

                        grid[row + 1][col].setValue(0);
                    }
                }
            }
        }
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