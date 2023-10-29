package Classes;

/**
 * This calss implements a manager for the Tile grid. It provides methods 
 * to set, get and change the grid data.
 * 
 * @author Ida Galimova
 * @id 1958895
 * @author Skaiste Liutkute
 * @id 2004119
 */
public class TileManager {
    private Tile[][] grid;
    private Tile lastBombedTile = new OrdinaryTile(0);

    /** Initializes the grid and sets the starting Tiles.
     */
    public TileManager() {
        grid = new Tile[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = new OrdinaryTile(0);
            }
        }

        setValue(2, 0, 32);
        setValue(2, 1, 32);

        setValue(3, 3, 32);
        setValue(1, 3, 32);
        setValue(0, 3, 32);

    }

    /** Gets the grid.
     */
    public Tile[][] getGrid() {
        return grid;
    }

    /** Gets the last bombed Tile.
     */
    public Tile getLastBombedTile() {
        return lastBombedTile;
    }

    /** Makes a Tile empty at the certain position.
     */
    public void setValueZero(int row, int col) {
        grid[row][col] = new OrdinaryTile(0);
    }

    /** Sets a new value for the Tile at the certain position.
     * If the value equal to or greater than 64, the OrdinaryTile becomes a 
     * SuperTile.
     */
    public void setValue(int row, int col, int newValue) {
        if (newValue >= 64 && grid[row][col] instanceof OrdinaryTile && newValue != 2048) {
            grid[row][col] = new SuperTile(newValue); 
            return;
        }
        grid[row][col].setValue(newValue);
    }

    /** Sets a value of the Tile from the position of the Tile before it was 
     * moved to the new postion.
     */
    public void setValueOnMove(int rowOld, int colOld, int rowNew, int colNew, int newValue) {
        grid[rowNew][colNew] = grid[rowOld][colOld];
    } 

    /** SuperTile bombs an OrdinaryTile given their position. If the SuperTile has
     * no remaining bombings left, it disappears as well.
     */
    public void bombTile(int rowSuper, int colSuper, int rowToBomb, int colToBomb) {
        if (grid[rowSuper][colSuper] instanceof SuperTile) {
            SuperTile superTile = (SuperTile) grid[rowSuper][colSuper];
            
            if (superTile.checkIfCanBomb(grid[rowToBomb][colToBomb].getValue())) {
                superTile.decreaseTimesUsed();
                lastBombedTile = grid[rowToBomb][colToBomb];
                setValueZero(rowToBomb, colToBomb);
    
                if (superTile.getBombingsLeft() == 0) {
                    setValueZero(rowSuper, colSuper);
                }
            }
        }
    }
}
