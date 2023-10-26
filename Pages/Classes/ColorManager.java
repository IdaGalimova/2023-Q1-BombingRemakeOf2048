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

    // Not final, but just a try on how different mode will operate:
    // publi

    // public Color darkMode(int value) {
    // // here can be a switch statement that returns different colors
    // // depending on the value
    // Color reTurnColor = new Color(234, 226, 183);

    // if (value == 2) {
    // reTurnColor = new Color(83, 63, 4);
    // }
    // // 4 dark green

    // if (value == 4) {
    // reTurnColor = new Color(22, 75, 53);
    // }
    // // 8 yellow
    // if (value == 8) {
    // reTurnColor = new Color(179, 134, 0); // finished here
    // }
    // // 16 orange
    // if (value == 16) {
    // reTurnColor = new Color(243, 138, 63);
    // }
    // // 32 light red
    // if (value == 32) {
    // reTurnColor = new Color(248, 113, 104);
    // }
    // // 64 dark red
    // if (value == 64) {
    // reTurnColor = new Color(226, 72, 61);
    // }
    // // 128 light blue
    // if (value == 128) {
    // reTurnColor = new Color(157, 217, 238);
    // }
    // // 256 dark blue
    // if (value == 256) {
    // reTurnColor = new Color(34, 125, 155);
    // }

    // // 512 light purle
    // if (value == 512) {
    // reTurnColor = new Color(184, 172, 246);
    // }

    // // 1024 magenta
    // if (value == 1024) {
    // reTurnColor = new Color(247, 151, 210);
    // }

    // // 2048 deeper magenta
    // if (value == 2048) {
    // reTurnColor = new Color(218, 98, 172);
    // }

    // return reTurnColor;
    // }

    public Color darkMode(int value) {
        // Dark mode colors
        Color returnColor = new Color(30, 30, 30); // Background color

        // 2: Dark green
        if (value == 2) {
            returnColor = new Color(78, 148, 78);
        }
        // 4: Dark teal
        else if (value == 4) {
            returnColor = new Color(0, 128, 128);
        }
        // 8: Dark yellow
        else if (value == 8) {
            returnColor = new Color(204, 153, 0);
        }
        // 16: Dark orange
        else if (value == 16) {
            returnColor = new Color(210, 105, 0);
        }
        // 32: Dark red
        else if (value == 32) {
            returnColor = new Color(159, 0, 0);
        }
        // 64: Dark purple
        else if (value == 64) {
            returnColor = new Color(61, 0, 122);
        }
        // 128: Dark blue
        else if (value == 128) {
            returnColor = new Color(0, 51, 153);
        }
        // 256: Dark violet
        else if (value == 256) {
            returnColor = new Color(102, 0, 102);
        }
        // 512: Dark magenta
        else if (value == 512) {
            returnColor = new Color(153, 0, 51);
        }
        // 1024: Dark pink
        else if (value == 1024) {
            returnColor = new Color(192, 76, 134);
        }
        // 2048: Dark cyan
        else if (value == 2048) {
            returnColor = new Color(8, 115, 33);
        }

        return returnColor;
    }

    public Color lightMode(int value) {
        // here can be a switch statement that returns different colors
        // depending on the value

        // light mode is pastel colors
        Color reTurnColor = new Color(234, 226, 183);

        // 2 light green
        if (value == 2) {
            reTurnColor = new Color(179, 223, 114);
        }
        // 4 dark green

        if (value == 4) {
            reTurnColor = new Color(42, 187, 127);
        }
        // 8 yellow
        if (value == 8) {
            reTurnColor = new Color(245, 205, 71);
        }
        // 16 orange
        if (value == 16) {
            reTurnColor = new Color(243, 138, 63);
        }
        // 32 light red
        if (value == 32) {
            reTurnColor = new Color(248, 113, 104);
        }
        // 64 dark red
        if (value == 64) {
            reTurnColor = new Color(226, 72, 61);
        }
        // 128 light blue
        if (value == 128) {
            reTurnColor = new Color(157, 217, 238);
        }
        // 256 dark blue
        if (value == 256) {
            reTurnColor = new Color(34, 125, 155);
        }

        // 512 light purle
        if (value == 512) {
            reTurnColor = new Color(184, 172, 246);
        }

        // 1024 magenta
        if (value == 1024) {
            reTurnColor = new Color(247, 151, 210);
        }

        // 2048 deeper magenta
        if (value == 2048) {
            reTurnColor = new Color(218, 98, 172);
        }

        return reTurnColor;
    }
}
