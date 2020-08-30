/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    // the state of the game logic
    private Grid grid;
    private LinkedList<Grid> moves;

    public boolean playing = false; // whether the game is running 
    private JLabel status; // Current status text, i.e. "Running..."

    // Game constants
    public static final int COURT_WIDTH = 370;
    public static final int COURT_HEIGHT = 390;

    public GameCourt(JLabel status) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        
        grid = new Grid();
        moves = new LinkedList<Grid>();

        // This key listener merges the grid in the direction of the arrow key pressed if the merged
        // grid isn't the same as the current grid
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (playing) {
                	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                		Grid g = grid.copyGrid();
                    	if (grid.mergeLeft()) {
                            moves.add(0, g);
                           	grid.generate();
                    	}
                        checkWinLose();
                        repaint();
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    	Grid g = grid.copyGrid();
                    	if (grid.mergeRight()) {
                    		moves.add(0, g);
                    		grid.generate();
                    	}
                    	checkWinLose();
                        repaint();
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    	Grid g = grid.copyGrid();
                    	if (grid.mergeDown()) {
                            moves.add(0, g);
                            grid.generate();
                        }
                    	checkWinLose();
                        repaint();
                    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    	Grid g = grid.copyGrid();
                    	if (grid.mergeUp()) {
                            moves.add(0, g);
                            grid.generate();
                    	}
                    	checkWinLose();
                        repaint();
                    }
                }
            }
        });

        this.status = status;
    }
    
    // checks to see if the game has been won or lost and changes status, disables undo, and 
    // writes score accordingly
    private void checkWinLose() {
    	if (grid.win()) {
    		playing = false;
    		status.setText("You win!");
    		Game.undo.setEnabled(false);
    		Score s = new Score(grid.getScore(), Game.name);
    		ScoreCourt.writeScore(s);
    	} else {
    		if (grid.gameOver()) {
        		playing = false;
            	status.setText("You lose!");
            	Game.undo.setEnabled(false);
        		Score s = new Score(grid.getScore(), Game.name);
            	ScoreCourt.writeScore(s);
            }
    	}
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        grid = new Grid();
        moves = new LinkedList<Grid>();
        grid.generate();
    	repaint();
        
        playing = true;
        status.setText("Running...");
        Game.undo.setEnabled(true);

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }
    
    //undoes the last move
    public void undo() {
    	if (!moves.isEmpty()) {
    		grid = moves.remove();
    		repaint();
    	}
    	requestFocusInWindow();
    }

    //draws the grid and current score
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        grid.draw(g);
        g.drawString("Current Score: " + Integer.toString(grid.getScore()), 10, 20);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}