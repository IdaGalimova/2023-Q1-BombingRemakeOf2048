import Classes.GameSetup;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PlayPage extends JFrame {
    GameSetup gameSetup = new GameSetup();
    Color darkBlue = new Color(38, 70, 83);
    Color mustardYellow = new Color(233, 198, 74);
    Color aqua = new Color(42, 157, 143);
    Font moonspaced = new Font("Monospaced", Font.ITALIC | Font.BOLD, 30);
    JButton[][] buttons;

    public PlayPage(JFrame previousFrame) {
        buttons = new JButton[4][4];
        
        // Setting up main frame:
        setTitle("PLAYING GAME");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(null);
        getContentPane().setBackground(darkBlue);

        // Setting up back button:
        JButton backButton = new JButton("Go Back");
        backButton.setBounds(0, 0, 100, 20);
        backButton.setBackground(aqua);
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
                buttons[i][j].setBackground(aqua);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

                if (gameSetup.getGrid()[i][j].getValue() == 0) {
                    buttons[i][j].setText("");
                    buttons[i][j].setBackground(mustardYellow);
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

                // gameSetup.printGrid();
                gameSetup.moveTiles("right");
                // gameSetup.printGrid();

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

                // gameSetup.printGrid();
                gameSetup.moveTiles("up");
                // gameSetup.printGrid();

                redrawGrid();
            }
        });


        // Moving tiles down:
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("DOWN"), "downKey");
        contentPane.getActionMap().put("downKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("down key pressed");

                // gameSetup.printGrid();
                gameSetup.moveTiles("down");
                // gameSetup.printGrid();

                redrawGrid();
            }
        });
    }

    public void redrawGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setText("" + gameSetup.getGrid()[i][j].getValue());
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                if (gameSetup.getGrid()[i][j].getValue() != 0) {
                    buttons[i][j].setBackground(aqua);
                }

                if (gameSetup.getGrid()[i][j].getValue() == 0) {
                    buttons[i][j].setText("");
                    buttons[i][j].setBackground(mustardYellow);
                }

            }
        }
    }

}
