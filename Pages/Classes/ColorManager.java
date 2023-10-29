package Classes;

import java.awt.Color;

/**
 * This class provides methods to work with light and dark modes.
 * 
 * @author Ida Galimova
 * @id 1958895
 * @author Skaiste Liutkute
 * @id 2004119
 */
public class ColorManager {
    private int mode = 1;
    private Color textColor;
    private Color backgroundColor;

    /**
     * Setting the mode and determining the color for the text.
     */
    public ColorManager(int mode) {
        this.mode = mode;
        textColor = determineTextColor();
        backgroundColor = determineBackgroundColor();
    }

    /**Setting the mode.
     */
    public void setMode(int mode) {
        this.mode = mode;
        textColor = determineTextColor();
        backgroundColor = determineBackgroundColor();
    }

    /** Getting the mode.
     */
    public Color getTextColor() {
        return textColor;
    }

    /** Gets background color.
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /** Determining the text color depending on the mode.
     */
    public Color determineTextColor() {
        if (mode == 1) {
            return new Color(0, 48, 73);
        } else if (mode == 2) {
            return new Color(235, 235, 235);
        }
        return null;
    }

    /** Determines background color depending on the mode.
     */
    public Color determineBackgroundColor() {
        if (mode == 2) {
            return new Color(0, 48, 73);
        } else if (mode == 1) {
            return new Color(160, 176, 234);
        } else {
            return new Color(255, 255, 255);
        }        
    }

    /** Determining the color of the tile.
     */
    public void determineColor(Tile tile) {
        if (mode == 1) {
            tile.setColor(lightMode(tile.getValue()));
        }

        if (mode == 2) {
            tile.setColor(darkMode(tile.getValue()));
        }
    }

    /** Setting the colors for the dark mode depending on their value.
     */
    public Color darkMode(int value) {
        switch (value) {
            case 2: return new Color(78, 148, 78);
            case 4: return new Color(0, 128, 128);
            case 8: return new Color(204, 153, 0);
            case 16: return new Color(210, 105, 0);
            case 32: return new Color(159, 0, 0);
            case 64: return new Color(61, 0, 122);
            case 128: return new Color(0, 51, 153);
            case 256: return new Color(102, 0, 102);
            case 512: return new Color(153, 0, 51);
            case 1024: return new Color(192, 76, 134);
            case 2048: return new Color(8, 115, 33);
            default: return new Color(30, 30, 30); // background color
        }
    }

    /** Setting the colors for the light mode depending on their value.
     */
    public Color lightMode(int value) {
        switch (value) {
            case 2: return new Color(179, 223, 114);
            case 4: return new Color(42, 187, 127);
            case 8: return new Color(245, 205, 71);
            case 16: return new Color(243, 138, 63);
            case 32: return new Color(248, 113, 104);
            case 64: return new Color(226, 72, 61);
            case 128: return new Color(157, 217, 238);
            case 256: return new Color(34, 125, 155);
            case 512: return new Color(184, 172, 246);
            case 1024: return new Color(247, 151, 210);
            case 2048: return new Color(218, 98, 172);
            default: return new Color(234, 226, 183); // background color 
        }
    }
}
