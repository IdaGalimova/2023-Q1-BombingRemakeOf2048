import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.GameSetup;

class StartPage {
    public StartPage() {
        JFrame startPageWindowFrame = new JFrame("2048 MENU");
        
        // this is for existing the program
        startPageWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color darkBlue = new Color(0, 48, 73); 
        Color aqua = new Color(42, 157, 143);
        Color mustardYellow = new Color(234, 226, 183);
        // Create a JPanel to hold the buttons
        JPanel panel = new JPanel();
        panel.setOpaque(false); // makes it transparent to show the JFrame's color
        panel.setLayout(null);
        // creating three buttons, so far only play works

        // PLAY BUTTON
        JButton buttonPlay = new JButton("Play");
        buttonPlay.setBounds(200, 100, 200, 40);
        buttonPlay.setBackground(mustardYellow);
        buttonPlay.setForeground(darkBlue);

        GameSetup gameSetup = new GameSetup();
        buttonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the main frame
                startPageWindowFrame.setVisible(false);

                // Open the new frame
                PlayPage newFrameInstance = new PlayPage(startPageWindowFrame);
                newFrameInstance.setVisible(true);
            }
        });


        // RULES BUTTON
        JButton buttonRules = new JButton("Rules");
        buttonRules.setBounds(200, 150, 200, 40);
        buttonRules.setBackground(aqua);
        buttonRules.setForeground(darkBlue);
        


        // SETTINGS BUTTON
        JButton buttonSettings = new JButton("Settings");
        buttonSettings.setBounds(200, 200, 200, 40);
        buttonSettings.setBackground(mustardYellow);
        buttonSettings.setForeground(darkBlue);
        buttonSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the main frame
                startPageWindowFrame.setVisible(false);
                SettingsPage settingsPage = new SettingsPage();
                settingsPage.settingsPage();
            }
        });

        
        // Add the buttons to the panel
        panel.add(buttonPlay);
        panel.add(buttonRules);
        panel.add(buttonSettings);
        
        // Add the panel to the frame
        startPageWindowFrame.add(panel);
        // Create color for the background

        //Set color for background
        startPageWindowFrame.getContentPane().setBackground(darkBlue);
        
        // Set the size of the window
        startPageWindowFrame.setSize(600, 600);

        
        // Make the window visible
        startPageWindowFrame.setVisible(true);
    }

}
