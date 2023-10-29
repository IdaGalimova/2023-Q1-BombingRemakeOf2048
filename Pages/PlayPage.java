import Classes.ColorManager;
import Classes.GameSetup;
import Classes.SuperTile;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * This class implements PlayPage. This is the page of the actual game. 
 * 
 * @author Ida Galimova
 * @id 1958895
 * @author Skaiste Liutkute
 * @id 2004119
 */
public class PlayPage {
    GameSetup gameSetup = new GameSetup();

    Color darkBlue = new Color(0, 48, 73);
    Color darkBluelighter = new Color(58, 94, 122);
    Color sandy = new Color(234, 226, 183);
    Color yellow = new Color(252, 191, 73);
    Color red = new Color(214, 40, 40);
    Color orange = new Color(247, 127, 0);
    Font moonspaced = new Font("Monospaced", Font.ITALIC | Font.BOLD, 30);
    Font sidePanelTextFont = new Font("Monospaced", Font.ITALIC | Font.BOLD, 20);
    Font scroreFont = new Font("Monospaced", Font.ITALIC | Font.BOLD, 25);
    Font rulesFont = new Font("Monospaced", Font.ITALIC | Font.BOLD, 15);
    JButton[][] buttons;
    JLabel scoreLabel = new JLabel();
    String scoreString = "Score: ";
    private List<ActionListener> bombableListeners = new ArrayList<>();

    ColorManager colorManager;

    JPanel sidePanelRight = new JPanel();
    JPanel sidePanelLeft = new JPanel();
    JButton closeButton = new JButton("Close");
    JPanel panel = new JPanel(new GridLayout(4, 4));
    JLabel bombingsLeftText = new JLabel("Bombings left:");
    JLabel valueOftile = new JLabel();
    JLabel bombingsLeft = new JLabel();
    JFrame playFrame = new JFrame();
        
    /** Initializes the colorManager.
     */
    public PlayPage(ColorManager colorManager) {
        this.colorManager = colorManager;
    }

