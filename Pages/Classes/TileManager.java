package Classes;

public class TileManager {
    private Tile[][] grid;
    private Tile lastBombedTile = new OrdinaryTile(0);

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

    public Tile getLastBombedTile() {
        return lastBombedTile;
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public void setValue(int row, int col, int newValue) {
        if (newValue >= 64 && grid[row][col] instanceof OrdinaryTile) {
            grid[row][col] = new SuperTile(newValue);
            return;
        }
        grid[row][col].setValue(newValue);
    }

    public void setValueZero(int row, int col) {
        grid[row][col] = new OrdinaryTile(0);
    }

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
