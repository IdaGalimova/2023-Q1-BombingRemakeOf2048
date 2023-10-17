import javax.swing.*;
import java.awt.*;

import Classes.GameSetup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

public class PlayPage extends JFrame{
    
    public PlayPage (JFrame previousFrame) {
        
        //SETING UP COLORS AND FONT
        Color darkBlue = new Color(38, 70, 83);
        Color mustardYellow = new Color(233, 198, 74);
        Color aqua = new Color(42, 157, 143);
        Font moonspaced = new Font("Monospaced", Font.ITALIC | Font.BOLD, 30);


        // SETTING UP MAIN FRAME
        setTitle("PLAYING GAME");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(600, 600);
        setLayout(null);
        getContentPane().setBackground(darkBlue);


        // SETTING UP BACK BUTTON
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
        
        //
        GameSetup gameSetup = new GameSetup();

        // CREATING GRID AND SCORE LABEL

        JPanel panel = new JPanel(new GridLayout(4, 4));
        //JLabel score = new JLabel("Your score is " + gameSetup.score);
       // score.setBounds(450, 500, 200, 20);
        panel.setBounds(90, 80, 400, 400);

        // CREATING ARRAY OF BUTONS AND ASSIGNING VALUES OF GRID TO THE BUTTONS AS TEXT
        // if the value is  0 then the text is just ""
        // yellow is for empty tiles and aqua is for filled tiles
        // when pressed the buttons boarders will turn green (i tried this to see if 
        // we will be able to implement bombing feature with my code)

        JButton[][] buttons = new JButton[4][4];


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                buttons[i][j] = new JButton("" + gameSetup.getGrid()[i][j].getValue()); 
                buttons[i][j].setBackground(aqua);

                System.out.println(gameSetup.getGrid()[i][j].getValue());

                if (gameSetup.getGrid()[i][j].getValue() == 0) {
                    buttons[i][j].setText("");
                    buttons[i][j].setBackground(mustardYellow);
                }
                buttons[i][j].setFont(moonspaced);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

                // these next integers are necessary because when setting action listener the values
                //used have to be final - that means that the value of them will not change.
                // i didnt get it fully but from what i understood with each iteration the ints
                // iPask and jPask are created like new ints again so although it changes with each
                // loop just like the i and j, they are not directly directly tied with loop

                int iPask = i;
                int jPask = j;

                //WHEN PRESSED ON A BUTTON CREATES A BOARDER AND BUTTON CLOSE
                // when pressed close changes the boarder to normal
                // tries this out to see how we will be able to implement the
                // select menu for bombing

                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                public void actionPerformed(ActionEvent e) {
                        buttons[iPask][jPask].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                        JButton whatToDo = new JButton("Close", null); 
                        whatToDo.setBounds(0, 30, 100, 20);
                        whatToDo.setBackground(Color.RED);
                        add(whatToDo);  
                        revalidate();
                        repaint(); 


                        whatToDo.addActionListener(new ActionListener() {
                            @Override
                public void actionPerformed(ActionEvent e) {
                            buttons[iPask][jPask].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                                remove(whatToDo);
                                revalidate();
                                repaint();                
                            }
                        });

                    }
                });

                panel.add(buttons[i][j]);
            }
        }




      //  add(score);
        add(backButton);
        add(panel);

        // this part i dont really understand so far i just copied the code just to try it out
        JComponent contentPane = (JComponent) getContentPane();
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "leftKey");
        contentPane.getActionMap().put("leftKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("left key pressed");
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        buttons[i][j].setText("" + gameSetup.getGrid()[i][j].getValue());

                        if (gameSetup.getGrid()[i][j].getValue() == 0) {
                            buttons[i][j].setText("");
                            buttons[i][j].setBackground(mustardYellow);
                        }

                    }
                }

            }
        });

        
        

        


    }
}
