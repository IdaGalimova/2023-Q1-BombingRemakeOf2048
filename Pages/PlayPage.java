import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

public class PlayPage extends JFrame{
    
    public PlayPage (JFrame previousFrame) {
        
        Color darkBlue = new Color(38, 70, 83);
        Color mustardYellow = new Color(233, 198, 74);
        Color aqua = new Color(42, 157, 143);


        setTitle("PLAYING GAME");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(400, 400);
        setLayout(null);
        getContentPane().setBackground(darkBlue);

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(0, 0, 100, 20);
        backButton.setBackground(aqua);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide this frame and show the previous frame again
                setVisible(false);
                previousFrame.setVisible(true);
            }
        });

        JLabel textABoutGame = new JLabel("insert game here");
        Font customFont = new Font("Monospaced", Font.ITALIC, 20);
        textABoutGame.setForeground(Color.CYAN);
        textABoutGame.setFont(customFont);
        textABoutGame.setBounds(100, 150, 200, 40);

        //ImageIcon imageIcon = new ImageIcon("C:\\Users\\user\\Desktop\\programing\\2048\\2023-Q1-BombingRemakeOf2048\\jogaila.jpg"); // Replace with your image path
        //JLabel imageLabel = new JLabel(imageIcon);
        //add(imageLabel);
        //imageLabel.setBounds(0, 0, 1080, 710);
        add(textABoutGame);
        add(backButton);


        


    }
}
