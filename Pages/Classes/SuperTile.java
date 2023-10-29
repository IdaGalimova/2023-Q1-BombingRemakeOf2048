package Classes;

/**
 * This class implements a child object of class Tile. SuperTile is a tile
 * with value bigger or equal to 64. Moreover, SuperTiles have a special 
 * ability to bomb/delete certain OrdinaryTiles. 
 * 
 * @author Ida Galimova
 * @id 1958895
 * @author Skaiste Liutkute
 * @id 2004119
 */
public class SuperTile extends Tile {
    private int bombingsLeft;
    private int canBombTileValue;

    /** Sets the value, remaining amount of bombings and what OrdinaryTile
     * this SuperTile can bomb.
     */
    public SuperTile(int value) {
        super(value); 
        bombingsLeft = 3;
        canBombTileValue = calculateWhatTileSuperCanBomb(); 
    }

    /** Gets what OrdinaryTile's value this SuperTIle can bomb.
     */
    public int getCanBombTile() {
        return canBombTileValue;
    }
    
    /** Gets remaining amount of bombings. 
     */
    public int getBombingsLeft() {
        return bombingsLeft;
    }
    
    /** Sets a new value and recalculates what tile this object can bomb. 
     */
    public void setValue(int newValue) {
        super.setValue(newValue);
        canBombTileValue = calculateWhatTileSuperCanBomb(); 
    }

    /** Decreases amount of bombings.
     */
    public void decreaseTimesUsed() {
        bombingsLeft = bombingsLeft - 1;
    }

    /** Calculates what OrdinaryTile's value this object can bomb.
     */
    public int calculateWhatTileSuperCanBomb() {
        switch (super.getValue()) {
            case 64: return 32;
            case 128: return 16;
            case 256: return 8;
            case 512: return 4;
            case 1024: return 2;
            default: return 0;
        }
    }

    /** Checks if this object can bomb an OrdinaryTile with this value.
     */
    public boolean checkIfCanBomb(int value) {
        return canBombTileValue == value;
    }
}
