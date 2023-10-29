import Classes.ColorManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * This class implements StartPage. This page acts as the menu for the game and is displayed after
 * the program is executed.
 * 
 * @author Ida Galimova
 * @id 1958895
 * @author Skaiste Liutkute
 * @id 2004119
 */
class StartPage {
    private ColorManager colorManager;

    /** Implements the functionality of the page.
     */
    public StartPage(ColorManager colorManager) {

        JFrame startPageWindowFrame = new JFrame("2048 MENU");
        this.colorManager = colorManager;
        
        Color darkBlue = new Color(0, 48, 73); 
        Color aqua = new Color(42, 157, 143);
        Color mustardYellow = new Color(234, 226, 183);
        Font buttonFont = new Font("Monospaced", Font.ITALIC | Font.BOLD, 20);

        UIManager.put("ToolTip.background", aqua);
        UIManager.put("ToolTip.foreground", darkBlue);
        UIManager.put("ToolTip.font", new Font("Moonspaced", Font.ITALIC, 12));

        startPageWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startPageWindowFrame.setLayout(new GridBagLayout());

        // Create a JPanel to hold the buttons
        JPanel panel = new JPanel();
        panel.setOpaque(false); // makes it transparent to show the JFrame's color
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // PLAY BUTTON
        JButton buttonPlay = new JButton("Play");
        buttonPlay.setFont(buttonFont);
        buttonPlay.setBorder(BorderFactory.createLineBorder(darkBlue, 4));
        buttonPlay.setFocusable(false);
        buttonPlay.setToolTipText("Start the game!");
        buttonPlay.setMaximumSize(new Dimension(200, 50));  
        buttonPlay.setPreferredSize(new Dimension(200, 50));
        buttonPlay.setBackground(mustardYellow);
        buttonPlay.setForeground(darkBlue);

        buttonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startPageWindowFrame.setVisible(false);

                PlayPage playPage = new PlayPage(colorManager);
                playPage.playPage();
            }
        });

        // RULES BUTTON
        JButton buttonRules = new JButton("Rules");
        buttonRules.setMaximumSize(new Dimension(200, 50));  
        buttonRules.setPreferredSize(new Dimension(200, 50));
        buttonRules.setBackground(aqua);
        buttonRules.setForeground(darkBlue);

        // SETTINGS BUTTON
        JButton buttonSettings = new JButton("Settings");
        buttonSettings.setBorder(BorderFactory.createLineBorder(darkBlue, 4));
        buttonSettings.setFont(buttonFont);
        buttonSettings.setFocusable(false);
        buttonSettings.setToolTipText("Adjust game settings.");
        buttonSettings.setMaximumSize(new Dimension(200, 50));  
        buttonSettings.setPreferredSize(new Dimension(200, 50));
        buttonSettings.setBackground(mustardYellow);
        buttonSettings.setForeground(darkBlue);
        buttonSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the main frame
                startPageWindowFrame.setVisible(false);
                SettingsPage settingsPage = new SettingsPage();
                settingsPage.settingsPage(colorManager);
            }
        });

        
        // Add the buttons to the panel
        GridBagConstraints gbc = new GridBagConstraints();
        startPageWindowFrame.add(panel, gbc);
        panel.add(buttonPlay);
        panel.add(Box.createVerticalStrut(20));
        panel.add(buttonSettings);
        
        // Add the panel to the frame
        startPageWindowFrame.add(panel);
        // Create color for the background

        //Set color for background
        startPageWindowFrame.getContentPane().setBackground(colorManager.getBackgroundColor());
        
        // Set the size of the window
        startPageWindowFrame.setSize(600, 600);
        
        // Make the window visible
        startPageWindowFrame.setVisible(true);
    }

}
