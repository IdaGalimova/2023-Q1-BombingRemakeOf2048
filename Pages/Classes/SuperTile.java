package Classes;
public class SuperTile extends Tile {
    private int bombingsLeft;
    private int canBombTileValue;

    public SuperTile(int value) {
        super(value); // not sure about this
        bombingsLeft = 3;
        canBombTileValue = calculateWhatTileSuperCanBomb(); 
    }

    public int getCanBombTile() {
        return canBombTileValue;
    }
    
    public int getBombingsLeft() {
        return bombingsLeft;
    }
    
    public void decreaseTimesUsed() {
        bombingsLeft = bombingsLeft - 1;
    }

    public void setValue(int newValue) {
        super.setValue(newValue);
        canBombTileValue = calculateWhatTileSuperCanBomb(); 
    }

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

    public boolean checkIfCanBomb(int value) {
        if (canBombTileValue == value) {
            return true;
        } else {
            return false;
        }
    }
}
