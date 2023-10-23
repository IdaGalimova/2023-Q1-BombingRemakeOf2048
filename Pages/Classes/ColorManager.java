package Classes;

import java.awt.Color;

public class ColorManager {

    private int mode = 2;

    public ColorManager(int mode) {
        this.mode = mode;
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
    public Color darkMode(int value) {
        //here can be a switch statement that returns different colors
        //depending on the value
        Color reTurnColor = new Color(234, 226, 183);

        if (value == 2) {
            reTurnColor = new Color(0,0,0);
        }
        //4 dark green

        if (value == 4) {
            reTurnColor = new Color(42,187,127);
        }
        //8 yellow
        if (value == 8) {
            reTurnColor = new Color(245, 205, 71);
        }
        //16 orange
        if (value == 16) {
            reTurnColor = new Color(243, 138, 63);
        }
        //32 light red
        if (value == 32) {
            reTurnColor = new Color(248, 113, 104);
        }
        //64 dark red
        if (value == 64) {
            reTurnColor = new Color(226, 72, 61);
        }
        //128 light blue
        if (value == 128) {
            reTurnColor = new Color(157, 217, 238);
        }
        //256 dark blue
        if (value == 256) {
            reTurnColor = new Color(34, 125, 155);
        }

        //512 light purle
        if (value == 512) {
            reTurnColor = new Color(184, 172, 246);
        }

        //1024 magenta
        if (value == 1024) {
            reTurnColor = new Color(247, 151, 210);
        }

        //2048 deeper magenta
        if (value == 2048) {
            reTurnColor = new Color(218, 98, 172);
        }


        return reTurnColor;
    }

    public Color lightMode(int value) {
        //here can be a switch statement that returns different colors
        //depending on the value

        // light mode is pastel colors
        Color reTurnColor = new Color(234, 226, 183);

        //2 light green
        if (value == 2) {
            reTurnColor = new Color(179, 223, 114);
        }
        //4 dark green

        if (value == 4) {
            reTurnColor = new Color(42,187,127);
        }
        //8 yellow
        if (value == 8) {
            reTurnColor = new Color(245, 205, 71);
        }
        //16 orange
        if (value == 16) {
            reTurnColor = new Color(243, 138, 63);
        }
        //32 light red
        if (value == 32) {
            reTurnColor = new Color(248, 113, 104);
        }
        //64 dark red
        if (value == 64) {
            reTurnColor = new Color(226, 72, 61);
        }
        //128 light blue
        if (value == 128) {
            reTurnColor = new Color(157, 217, 238);
        }
        //256 dark blue
        if (value == 256) {
            reTurnColor = new Color(34, 125, 155);
        }

        //512 light purle
        if (value == 512) {
            reTurnColor = new Color(184, 172, 246);
        }

        //1024 magenta
        if (value == 1024) {
            reTurnColor = new Color(247, 151, 210);
        }

        //2048 deeper magenta
        if (value == 2048) {
            reTurnColor = new Color(218, 98, 172);
        }


        return reTurnColor;
    }
}
