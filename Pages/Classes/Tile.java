package Classes;
import java.awt.Color;

public class Tile {
    private int value;
    private Color color;

    // When initializing a Tile it should assign a color depending on its value
    public Tile(int value) {
        this.value = value;
        this.color = new Color(255,   0,   0); // change this later
    }

    public int getValue() {
        return this.value;
    } 

    public void setValue(int newValue) {
        this.value = newValue;
    }

    public boolean hasValue() {
        if (this.value != 0) {
            return true;
        } else {
            return false;
        }
    }
}
