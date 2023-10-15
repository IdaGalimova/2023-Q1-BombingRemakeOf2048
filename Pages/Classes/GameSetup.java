package Classes;

import java.util.Arrays;

public class GameSetup {
    private Tile[][] grid;
    private int score;

    public GameSetup() {
        grid = new Tile[4][4];
        score = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = new Tile(0);
            }
        }

        grid[1][1].setValue(2); // delete later!!! only for testing
        grid[1][2].setValue(4); // delete later!!! only for testing
        grid[0][1].setValue(8); // delete later!!! only for testing

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

    // 0 0 0 2
    // 0 0 0 0
    // 0 0 0 0
    // 0 0 0 0
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
}