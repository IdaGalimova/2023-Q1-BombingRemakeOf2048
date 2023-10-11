public class GameSetup {
    private Tile[][] grid;
    private int score;

    public GameSetup() {
        grid = new Tile[4][4];
        score = 0;

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
}