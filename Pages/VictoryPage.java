import Classes.ColorManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class implements the VictoryPage. THis page is displayed when the game
 * is won.
 * 
 * @author Ida Galimova
 * @id 1958895
 * @author Skaiste Liutkute
 * @id 2004119
 */
public class VictoryPage {
    private ColorManager colorManager;

    /** Initializes the colorManager.
     */
    public VictoryPage(ColorManager colorManager, JFrame playFrame) {
        this.colorManager = colorManager;
        this.playFrameReference = playFrame;
    }

    Color darkBlue = new Color(0, 48, 73);
    Color sandy = new Color(234, 226, 183);
    Color yellow = new Color(252, 191, 73);
    Color red = new Color(214, 40, 40);
    Color orange = new Color(247, 127, 0);
    Font moonspaced = new Font("Monospaced", Font.ITALIC | Font.BOLD, 45);
    Font moonspaced2 = new Font("Monospaced", Font.ITALIC | Font.BOLD, 15);
    private JFrame playFrameReference;

    /** Main method for this page.
     */
    public void victoryPage() {
        // Setting up victory frame:
        JFrame victoryFrame = new JFrame();
        victoryFrame.setTitle("VICTORY");
        victoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        victoryFrame.setSize(1000, 600);
        victoryFrame.setLayout(new BorderLayout()); 
        victoryFrame.getContentPane().setBackground(sandy);
    
        JPanel panel = new JPanel(new GridBagLayout()); 
        panel.setBackground(sandy);
        
        JLabel congradsMessage = new JLabel("Victory!!");
        congradsMessage.setFont(moonspaced);
        congradsMessage.setForeground(darkBlue);
    
        panel.add(congradsMessage); 
    
        victoryFrame.add(panel, BorderLayout.CENTER);  
        victoryFrame.setVisible(true);


        JButton gotoMenuButton = new JButton("Go to Menu");
        gotoMenuButton.setBackground(darkBlue);
        gotoMenuButton.setFont(moonspaced2);
        gotoMenuButton.setFocusable(false);
        gotoMenuButton.setForeground(Color.WHITE);
        gotoMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                victoryFrame.dispose(); 
                playFrameReference.dispose();
                new StartPage(colorManager);
            }
        });

        JButton restartButton = new JButton("Restart");
        restartButton.setBackground(darkBlue);
        restartButton.setFont(moonspaced2);
        restartButton.setForeground(Color.WHITE);
        restartButton.setFocusable(false);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                victoryFrame.dispose(); 
                
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(sandy);
        bottomPanel.add(gotoMenuButton);
        bottomPanel.add(restartButton);
        victoryFrame.add(bottomPanel, BorderLayout.SOUTH);
    }

}