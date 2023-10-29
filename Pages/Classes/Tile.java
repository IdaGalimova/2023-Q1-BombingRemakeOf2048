package Classes;

import java.awt.Color;

/**
 * This class implements Tile. Each Tile has a value and a color.
 * 
 * @author Ida Galimova
 * @id 1958895
 * @author Skaiste Liutkute
 * @id 2004119
 */
public class Tile {
    private int value;
    private Color color;
    
    /** Sets the value on the creation of Tile.
     */
    public Tile(int value) {
        this.value = value;
    }

    /** Gets the value. 
     */
    public int getValue() {
        return this.value;
    } 

    /** Gets the color.
     */
    public Color getColor() {
        return this.color;
    }

    /** Sets a value. 
     */
    public void setValue(int newValue) {
        this.value = newValue;
    }

    /** Sets a color. 
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /** Checks if this Tile has a value. 
     */
    public boolean hasValue() {
        return this.value != 0;
    }
}
