package Classes;

import java.util.Random;

/**
 * This class implements the functionality of the game.
 * It contains methods such as combining, moving, spawning, bombing, 
 * game resetting, and checking for avalible moves, victory and game over.
 * 
 * @author Ida Galimova
 * @id 1958895
 * @author Skaiste Liutkute
 * @id 2004119
 */
public class GameSetup {
    private TileManager tileManager;
    private int score;

    /** Initializing tileManager and setting score.
     */
    public GameSetup() {
        tileManager = new TileManager();
        spawnNewTile();
        spawnNewTile();

        score = 0;
    }

    /** Getting the grid.
     */
    public Tile[][] getGrid() {
        return tileManager.getGrid();
    }

    /** Getting the score.
     */
    public int getScore() {
        return score;
    }

    /** Printing the grid. Implemented only for back-end testing.
     */
    public void printGrid() {
        Tile[][] grid = tileManager.getGrid();
        
        String splitterLine = "-----------------";
        String endHorizontal = "|";
        System.out.println(splitterLine);

        for (int i = 0; i < 4; i++) {
            String rowLine = endHorizontal + " ";
            for (int j = 0; j < 4; j++) {
                rowLine += grid[i][j].getValue();
                // For testing if bombing is working:
                if (grid[i][j] instanceof SuperTile) {
                    SuperTile superTile = (SuperTile) grid[i][j];
                    rowLine += "(!";
                    rowLine += superTile.getBombingsLeft();
                    rowLine += ")";
                }
                rowLine += " ";
            }
            rowLine += endHorizontal;
            System.out.println(rowLine);
        }
        System.out.println(splitterLine);
    }

