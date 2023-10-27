import Classes.ColorManager;
import Classes.GameSetup;
import Classes.GameSetup;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SettingsPage {

    private float alpha = 1f;

    class TransparentLabel extends JLabel {
        private float alpha = 1f;  // Declare alpha inside TransparentLabel
    
        public TransparentLabel(String text) {
            super(text);
        }
    
        public void setAlpha(float alpha) {
            this.alpha = alpha;
            repaint();
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
            super.paintComponent(g2d);
            g2d.dispose();
        }
    }
    
    GameSetup gameSetup = new GameSetup();
    private ColorManager colorManager;
    TransparentLabel messageLabel;
    Timer timer = new Timer(50, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            alpha -= 0.05f;
            if (alpha <= 0f) {
                messageLabel.setAlpha(0f);  // This will now correctly set the alpha value of the label
                timer.stop();
                messageLabel.setText("");
            } else {
                messageLabel.setAlpha(alpha);  // Adjust the alpha of the label here
            }
        }
    });
    

    Color darkBlue = new Color(0, 48, 73);
    Color sandy = new Color(234, 226, 183);
    Color yellow = new Color(252, 191, 73);
    Color red = new Color(214, 40, 40);
    Color orange = new Color(247, 127, 0);
    Font moonspaced = new Font("Monospaced", Font.ITALIC | Font.BOLD, 15);

    
    public void settingsPage(ColorManager colorManager){
        this.colorManager = colorManager;

        JFrame settingsFrame = new JFrame();
        settingsFrame.setTitle("PLAYING GAME");
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsFrame.setSize(600, 600);
        settingsFrame.setLayout(null);
        settingsFrame.getContentPane().setBackground(darkBlue);
        settingsFrame.setVisible(true);


        messageLabel = new TransparentLabel(""); // Initialize with the custom TransparentLabel class
        messageLabel.setBounds(150, 200, 300, 30);
        messageLabel.setForeground(sandy);
        settingsFrame.add(messageLabel);



        


        JButton lightModeButton = new JButton("Light mode");
        lightModeButton.setBounds(150, 250, 130, 100);
        lightModeButton.setBackground(sandy);
        lightModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    //   tried out animation. conclusion - it sucks 
                    {
                        colorManager.setMode(1);
                        messageLabel.setText("Light Mode Activated!");
                        messageLabel.setBounds(150, 200, 300, 30);
                        if (timer != null && timer.isRunning()) {
                            timer.stop();
                        }
                        alpha = 1f;
                        messageLabel.setAlpha(1f);
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
                        colorManager.setMode(2);
                        messageLabel.setText("Dark Mode Activated!");
                        messageLabel.setBounds(300, 200, 300, 30);
                        if (timer != null && timer.isRunning()) {
                            timer.stop();
                        }
                        alpha = 1f;
                        messageLabel.setAlpha(1f);
                        timer.start();
                        }
            }
        });

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(0, 0, 100, 20);
        backButton.setBackground(sandy);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setVisible(false);
                settingsFrame.dispose();
                StartPage startPage = new StartPage(colorManager);
            }
        });
        settingsFrame.add(backButton);
        

        settingsFrame.add(darkModeButton);

    }


    //private void showMessage() 
        
}
