import java.awt.Color;

abstract class Tile {
    private int value;
    private Color color;

    // When initializing a Tile it should assign a color depending on its value
    public Tile(int value) {
        this.value = value;
        this.color = new Color(255,   0,   0); // change this later
    }
}
