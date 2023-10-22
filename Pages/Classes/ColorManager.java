package Classes;

import java.awt.Color;

public class ColorManager {

    private int mode;

    public ColorManager(int mode) {
        this.mode = mode;
    }

    public void determineColor(Tile tile) {
        // set the color value of the tile to the color that corresponds to the mode
    }

    // Not final, but just a try on how different mode will operate:
    public Color DarkMode(int value) {
        //here can be a switch statement that returns different colors
        //depending on the value

        return new Color(0, 0, 0);
    }

    public Color LightMode(int value) {
        //here can be a switch statement that returns different colors
        //depending on the value

        return new Color(0, 0, 0);
    }
}
