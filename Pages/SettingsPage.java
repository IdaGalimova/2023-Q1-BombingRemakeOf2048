import Classes.ColorManager;
import Classes.GameSetup;
import Classes.GameSetup;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SettingsPage {
    
    GameSetup gameSetup = new GameSetup();

    JLabel messageLabel;
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            messageLabel.setText("");
        }
    });
    

    Color darkBlue = new Color(0, 48, 73);
    Color sandy = new Color(234, 226, 183);
    Color yellow = new Color(252, 191, 73);
    Color red = new Color(214, 40, 40);
    Color orange = new Color(247, 127, 0);
    Font moonspaced = new Font("Monospaced", Font.ITALIC | Font.BOLD, 15);

    
    public void settingsPage(){
        JFrame settingsFrame = new JFrame();
        settingsFrame.setTitle("PLAYING GAME");
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsFrame.setSize(600, 600);
        settingsFrame.setLayout(null);
        settingsFrame.getContentPane().setBackground(darkBlue);
        settingsFrame.setVisible(true);


        messageLabel = new JLabel();
        messageLabel.setBounds(150, 200, 300, 30);
        messageLabel.setForeground(sandy); 
        settingsFrame.add(messageLabel);



        JButton backButton = new JButton("Go Back");
        backButton.setBounds(0, 0, 100, 20);
        backButton.setBackground(sandy);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setVisible(false);
                settingsFrame.dispose();
                StartPage startPage = new StartPage();
            }
        });
        settingsFrame.add(backButton);


        JButton lightModeButton = new JButton("Light mode");
        lightModeButton.setBounds(150, 250, 130, 100);
        lightModeButton.setBackground(sandy);
        lightModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    //   tried out animation. conclusion - it sucks 
                    {
                        ColorManager colorManager = new ColorManager(1);
                        messageLabel.setText("Light Mode Activated!");
                        messageLabel.setBounds(150, 200, 300, 30);
                        if (timer != null && timer.isRunning()) {
                            timer.stop();
                        }
                        timer.start();
                        }
            }
        });

        settingsFrame.add(lightModeButton);

        JButton darkModeButton = new JButton("Dark mode");
        darkModeButton.setBounds(300, 250, 130, 100);
        darkModeButton.setBackground(sandy);
        darkModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    //   tried out animation. conclusion - it sucks 
                    {
                        ColorManager colorManager = new ColorManager(2);
                        messageLabel.setText("Dark Mode Activated!");
                        messageLabel.setBounds(300, 200, 300, 30);
                        if (timer != null && timer.isRunning()) {
                            timer.stop();
                        }
                        timer.start();
                        }
            }
        });
        

        settingsFrame.add(darkModeButton);

    }


    //private void showMessage() 
        
}
