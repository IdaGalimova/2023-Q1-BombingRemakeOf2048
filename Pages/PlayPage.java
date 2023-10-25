import Classes.ColorManager;
import Classes.GameSetup;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PlayPage  {
    GameSetup gameSetup = new GameSetup();

    Color darkBlue = new Color(0, 48, 73);
    Color sandy = new Color(234, 226, 183);
    Color yellow = new Color(252, 191, 73);
    Color red = new Color(214, 40, 40);
    Color orange = new Color(247, 127, 0);
    Font moonspaced = new Font("Monospaced", Font.ITALIC | Font.BOLD, 30);
    Font scroreFont = new Font("Monospaced", Font.ITALIC | Font.BOLD, 15);

    JButton[][] buttons;
    JLabel scoreLabel = new JLabel();
    String scoreString = "Score: ";

    ColorManager colorManager;

    public PlayPage(ColorManager colorManager) {
        this.colorManager = colorManager;
    }
    
    public void playPage() {

        
        buttons = new JButton[4][4];

        VictoryPage victoryPage = new VictoryPage(colorManager);
        GameOverPage gameOverPage = new GameOverPage(colorManager);

        
        
        // Setting up main frame:
        JFrame playFrame = new JFrame();
        playFrame.setTitle("PLAYING GAME");
        playFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playFrame.setSize(600, 600);
        playFrame.setLayout(null);
        playFrame.getContentPane().setBackground(darkBlue);
        playFrame.setVisible(true);

        // Setting up score:
        scoreLabel.setText(scoreString + gameSetup.getScore());
        scoreLabel.setFont(scroreFont);
        scoreLabel.setForeground(orange);
        scoreLabel.setBounds(400, 0, 200, 20);
        playFrame.add(scoreLabel);

        // Setting up back button:
        JButton backButton = new JButton("Go Back");
        backButton.setBounds(0, 0, 100, 20);
        backButton.setBackground(yellow);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playFrame.setVisible(false);
                playFrame.dispose();
                StartPage startPage = new StartPage(colorManager);
            }
        });

        // Setting up panel to show the SuperTile abilities

        JPanel sidePanel = new JPanel();
        sidePanel.setBounds(520, 80, 60, 400); 
        sidePanel.setBackground(Color.BLUE); 
        sidePanel.setVisible(false); 
        playFrame.add(sidePanel);

        // Setting up the close button for the side panel

        JButton closeButton = new JButton("Close");
        sidePanel.add(closeButton);
        closeButton.setBounds(10, 10, 40, 20);

        JPanel panel = new JPanel(new GridLayout(4, 4));
        panel.setBounds(100, 100, 400, 400);

        playFrame.add(backButton);
        playFrame.add(panel);
        

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // we need to figure out how to pass the mode here
                
                buttons[i][j] = new JButton("" + gameSetup.getGrid()[i][j].getValue());
                colorManager.determineColor(gameSetup.getGrid()[i][j]);
                System.out.println();
                buttons[i][j].setBackground(gameSetup.getGrid()[i][j].getColor());
                buttons[i][j].setBorder(BorderFactory.createLineBorder(darkBlue, 2));

                if (gameSetup.getGrid()[i][j].getValue() == 0) {
                    buttons[i][j].setText("");
                }
                buttons[i][j].setFont(moonspaced);

                panel.add(buttons[i][j]);

                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        sidePanel.setVisible(true); // Show the side panel when a button is clicked

                          
                        closeButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                sidePanel.setVisible(false);
                                playFrame.remove(closeButton);  
                            }
                        });
                    }
                });

                playFrame.revalidate();
                playFrame.repaint();
            }
        }

        

        

        

        // Moving tiles to the left:
        JComponent contentPane = (JComponent) playFrame.getContentPane();
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke("LEFT"), "leftKey");
        contentPane.getActionMap().put("leftKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("left key pressed");

                gameSetup.moveTiles("left");
                redrawGrid();

                if (gameSetup.checkGameOver()) {
                    gameOverPage.runGameOver();
                }
                scoreLabel.setText(scoreString + gameSetup.getScore());
                
                if (gameSetup.checkVictory()) {
                    VictoryPage victoryPage = new VictoryPage(colorManager);
                    victoryPage.victoryPage();
                    redrawGrid();
                }

            }
        });

        // Moving tiles to the right:
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("RIGHT"), "rightKey");
        contentPane.getActionMap().put("rightKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("right key pressed");

                gameSetup.moveTiles("right");
                redrawGrid();

                if (gameSetup.checkGameOver()) {
                    gameOverPage.runGameOver();
                    redrawGrid();
                }

                scoreLabel.setText(scoreString + gameSetup.getScore());

                if (gameSetup.checkVictory()) {
                    VictoryPage victoryPage = new VictoryPage(colorManager);
                    victoryPage.victoryPage();
                    redrawGrid();
                }
            }
        });

        // Moving tiles up:
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("UP"), "upKey");
        contentPane.getActionMap().put("upKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("up key pressed");

                gameSetup.moveTiles("up");
                redrawGrid();

                if (gameSetup.checkGameOver()) {
                    gameOverPage.runGameOver();
                    redrawGrid();
                }

                if (gameSetup.checkVictory()) {
                    VictoryPage victoryPage = new VictoryPage(colorManager);
                    victoryPage.victoryPage();
                    redrawGrid();
                }

                scoreLabel.setText(scoreString + gameSetup.getScore());
            }
        });

        // Moving tiles down:
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("DOWN"), "downKey");
        contentPane.getActionMap().put("downKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("down key pressed");

                gameSetup.moveTiles("down");
                redrawGrid();

                if (gameSetup.checkGameOver()) {
                    gameOverPage.runGameOver();
                    redrawGrid();
                }
                scoreLabel.setText(scoreString + gameSetup.getScore());
                
                if (gameSetup.checkVictory()) {
                    VictoryPage victoryPage = new VictoryPage(colorManager);
                    victoryPage.victoryPage();
                    redrawGrid();
                }
                
            }
        });

    }

    public void redrawGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // we need to figure out how to pass the mode here
                buttons[i][j].setText("" + gameSetup.getGrid()[i][j].getValue());
                buttons[i][j].setBorder(BorderFactory.createLineBorder(darkBlue, 2));
                if (gameSetup.getGrid()[i][j].getValue() != 0) {
                    colorManager.determineColor(gameSetup.getGrid()[i][j]);
                    buttons[i][j].setBackground(gameSetup.getGrid()[i][j].getColor());
                }

                if (gameSetup.getGrid()[i][j].getValue() == 0) {
                    colorManager.determineColor(gameSetup.getGrid()[i][j]);
                    buttons[i][j].setBackground(gameSetup.getGrid()[i][j].getColor());
                    buttons[i][j].setText("");
                }

            }
        }
    }

}
