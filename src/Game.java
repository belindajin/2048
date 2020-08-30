/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	public static JButton undo;
	public static String name;
	
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for local variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("2048");
        frame.setLocation(300, 300);
        
        // Frame that pops up at the beginning of the game and allows you to enter your username
        final JFrame enterName = new JFrame();
        enterName.setLocation(850, 300);
        
        // Username text field
        final JTextField username = new JTextField(10);
        enterName.add(username, BorderLayout.CENTER);
        
        // Done button for after you finish entering your name
        final JButton doneName = new JButton("Done");
        doneName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name = username.getText();
                doneName.setEnabled(false);
            }
        });
        enterName.add(doneName, BorderLayout.SOUTH);
        
        // Instructions window
        final JFrame instructionsFrame = new JFrame();
        instructionsFrame.setLocation(300, 200);
        
        // Actual instructions
        final JLabel space = new JLabel(" ");
        final JLabel space2 = new JLabel(" ");
        final JLabel instructions = new JLabel("   Use your arrow keys to move tiles. When two "
        		+ "tiles of the same value touch, they merge into one! Try to reach 2048 by merging"
        		+ " tiles.   ");
        instructionsFrame.add(space, BorderLayout.NORTH);
        instructionsFrame.add(space2, BorderLayout.SOUTH);
        instructionsFrame.add(instructions, BorderLayout.CENTER);
        

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);
        
        // Score panel
        final ScoreCourt scores = new ScoreCourt();
        frame.add(scores, BorderLayout.EAST);

        // Undo button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        
        // Action listener for undo button
        undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.undo();
            }
        });
        control_panel.add(undo);
        
        undo.setEnabled(true);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        // Put the name entry frame on the screen
        enterName.pack();
        enterName.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        enterName.setVisible(true);
        
        // Put the instructions frame on the screen
        instructionsFrame.pack();
        instructionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        instructionsFrame.setVisible(true);

        // Start game
        court.reset();
        doneName.setEnabled(true);
        name = "";
        
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}