    /** 
     * Checking if it's possible to move tiles to the provided direction,
     * and then moving and combining them. After the tiles are moved, a new tile is spawn.
     */
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
            spawnNewTile();
        }
    }

    /** Moves all of the tiles up. Helper method for the moveTiles() method
     */
    public void moveTilesUp() {
        Tile[][] grid = tileManager.getGrid();

        for (int col = 0; col < 4; col++) {
            int count = 0;

            for (int row = 0; row < 4; row++) {
                if (grid[row][col].hasValue()) {
                    tileManager.setValueOnMove(row, col, count, col, grid[row][col].getValue());
                    
                    // Setting original tile as empty after the tile is moved
                    if (count != row) {
                        tileManager.setValueZero(row, col);
                    }
                    count++;
                }
            }
        }

    }

    /** Moves all of the tiles down. Helper method for the moveTiles() method
     */
    public void moveTilesDown() {
        Tile[][] grid = tileManager.getGrid();

        for (int col = 0; col < 4; col++) {
            int count = 3;

            for (int row = 3; row >= 0; row--) {
                if (grid[row][col].hasValue()) {
                    tileManager.setValueOnMove(row, col, count, col, grid[row][col].getValue());

                    // Setting original tile as empty after the tile is moved
                    if (count != row) {
                        tileManager.setValueZero(row, col);
                    }
                    count--;
                }
            }
        }

    }

    /** Moves all of the tiles right. Helper method for the moveTiles() method
     */
    public void moveTilesRight() {
        Tile[][] grid = tileManager.getGrid();

        for (int row = 0; row < 4; row++) {
            int count = 3;
            for (int col = 3; col >= 0; col--) {
                if (grid[row][col].hasValue()) {
                    tileManager.setValueOnMove(row, col, row, count, grid[row][col].getValue());

                    // Setting original tile as empty after the tile is moved
                    if (count != col) {
                        tileManager.setValueZero(row, col);
                    }
                    count--;
                }
            }
        }
    }

    /** Moves all of the tiles left. Helper method for the moveTiles() method
     */
    public void moveTilesLeft() {
        Tile[][] grid = tileManager.getGrid();

        for (int row = 0; row < 4; row++) {
            int count = 0;
            for (int col = 0; col < 4; col++) {
                if (grid[row][col].hasValue()) {
                    tileManager.setValueOnMove(row, col, row, count, grid[row][col].getValue());

                    // Setting original tile as empty after the tile is moved
                    if (count != col) {
                        tileManager.setValueZero(row, col);
                    }
                    count++;
                }
            }
        }
    }

    /** Combines the tiles with the same value when moving them upwards. 
     *  Helper method for the moveTiles() method
     */
    public void combineTilesUp() {
        Tile[][] grid = tileManager.getGrid();
        
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 4; row++) {
                int nextTileValue = 0;
                if (row != 3) {
                    nextTileValue = grid[row + 1][col].getValue();
                }
                if (grid[row][col].hasValue()) {
                    if (grid[row][col].getValue() == nextTileValue) {
                        score += 2 * grid[row][col].getValue();
                        tileManager.setValue(row, col, grid[row][col].getValue() * 2);

                        tileManager.setValueZero(row + 1, col);
                    }
                }
            }
        }
    }

    /** Combines the tiles with the same value when moving them downwards. 
     *  Helper method for the moveTiles() method
     */
    public void combineTilesDown() {
        Tile[][] grid = tileManager.getGrid();
        
        for (int col = 0; col < 4; col++) {
            for (int row = 3; row >= 0; row--) {
                int nextTileValue = 0;
                if (row != 0) {
                    nextTileValue = grid[row - 1][col].getValue();
                }
                if (grid[row][col].hasValue()) {
                    if (grid[row][col].getValue() == nextTileValue) {
                        score += 2 * grid[row][col].getValue();
                        tileManager.setValue(row, col, grid[row][col].getValue() * 2);

                        tileManager.setValueZero(row - 1, col);
                    }
                }
            }
        }
    }

    /** Combines the tiles with the same value when moving them to the right. 
     *  Helper method for the moveTiles() method
     */
    public void combineTilesRight() {
        Tile[][] grid = tileManager.getGrid();
        
        for (int row = 0; row < 4; row++) {
            for (int col = 3; col >= 0; col--) {
                int nextTileValue = 0;
                if (col != 0) {
                    nextTileValue = grid[row][col - 1].getValue();
                }
                if (grid[row][col].hasValue()) {

                    if (grid[row][col].getValue() == nextTileValue) {
                        score += 2 * grid[row][col].getValue();
                        tileManager.setValue(row, col, grid[row][col].getValue() * 2);

                        tileManager.setValueZero(row, col - 1);
                    }
                }
            }
        }
    }

    /** Combines the tiles with the same value when moving them to the left. 
     *  Helper method for the moveTiles() method
     */
    public void combineTilesLeft() {
        Tile[][] grid = tileManager.getGrid();
        
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int nextTileValue = 0;
                if (col != 3) {
                    nextTileValue = grid[row][col + 1].getValue();
                }
                if (grid[row][col].hasValue()) {

                    if (grid[row][col].getValue() == nextTileValue) {
                        score += 2 * grid[row][col].getValue();
                        tileManager.setValue(row, col, grid[row][col].getValue() * 2);

                        tileManager.setValueZero(row, col + 1);
                    }
                }
            }
        }
    }

    /** Destroys a tile by providing the position of the SuperTile and tile to bomb.
     */
    public void bombATile(int rowSuper, int colSuper, int rowToBomb, int colToBomb) { 
        tileManager.bombTile(rowSuper, colSuper, rowToBomb, colToBomb);

    }

    /** Checks if it's possible to move tiles in the certain direction.
     */
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

    /** Checks if it's possible to move tiles to the left.
     *  Helper method for the canMove() method
     */
    public boolean canMoveLeft() {
        Tile[][] grid = tileManager.getGrid();
        
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

    /** Checks if it's possible to move tiles to the right.
     *  Helper method for the canMove() method
     */
    public boolean canMoveRight() {
        Tile[][] grid = tileManager.getGrid();
        
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

    /** Checks if it's possible to move tiles upwards.
     *  Helper method for the canMove() method
     */
    public boolean canMoveUp() {
        Tile[][] grid = tileManager.getGrid();
        
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

    /** Checks if it's possible to move tiles downwards.
     *  Helper method for the canMove() method
     */
    public boolean canMoveDown() {
        Tile[][] grid = tileManager.getGrid();
        
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

    /** Checks if there is a tile with "2048" value.
     */
    public boolean checkVictory() {
        Tile[][] grid = tileManager.getGrid();

        boolean checkVictory = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j].getValue() == 2048) {
                    checkVictory = true;
                    resetGame();
                    printGrid();
                    break;
                }
            }
        }
        return checkVictory;
    }

    /** Checks if a grid is full.
     */
    public boolean checkIfFullGrid() {
        Tile[][] grid = tileManager.getGrid();
        
        boolean isFull = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j].getValue() == 0) {
                    isFull = false;
                    break;
                }
            }
        }

        return isFull;
    }

    /** Checks if there are any available moves left.
     */
    public boolean hasAvailableMoves() {
        if (!canMoveDown() && !canMoveUp() && !canMoveLeft() && !canMoveRight()) {
            return false;
        }
        return true;
    }

    /** Checks if the game is over.
     */
    public boolean checkGameOver() {
        if (checkIfFullGrid() && !hasAvailableMoves()) {
            resetGame();
            return true;
        } else {
            return false;
        }
    }

    /** Generates a random value for the tile to spawn. The value of the tile is calculated by 
     * selecting a random value, with the highest likelihood of getting 2 and 
     * the lowest probability of choosing 32.
     * Helper method for the spawnNewTile() method
     */
    public int generateRandomValue() {
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
        int randomInt = rand.nextInt(100);
        randNumber = arrayOfValues[randomInt];

        return randNumber;
    }

    /** Spawns a new tile at a random place.
     */
    public Tile[][] spawnNewTile() {
        Tile[][] grid = tileManager.getGrid();
        
        if (!checkIfFullGrid()) {
            Random rand = new Random();

            int randomRow = rand.nextInt(4);
            int randomCol = rand.nextInt(4);

            if (grid[randomRow][randomCol].getValue() == 0) {
                int randomValue = generateRandomValue();
                tileManager.setValue(randomRow, randomCol, randomValue);
            } else if (grid[randomRow][randomCol].getValue() != 0) {
                spawnNewTile();
            }

        }

        return grid;
    }

    /** Resets the game.
     */
    public void resetGame() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tileManager.setValueZero(i, j);
            }
        }
        score = 0;

        spawnNewTile();
        spawnNewTile();
    }

}