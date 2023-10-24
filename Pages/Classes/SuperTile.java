package Classes;
public class SuperTile extends Tile {
    private int timesUsed;
    private OrdinaryTile canBombTile;

    public SuperTile(int value) {
        super(value);
        timesUsed = 0;
        canBombTile = new OrdinaryTile(0); // change here later
    }

    public int getTimesUsed() {
        return timesUsed;
    }
    
    public void decreaseTimesUsed() {
        timesUsed = timesUsed - 1;
    }
    
}