    /** Main method for this page.
     */
    public void playPage() {
        buttons = new JButton[4][4];

        // Setting up main frame:
        playFrame.setTitle("PLAYING GAME");
        playFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playFrame.setSize(1000, 600);
        playFrame.setLayout(null);
        playFrame.getContentPane().setBackground(colorManager.getBackgroundColor());
        playFrame.setVisible(true);

        // Setting up score:
        scoreLabel.setText(scoreString + gameSetup.getScore());
        scoreLabel.setFont(scroreFont);
        scoreLabel.setForeground(colorManager.getTextColor());
        scoreLabel.setBounds(750, 0, 200, 20);
        playFrame.add(scoreLabel);

        // Setting up back button:
        JButton backButton = new JButton("Go Back");
        backButton.setBounds(40, 20, 235, 35);
        backButton.setBorder(BorderFactory.createLineBorder(darkBlue, 4));
        backButton.setBackground(darkBluelighter);
        backButton.setFont(sidePanelTextFont);
        backButton.setFocusable(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playFrame.setVisible(false);
                playFrame.dispose();
                new StartPage(colorManager);
            }
        });

        // Setting up panel to show the SuperTile abilities
        sidePanelRight.setBounds(725, 100, 210, 400);
        sidePanelRight.setBorder(BorderFactory.createLineBorder(darkBlue, 4));
        sidePanelRight.setBackground(darkBluelighter);
        sidePanelRight.setVisible(false);
        playFrame.add(sidePanelRight);

        // Setting up side panel for rules
        sidePanelLeft.setBounds(40, 100, 235, 400);
        sidePanelLeft.setBackground(darkBluelighter);
        sidePanelLeft.setVisible(false);
        playFrame.add(sidePanelLeft);

        JTextArea textArea = new JTextArea(15, 25);
        textArea.setBackground(darkBluelighter);
        textArea.setFocusable(false);
        textArea.setFont(rulesFont);
        textArea.setForeground(Color.WHITE);
        textArea.setText(
                "RULES: "
                + "\r\n"
                + "Goal: Merge tiles to get the 2048 tile.\r\n" 
                + "Moving Tiles: Swipe any direction to move tiles.\r\n"
                + "Merging: Tiles merge when they're the same "
                    + "number.\r\n" 
                + "Supertile: Tiles above 64 value.\r\n"
                + "Bomb Ability: Click a supertile to select it. It can" 
                + " bomb other tiles, up to three times.\r\n"
                + "Game Over: When the board fills up and no more moves are possible.\r\n"
                + "Enjoy the game!");
        textArea.setWrapStyleWord(true); // wrap lines by words, not characters
        textArea.setLineWrap(true);      // wrap lines
        sidePanelLeft.setBorder(BorderFactory.createLineBorder(darkBlue, 4));
        sidePanelLeft.add(textArea);

        // Setting up the close button for the side panel
        sidePanelRight.add(closeButton);
        closeButton.setBackground(darkBlue);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusable(false);
        closeButton.setFont(rulesFont);
        closeButton.setBounds(480, 10, 40, 20);

        // Setting up the panel for the buttons
        panel.setBounds(300, 100, 400, 400);
        panel.setBorder(BorderFactory.createLineBorder(darkBlue, 4));

        playFrame.add(backButton);
        playFrame.add(panel);

        sidePanelLeft.setVisible(true);

        // Setting up victory and game over pages:
        VictoryPage victoryPage = new VictoryPage(colorManager, playFrame);
        GameOverPage gameOverPage = new GameOverPage(colorManager, playFrame);

        // Creating buttons
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                buttons[i][j] = new JButton("" + gameSetup.getGrid()[i][j].getValue());
                colorManager.determineColor(gameSetup.getGrid()[i][j]);
                buttons[i][j].setFocusable(false);
                buttons[i][j].setBackground(gameSetup.getGrid()[i][j].getColor());
                buttons[i][j].setBorder(BorderFactory.createLineBorder(darkBlue, 2));
                buttons[i][j].setForeground(colorManager.getTextColor());

                if (gameSetup.getGrid()[i][j].getValue() == 0) {
                    buttons[i][j].setText("");
                }
                buttons[i][j].setFont(moonspaced);

                panel.add(buttons[i][j]);
            }
        }

        // Adding action listeners and bombing features
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int pressedButtonI = i;
                int pressedButtonJ = j;

                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameSetup.getGrid()
                            [pressedButtonI][pressedButtonJ] instanceof SuperTile) {
                            SuperTile superTile = (SuperTile) gameSetup.getGrid()
                                [pressedButtonI][pressedButtonJ];

                            valueOftile.setText(superTile.getValue() + ": " 
                                + superTile.getCanBombTile());

                            valueOftile.setFont(sidePanelTextFont);
                            valueOftile.setForeground(Color.WHITE);
                            sidePanelRight.add(valueOftile);

                            bombingsLeftText.setFont(sidePanelTextFont);
                            bombingsLeftText.setForeground(Color.WHITE);
                            sidePanelRight.add(bombingsLeftText);

                            bombingsLeft.setText(superTile.getBombingsLeft() + "");
                            bombingsLeft.setFont(sidePanelTextFont);
                            bombingsLeft.setForeground(Color.WHITE);
                            sidePanelRight.add(bombingsLeft);

                            // Show the side panel when a button is clicked
                            sidePanelRight.setVisible(true); 
                            playFrame.setSize(1000, 600);

                            // Adding borders to the tiles that can be bombed, disabling all
                            // buttons that cannot be bombed
                            for (int m = 0; m < 4; m++) {
                                for (int n = 0; n < 4; n++) {

                                    buttons[m][n].setEnabled(false);
                                    if (m == pressedButtonI && n == pressedButtonJ) {
                                        buttons[m][n].setEnabled(true);
                                    }

                                    if (gameSetup.getGrid()[m][n].getValue() 
                                        == superTile.calculateWhatTileSuperCanBomb()) {
                                        buttons[m][n].setBorder(BorderFactory
                                            .createLineBorder(Color.GREEN, 4));
                                        buttons[m][n].setEnabled(true);
                                    }
                                }
                            }

                            addBombableListener(pressedButtonI, pressedButtonJ);
                            playFrame.revalidate();
                            playFrame.repaint();

                            // Close button 
                            closeButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    sidePanelRight.remove(valueOftile);
                                    sidePanelRight.remove(bombingsLeftText);
                                    sidePanelRight.remove(bombingsLeft);
                                    sidePanelRight.setVisible(false);

                                    // Unchanging the borders
                                    for (int m = 0; m < 4; m++) {
                                        for (int n = 0; n < 4; n++) {
                                            buttons[m][n].setEnabled(true);

                                            if (gameSetup.getGrid()[m][n].getValue() 
                                                == superTile.calculateWhatTileSuperCanBomb()) {
                                                buttons[m][n].setBorder(BorderFactory
                                                    .createLineBorder(darkBlue, 2));
                                            }

                                            removeBombableListeners();
                                        }
                                    }

                                    playFrame.setSize(1000, 600);

                                }
                            });

                        }
                    }
                });

            }
        }

        // Moving tiles to the left:
        JComponent contentPane = (JComponent) playFrame.getContentPane();
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("LEFT"), "leftKey");
        contentPane.getActionMap().put("leftKey", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                gameSetup.moveTiles("left");
                redrawGrid();

                if (gameSetup.checkGameOver()) {
                    gameOverPage.runGameOver();
                }
                scoreLabel.setText(scoreString + gameSetup.getScore());

                if (gameSetup.checkVictory()) {
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

                gameSetup.moveTiles("right");
                redrawGrid();

                if (gameSetup.checkGameOver()) {
                    gameOverPage.runGameOver();
                    redrawGrid();
                }

                scoreLabel.setText(scoreString + gameSetup.getScore());

                if (gameSetup.checkVictory()) {
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

                gameSetup.moveTiles("up");
                redrawGrid();

                if (gameSetup.checkGameOver()) {

                    gameOverPage.runGameOver();
                    redrawGrid();
                }

                if (gameSetup.checkVictory()) {
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

                gameSetup.moveTiles("down");
                redrawGrid();

                if (gameSetup.checkGameOver()) {
                    gameOverPage.runGameOver();
                    redrawGrid();
                }
                scoreLabel.setText(scoreString + gameSetup.getScore());

                if (gameSetup.checkVictory()) {
                    victoryPage.victoryPage();
                    redrawGrid();
                }
            }
        });

    }

    /** Creates a listener for each button that can be bombed and adds it to the list.
     */
    private void addBombableListener(int pressedButtonI, int pressedButtonJ) {
        SuperTile superTile = (SuperTile) gameSetup.getGrid()[pressedButtonI][pressedButtonJ];
        for (int m = 0; m < 4; m++) {
            for (int n = 0; n < 4; n++) {
                int pressedM = m;
                int pressedN = n;
                if (gameSetup.getGrid()[m][n].getValue() 
                    == superTile.calculateWhatTileSuperCanBomb()) {
                    ActionListener listener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gameSetup.bombATile(pressedButtonI, pressedButtonJ, pressedM, pressedN);
                            removeBombableListeners();
                            sidePanelRight.remove(valueOftile);
                            sidePanelRight.remove(bombingsLeft);
                            sidePanelRight.remove(bombingsLeftText);
                            redrawGrid();
                            //sidePanel.remove();
                            sidePanelRight.setVisible(false);
                            for (int m = 0; m < 4; m++) {
                                for (int n = 0; n < 4; n++) {
                                    buttons[m][n].setEnabled(true);
                                    if (gameSetup.getGrid()[m][n].getValue() 
                                        == superTile.calculateWhatTileSuperCanBomb()) {
                                        buttons[m][n].setBorder(BorderFactory
                                            .createLineBorder(darkBlue, 2));
                                    }
                                }
                            }
                        }
                    };
                    buttons[m][n].addActionListener(listener);
                    bombableListeners.add(listener);
                }
            }
        }
    }
    
    /** Clears all of the listeners for the tiles that can be bombed.
     */
    private void removeBombableListeners() {
        for (int m = 0; m < 4; m++) {
            for (int n = 0; n < 4; n++) {
                for (ActionListener listener : bombableListeners) {
                    buttons[m][n].removeActionListener(listener);
                }
            }
        }
        
    }

    /** Redraws the grid.
     */
    public void redrawGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
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
