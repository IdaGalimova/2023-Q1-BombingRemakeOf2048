package Classes;
public class SuperTile extends Tile {
    private int timesUsed;
    private int canBombTileValue;

    public SuperTile(int value) {
        super(value); // not sure about this
        timesUsed = 0;
        canBombTileValue = calculateWhatTileSuperCanBomb(); 
    }

    public int getTimesUsed() {
        return timesUsed;
    }
    
    public void decreaseTimesUsed() {
        timesUsed = timesUsed - 1;
    }

    public void setValue(int newValue) {
        super.setValue(newValue);
        canBombTileValue = calculateWhatTileSuperCanBomb(); 
    }

    // 1024: 2
    //512: 4
    //256: 8
    //128: 16
    //64: 32
    public int calculateWhatTileSuperCanBomb() { // !!! find a better name 
        switch (super.getValue()) {
            case 64: return 32;
            case 128: return 16;
            case 256: return 8;
            case 512: return 4;
            case 1024: return 2;
            default: return 0;
        }

    }
}
