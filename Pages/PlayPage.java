import Classes.ColorManager;
import Classes.GameSetup;
import Classes.SuperTile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;

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

    JButton[][] buttons;
    JLabel scoreLabel = new JLabel();
    String scoreString = "Score: ";
    private List<ActionListener> bombableListeners = new ArrayList<>();

    ColorManager colorManager;


    JPanel sidePanel = new JPanel();
    JButton closeButton = new JButton("Close");
    JPanel panel = new JPanel(new GridLayout(4, 4));
    JLabel bombingsLeftText = new JLabel("Bombings left:");
    JLabel valueOftile = new JLabel();
    JLabel bombingsLeft = new JLabel();
    JFrame playFrame = new JFrame();
        

    public PlayPage(ColorManager colorManager) {
        this.colorManager = colorManager;
    }

    public void playPage() {

        buttons = new JButton[4][4];

    

        VictoryPage victoryPage = new VictoryPage(colorManager, playFrame);
        GameOverPage gameOverPage = new GameOverPage(colorManager);

        // Setting up main frame:
        
        playFrame.setTitle("PLAYING GAME");
        playFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playFrame.setSize(800, 600);
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
        backButton.setBackground(darkBluelighter);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playFrame.setVisible(false);
                playFrame.dispose();
                StartPage startPage = new StartPage(colorManager);
            }
        });

        // Setting up panel to show the SuperTile abilities
        sidePanel.setBounds(520, 100, 200, 400);
        sidePanel.setBackground(darkBluelighter);
        sidePanel.setVisible(false);
        playFrame.add(sidePanel);
        

        // Setting up the close button for the side panel

        
        sidePanel.add(closeButton);
        closeButton.setBounds(10, 10, 40, 20);

        // Setting up the panel for the buttons

       
        panel.setBounds(100, 100, 400, 400);

        playFrame.add(backButton);
        playFrame.add(panel);

        // Creating buttons

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                buttons[i][j] = new JButton("" + gameSetup.getGrid()[i][j].getValue());
                colorManager.determineColor(gameSetup.getGrid()[i][j]);
                System.out.println();
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

                            valueOftile.setText(superTile.getValue() + ": " + superTile.getCanBombTile());

                            // valueOftile.setBounds(570, 250, 70, 50);
                            valueOftile.setFont(sidePanelTextFont);
                            valueOftile.setForeground(Color.WHITE);
                            sidePanel.add(valueOftile);

                            bombingsLeftText.setFont(sidePanelTextFont);
                            bombingsLeftText.setForeground(Color.WHITE);
                            sidePanel.add(bombingsLeftText);

                            bombingsLeft.setText(superTile.getBombingsLeft() + "");
                            bombingsLeft.setFont(sidePanelTextFont);
                            bombingsLeft.setForeground(Color.WHITE);
                            sidePanel.add(bombingsLeft);

                            sidePanel.setVisible(true); // Show the side panel when a button is clicked
                            playFrame.setSize(800, 600);

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
                                        buttons[m][n].setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
                                        buttons[m][n].setEnabled(true);
                                    }
                                }
                            }

                            addBombableListener(pressedButtonI, pressedButtonJ);
                            playFrame.revalidate();
                            playFrame.repaint();
                            sidePanel.revalidate();
                            sidePanel.repaint();


                            

                            // Close button 
                          
                            closeButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    sidePanel.remove(valueOftile);
                                    sidePanel.remove(bombingsLeftText);
                                    sidePanel.remove(bombingsLeft);
                                    sidePanel.setVisible(false);

                                    // Unchanging the borders
                                    

                                    for (int m = 0; m < 4; m++) {
                                        for (int n = 0; n < 4; n++) {
                                            buttons[m][n].setEnabled(true);

                                            if (gameSetup.getGrid()[m][n].getValue() == superTile.calculateWhatTileSuperCanBomb()) {
                                                buttons[m][n].setBorder(BorderFactory.createLineBorder(darkBlue, 2));
                                            }

                                            removeBombableListeners();
                                        }
                                    }

                                    playFrame.setSize(800, 600);

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
                System.out.println("left key pressed");

                gameSetup.moveTiles("left");
                redrawGrid();

                if (gameSetup.checkGameOver()) {
                    gameOverPage.runGameOver();
                }
                scoreLabel.setText(scoreString + gameSetup.getScore());

                if (gameSetup.checkVictory()) {
                    VictoryPage victoryPage = new VictoryPage(colorManager, playFrame);
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
                    VictoryPage victoryPage = new VictoryPage(colorManager, playFrame);
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
                    VictoryPage victoryPage = new VictoryPage(colorManager, playFrame);
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
                    VictoryPage victoryPage = new VictoryPage(colorManager, playFrame);
                    victoryPage.victoryPage();
                    redrawGrid();
                }

            }
        });

    }

    private void addBombableListener(int pressedButtonI, int pressedButtonJ) {
        SuperTile superTile = (SuperTile) gameSetup.getGrid()[pressedButtonI][pressedButtonJ];
        for (int m = 0; m < 4; m++) {
            for (int n = 0; n < 4; n++) {
                int pressedM = m;
                int pressedN = n;
                if (gameSetup.getGrid()[m][n].getValue() == superTile.calculateWhatTileSuperCanBomb()) {
                    ActionListener listener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            gameSetup.bombATile(pressedButtonI, pressedButtonJ, pressedM, pressedN);
                            removeBombableListeners();
                            sidePanel.remove(valueOftile);
                            sidePanel.remove(bombingsLeft);
                            sidePanel.remove(bombingsLeftText);
                            redrawGrid();
                            //sidePanel.remove();
                            sidePanel.setVisible(false);
                            for (int m = 0; m < 4; m++) {
                                    for (int n = 0; n < 4; n++) {
                                        buttons[m][n].setEnabled(true);

                                            if (gameSetup.getGrid()[m][n].getValue() == superTile.calculateWhatTileSuperCanBomb()) {
                                                buttons[m][n].setBorder(BorderFactory.createLineBorder(darkBlue, 2));
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
    
    private void removeBombableListeners() {
        for (int m = 0; m < 4; m++) {
            for (int n = 0; n < 4; n++) {
                for (ActionListener listener : bombableListeners) {
                    buttons[m][n].removeActionListener(listener);
                }
            }
        }
        
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
