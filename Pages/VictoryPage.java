import Classes.GameSetup;
import Classes.Tile;
import Classes.ColorManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VictoryPage {

    private ColorManager colorManager;
    public VictoryPage(ColorManager colorManager) {
        colorManager = colorManager;
    }

    Color darkBlue = new Color(0, 48, 73);
    Color sandy = new Color(234, 226, 183);
    Color yellow = new Color(252, 191, 73);
    Color red = new Color(214, 40, 40);
    Color orange = new Color(247, 127, 0);
    Font moonspaced = new Font("Monospaced", Font.ITALIC | Font.BOLD, 45);

    public void victoryPage() {
        // Setting up victory frame:
        JFrame victoryFrame = new JFrame();
        victoryFrame.setTitle("VICTORY");
        victoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        victoryFrame.setSize(600, 600);
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


        JButton GotoMenuButton = new JButton("Go to Menu");
        GotoMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                victoryFrame.dispose(); 
                StartPage startPage = new StartPage(colorManager);
                
            }
        });

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                victoryFrame.dispose(); 
                
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(sandy);
        bottomPanel.add(GotoMenuButton);
        bottomPanel.add(restartButton);
        victoryFrame.add(bottomPanel, BorderLayout.SOUTH);
    }

}