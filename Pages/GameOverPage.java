import Classes.ColorManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class implements the GameOverPage. This page is displayed when the game
 * is over.
 * 
 * @author Ida Galimova
 * @id 1958895
 * @author Skaiste Liutkute
 * @id 2004119
 */
public class GameOverPage {
    Color darkBlue = new Color(0, 48, 73);
    Color sandy = new Color(234, 226, 183);
    Color yellow = new Color(252, 191, 73);
    Color red = new Color(214, 40, 40);
    Color orange = new Color(247, 127, 0);
    Font moonspaced = new Font("Monospaced", Font.ITALIC | Font.BOLD, 45);
    Font moonspaced2 = new Font("Monospaced", Font.ITALIC | Font.BOLD, 15);

    private ColorManager colorManager;
    private JFrame playFrameReference;


    /** Initializes the colorManager.
     */
    public GameOverPage(ColorManager colorManager, JFrame playFrame) {
        this.colorManager = colorManager;
        this.playFrameReference = playFrame;
        
    }

    /** Main method for this page.
     */
    public void runGameOver() {
        // Setting up game over frame:
        JFrame gameOverFrame = new JFrame();
        gameOverFrame.setTitle("GAME OVER");
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setSize(1000, 600);
        gameOverFrame.setLayout(new BorderLayout()); 
        gameOverFrame.getContentPane().setBackground(sandy);
    
        JPanel panel = new JPanel(new GridBagLayout()); 
        panel.setBackground(sandy);
        
        JLabel gameOverMessage = new JLabel("Game over!");
        gameOverMessage.setFont(moonspaced);
        gameOverMessage.setForeground(darkBlue);
    
        panel.add(gameOverMessage); 
    
        gameOverFrame.add(panel, BorderLayout.CENTER);  
        gameOverFrame.setVisible(true);


        JButton gotoMenuButton = new JButton("Go to Menu");
        gotoMenuButton.setBackground(darkBlue);
        gotoMenuButton.setFont(moonspaced2);
        gotoMenuButton.setFocusable(false);
        gotoMenuButton.setForeground(Color.WHITE);
        gotoMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameOverFrame.dispose(); 
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
                gameOverFrame.dispose(); 
                
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(sandy);
        bottomPanel.add(gotoMenuButton);
        bottomPanel.add(restartButton);
        gameOverFrame.add(bottomPanel, BorderLayout.SOUTH);
    }

}