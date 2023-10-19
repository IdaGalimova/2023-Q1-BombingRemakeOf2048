import Classes.GameSetup;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PlayPage extends JFrame {
    GameSetup gameSetup = new GameSetup();
    

    Color darkBlue = new Color(0, 48, 73);
    Color sandy = new Color(234, 226, 183);
    Color yellow = new Color(252, 191, 73);
    Color red = new Color(214, 40, 40);
    Color orange = new Color(247, 127, 0);
    Font moonspaced = new Font("Monospaced", Font.ITALIC | Font.BOLD, 30);
    JButton[][] buttons;

    public PlayPage(JFrame previousFrame) {
        buttons = new JButton[4][4];

        VictoryPage victoryPage = new VictoryPage();
        
        // Setting up main frame:
        setTitle("PLAYING GAME");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(null);
        getContentPane().setBackground(darkBlue);

        // Setting up back button:
        JButton backButton = new JButton("Go Back");
        backButton.setBounds(0, 0, 100, 20);
        backButton.setBackground(yellow);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                previousFrame.setVisible(true);
            }
        });

        JPanel panel = new JPanel(new GridLayout(4, 4));
        panel.setBounds(90, 80, 400, 400);


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                buttons[i][j] = new JButton("" + gameSetup.getGrid()[i][j].getValue());
                gameSetup.determineTileColor(gameSetup.getGrid(), i, j);
                buttons[i][j].setBackground(gameSetup.getGrid()[i][j].getColor());
                buttons[i][j].setBorder(BorderFactory.createLineBorder(darkBlue, 2));

                if (gameSetup.getGrid()[i][j].getValue() == 0) {
                    buttons[i][j].setText("");
                }
                buttons[i][j].setFont(moonspaced);

                panel.add(buttons[i][j]);
            }
        }

        add(backButton);
        add(panel);

        // Moving tiles to the left:
        JComponent contentPane = (JComponent) getContentPane();
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("LEFT"), "leftKey");
        contentPane.getActionMap().put("leftKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("left key pressed");

                // gameSetup.printGrid();
                gameSetup.moveTiles("left");
                // gameSetup.printGrid();
                gameSetup.fillTileWithRandomNumber(gameSetup.getGrid());
                redrawGrid();

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
                gameSetup.fillTileWithRandomNumber(gameSetup.getGrid());
                redrawGrid();
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
                gameSetup.fillTileWithRandomNumber(gameSetup.getGrid());
                redrawGrid();
                
                if (gameSetup.checkVictory()) {
                    victoryPage.victoryPage();
                    redrawGrid();
                }
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
                gameSetup.fillTileWithRandomNumber(gameSetup.getGrid());
                redrawGrid();

                
            }
        });


        
    }

    public void redrawGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setText("" + gameSetup.getGrid()[i][j].getValue());
                buttons[i][j].setBorder(BorderFactory.createLineBorder(darkBlue, 2));
                if (gameSetup.getGrid()[i][j].getValue() != 0) {
                    gameSetup.determineTileColor(gameSetup.getGrid(), i, j);
                    buttons[i][j].setBackground(gameSetup.getGrid()[i][j].getColor());
                }

                if (gameSetup.getGrid()[i][j].getValue() == 0) {
                    gameSetup.determineTileColor(gameSetup.getGrid(), i, j);
                    buttons[i][j].setBackground(gameSetup.getGrid()[i][j].getColor());
                    buttons[i][j].setText("");
                }

            }
        }
    }

}
