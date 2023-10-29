import Classes.GameSetup;
import Classes.Tile;
import Classes.ColorManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VictoryPage {

    private ColorManager colorManager;
    public VictoryPage(ColorManager colorManager, JFrame playFrame) {
        colorManager = colorManager;
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


        JButton GotoMenuButton = new JButton("Go to Menu");
        GotoMenuButton.setBackground(darkBlue);
        GotoMenuButton.setFont(moonspaced2);
        GotoMenuButton.setFocusable(false);
        GotoMenuButton.setForeground(Color.WHITE);
        GotoMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                victoryFrame.dispose(); 
                Main main = new Main();
                main.main();
                playFrameReference.dispose();
                
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
        bottomPanel.add(GotoMenuButton);
        bottomPanel.add(restartButton);
        victoryFrame.add(bottomPanel, BorderLayout.SOUTH);
    }

}