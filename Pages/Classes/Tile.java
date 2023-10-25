package Classes;
import java.awt.Color;

public class Tile {
    private int value;
    private Color color;

    // When initializing a Tile it should assign a color depending on its value
    public Tile(int value) {
        this.value = value;
        this.color = new Color(234, 226, 183);
    }

    public int getValue() {
        return this.value;
    } 

    public void setValue(int newValue) {
        this.value = newValue;
    }

    // protected void convertToSuperTile() { // not sure about this yet
    //     SuperTile convertedTile = new SuperTile(this.getValue());
    // }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean hasValue() {
        if (this.value != 0) {
            return true;
        } else {
            return false;
        }
    }
}
