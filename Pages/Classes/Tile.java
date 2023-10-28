package Classes;
import java.awt.Color;

public class Tile {
    private int value;
    private Color color;
    
    public Tile(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    } 

    public Color getColor() {
        return this.color;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean hasValue() {
        return this.value != 0;
    }
}
