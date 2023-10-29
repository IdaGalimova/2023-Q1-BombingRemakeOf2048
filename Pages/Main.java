import Classes.ColorManager;

/**
 * This is the main program. If you execute this file, the game will be displayed.
 * 
 * @author Ida Galimova
 * @id 1958895
 * @author Skaiste Liutkute
 * @id 2004119
 */
public class Main {
    /** Sets a color manager with the light mode as default. StartPage will be
     * displayed.
     */
    public static void main(String[] args) {
        ColorManager colorManager = new ColorManager(1);
        new StartPage(colorManager);
    }
}