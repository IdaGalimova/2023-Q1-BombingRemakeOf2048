package Classes;

public class TileManager {
    private Tile[][] grid;

    public TileManager() {
        grid = new Tile[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = new OrdinaryTile(0);
            }
        }

        // grid[2][1].setValue(64);
        // grid[3][3].setValue(2);
        setValue(2, 3, 64);
        setValue(3, 3, 64);
        setValue(1, 3, 128);
        setValue(1, 3, 128);

    }

    public Tile[][] getGrid() {
        return grid;
    }

    public void setGrid(Tile[][] grid) {
        this.grid = grid;
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
}
