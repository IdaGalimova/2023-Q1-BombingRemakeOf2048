import java.awt.Color;
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
        textABoutGame.setForeground(Color.CYAN);
        textABoutGame.setBounds(150, 150, 200, 40);

        add(textABoutGame);
        add(backButton);


        


    }
}
