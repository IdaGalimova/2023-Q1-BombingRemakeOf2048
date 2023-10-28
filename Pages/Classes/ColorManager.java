package Classes;

import java.awt.Color;

public class ColorManager {

    private int mode = 1;
    private Color textColor;

    public ColorManager(int mode) {
        this.mode = mode;
        textColor = determineTextColor();
    }

    public void setMode(int mode) {
        this.mode = mode;
        textColor = determineTextColor();
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color determineTextColor() {
        if (mode == 1) {
            return new Color(0, 48, 73);
        } else if (mode == 2) {
            return new Color(235, 235, 235);
        }
        return null;
    }

    public void determineColor(Tile tile) {
        if (mode == 1) {
            tile.setColor(lightMode(tile.getValue()));
        }

        if (mode == 2) {
            tile.setColor(darkMode(tile.getValue()));
        }
    }

    public Color darkMode(int value) {
        // Dark mode colors

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

    public Color lightMode(int value) {
        // Light mode colors

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
