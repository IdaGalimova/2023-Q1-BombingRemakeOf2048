package Classes;

import java.util.Random;
import java.awt.Color;

public class GameSetup {
    private ColorManager colorManager;
    private Tile[][] grid;
    private int score = 0;

    public GameSetup() {
        grid = new Tile[4][4];
        score = 0;
        colorManager = new ColorManager(0); // CHANGE MODE HERE LATER

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = new Tile(0);
            }
        }

        grid[0][0].setValue(1024);
        grid[1][0].setValue(1024);
        grid[1][3].setValue(2);
        grid[1][1].setValue(8);

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
            combineTilesUp();
            moveTilesUp();
        } else if (direction == "down") {
            moveTilesDown();
            combineTilesDown();
            moveTilesDown();
        } else if (direction == "right") {
            moveTilesRight();
            combineTilesRight();
            moveTilesRight();
        } else if (direction == "left") {
            moveTilesLeft();
            combineTilesLeft();
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

    public void combineTilesUp() {
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 4; row++) {
                int nextTileValue = 0;
                if (row != 3) {
                    nextTileValue = grid[row + 1][col].getValue();
                }
                if (grid[row][col].hasValue()) {
                    if (grid[row][col].getValue() == nextTileValue) {
                        grid[row][col].setValue(grid[row][col].getValue() * 2);

                        grid[row + 1][col].setValue(0);
                    }
                }
            }
        }
    }

    public void combineTilesDown() {
        for (int col = 0; col < 4; col++) {
            for (int row = 3; row >= 0; row--) {
                int nextTileValue = 0;
                if (row != 0) {
                    nextTileValue = grid[row - 1][col].getValue();
                }
                if (grid[row][col].hasValue()) {
                    if (grid[row][col].getValue() == nextTileValue) {
                        grid[row][col].setValue(grid[row][col].getValue() * 2);

                        grid[row - 1][col].setValue(0);
                    }
                }
            }
        }
    }

    public void combineTilesRight() {
        for (int row = 0; row < 4; row++) {
            for (int col = 3; col >= 0; col--) {
                int nextTileValue = 0;
                if (col != 0) {
                    nextTileValue = grid[row][col - 1].getValue();
                }
                if (grid[row][col].hasValue()) {

                    if (grid[row][col].getValue() == nextTileValue) {
                        grid[row][col].setValue(grid[row][col].getValue() * 2);

                        grid[row][col - 1].setValue(0);
                    }
                }
            }
        }
    }

    public void combineTilesLeft() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int nextTileValue = 0;
                if (col != 3) {
                    nextTileValue = grid[row][col + 1].getValue();
                }
                if (grid[row][col].hasValue()) {

                    if (grid[row][col].getValue() == nextTileValue) {
                        grid[row][col].setValue(grid[row][col].getValue() * 2);

                        grid[row][col + 1].setValue(0);
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

        boolean checkVictory = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (getGrid()[i][j].getValue() == 2048) {
                    checkVictory = true;
                    resetGame();
                    printGrid();
                    break;
                }
            }
        }
        return checkVictory;
    }

    public Tile[][] getGrid() {

        return grid;
    }

    // Method to check if there are any empty tiles left
    public boolean checkIfFullGrid() {
        boolean fullGrid = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (getGrid()[i][j].getValue() == 0) {
                    fullGrid = false;
                    break;
                }
            }
        }

        return fullGrid;
    }

    // Method to generate a random number
    public int generateNumber() {
        int randNumber;
        int[] arrayOfValues = new int[100];

        for (int i = 0; i < 100; i++) {
            if (i < 80) {
                arrayOfValues[i] = 2;
            } else if (i >= 80 && i < 90) {
                arrayOfValues[i] = 4;
            } else if (i >= 90 && i < 95) {
                arrayOfValues[i] = 8;
            } else if (i >= 95 && i < 98) {
                arrayOfValues[i] = 16;
            } else if (i >= 98) {
                arrayOfValues[i] = 32;
            }
        }

        Random rand = new Random();
        int randomInt = rand.nextInt(101);
        randNumber = arrayOfValues[randomInt];

        return randNumber;

    }

    // Method to fill a random tile
    public Tile[][] fillTileWithRandomNumber(Tile[][] grid) {

        if (checkIfFullGrid() == false) {

            Random rand = new Random();

            int randomRow = rand.nextInt(4);
            int randomCol = rand.nextInt(4);

            if (grid[randomRow][randomCol].getValue() == 0) {
                grid[randomRow][randomCol].setValue(generateNumber());
            } else if (grid[randomRow][randomCol].getValue() != 0) {
                fillTileWithRandomNumber(grid);
            }

        }

        return grid;
    }

    public Tile[][] determineTileColor(Tile[][] grid, int row, int col) {

        if (grid[row][col].getValue() == 0) {
            grid[row][col].setColor(234, 226, 183);
        }

        if (grid[row][col].getValue() == 2) {
            grid[row][col].setColor(252, 191, 73);
        }

        if (grid[row][col].getValue() == 4) {
            grid[row][col].setColor(247, 127, 0);
        }

        if (grid[row][col].getValue() == 8) {
            grid[row][col].setColor(214, 40, 40);
        }

        if (grid[row][col].getValue() > 8) {
            grid[row][col].setColor(139, 0, 0);
        }

        return grid;
    }

    public void resetGame() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                getGrid()[i][j].setValue(0);
            }
        }

        fillTileWithRandomNumber(getGrid());
    }

}