package Classes;

import java.util.Random;
import java.awt.Color;

public class GameSetup {
    private ColorManager colorManager;
    private Tile[][] grid;
    private int score;

    public GameSetup() {
        grid = new Tile[4][4];
        score = 0;
        colorManager = new ColorManager(1); // CHANGE MODE HERE LATER

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = new OrdinaryTile(0);
            }
        }

        grid[2][1].setValue(64);
        grid[3][3].setValue(2);

    }

    public int getScore() {
        return score;
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

    public void moveTiles(String direction) {
        if (canMove(direction)) {
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
            fillTileWithRandomNumber();
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

    public void combineTiles() { // probably dont need this one as we are using separate
                                 // methods for combining tiles

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
                        score += 2 * grid[row][col].getValue();
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
                        score += 2 * grid[row][col].getValue();
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
                        score += 2 * grid[row][col].getValue();
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
                        score += 2 * grid[row][col].getValue();
                        grid[row][col].setValue(grid[row][col].getValue() * 2);

                        grid[row][col + 1].setValue(0);
                    }
                }
            }
        }
    }

    public void bombATile(int rowSuper, int colSuper, int rowToBomb, int colToBomb) { 
        // (Tile superTile, Tile tileToBomb) // maybe this is better
        if (grid[rowSuper][colSuper] instanceof SuperTile) {
            SuperTile superTile = (SuperTile) grid[rowSuper][colSuper];
            
            grid[rowToBomb][colToBomb].setValue(0);
            superTile.decreaseTimesUsed();

            if (superTile.getTimesUsed() == 0) {
                grid[rowSuper][colSuper] = new OrdinaryTile(0);
            }
        }

    }

    public boolean canMove(String direction) {
        if (direction == "left") {
            return canMoveLeft();
        } else if (direction == "right") {
            return canMoveRight();
        } else if (direction == "up") {
            return canMoveUp();
        } else if (direction == "down") {
            return canMoveDown();
        }

        return false;
    }

    public boolean canMoveLeft() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                if (grid[row][col].getValue() == 0 && grid[row][col + 1].hasValue()) {
                    return true;
                }

                if (grid[row][col].hasValue()
                        && grid[row][col].getValue() == grid[row][col + 1].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canMoveRight() {
        for (int row = 0; row < 4; row++) {
            for (int col = 3; col > 0; col--) {
                if (grid[row][col].getValue() == 0 && grid[row][col - 1].hasValue()) {
                    return true;
                }

                if (grid[row][col].hasValue()
                        && grid[row][col].getValue() == grid[row][col - 1].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canMoveUp() {
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 3; row++) {
                if (grid[row][col].getValue() == 0 && grid[row + 1][col].hasValue()) {
                    return true;
                }

                if (grid[row][col].hasValue()
                        && grid[row][col].getValue() == grid[row + 1][col].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canMoveDown() {
        for (int col = 0; col < 4; col++) {
            for (int row = 3; row > 0; row--) {
                if (grid[row][col].getValue() == 0 && grid[row - 1][col].hasValue()) {
                    return true;
                }

                if (grid[row][col].hasValue()
                        && grid[row][col].getValue() == grid[row - 1][col].getValue()) {
                    return true;
                }
            }
        }
        return false;
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

    public Tile getTileInGrid(int row, int col) {
        Tile tile = getGrid() [row] [col];
        return tile; 
    }

    // Method to check if there are any empty tiles left
    public boolean checkIfFullGrid() {
        boolean isFull = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (getGrid()[i][j].getValue() == 0) {
                    isFull = false;
                    break;
                }
            }
        }

        return isFull;
    }

    // Method to check if there are any available moves left
    public boolean hasAvailableMoves() {
        if (!canMoveDown() && !canMoveUp() && !canMoveLeft() && !canMoveRight()) {
            return false;
        }
        return true;
    }

    public boolean checkGameOver() {
        if (checkIfFullGrid() && !hasAvailableMoves()) {
            resetGame();
            return true;
        } else {
            return false;
        }
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
    public Tile[][] fillTileWithRandomNumber() {
        if (!checkIfFullGrid()) {
            Random rand = new Random();

            int randomRow = rand.nextInt(4);
            int randomCol = rand.nextInt(4);

            if (grid[randomRow][randomCol].getValue() == 0) {
                int randomValue = generateNumber();
                grid[randomRow][randomCol].setValue(randomValue);
                // score += randomValue;
            } else if (grid[randomRow][randomCol].getValue() != 0) {
                fillTileWithRandomNumber();
            }

        }

        return grid;
    }

    public void resetGame() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                getGrid()[i][j].setValue(0);
            }
        }
        score = 0;

        fillTileWithRandomNumber();
        fillTileWithRandomNumber();
    }

}