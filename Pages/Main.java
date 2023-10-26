import Classes.ColorManager;
import Classes.GameSetup;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//* The main program.*/

public class Main {
    

    public void main() {
        ColorManager colorManager = new ColorManager(1);
        StartPage startPage = new StartPage(colorManager);
    }
    public static void main(String[] args) {
        
        Main main = new Main();
        main.main();
    }
}