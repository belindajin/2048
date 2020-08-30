import java.awt.Dimension;
import java.awt.Graphics;
import java.io.*;
import java.util.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class ScoreCourt extends JPanel {
	private static LinkedList<Score> scores;
	private BufferedReader reader;
	private Score current;
	private boolean hasCurrent;
	
	// dimensions
	private final int COURT_WIDTH = 150;
	private final int COURT_HEIGHT = 300;
		
	// checks to see if the file has another line to read
	private boolean hasNext() {
		if (current == null) {
			hasCurrent = false;
		}
		boolean hasNext = hasCurrent;
		if (!hasCurrent) {
			try {
				reader.close();
			}
			catch (IOException e) {
				System.out.println("Error");
			}
		}
		return hasNext;
	}
	
	// reads two lines (one for player and one for score), creates Score out of Strings and adds it
	// to the scores list
	private Score next() {
		if (current == null) {
			throw new java.util.NoSuchElementException();
		}
		Score s = current;
		try {
			String currentPlayer = reader.readLine();
			String currentScore = reader.readLine();
			int cs = 0;
			if (currentScore != null) {
				cs = Integer.parseInt(currentScore);
			}
			
			if (currentPlayer != null) {
				Score n = new Score(cs, currentPlayer);
				scores.add(n);
			} else {
				current = null;
				hasCurrent = false;
			}
			
		}
		catch (IOException e) {
			current = null;
			hasCurrent = false;
		}
		return s;
	}
	
	// creates a new ScoreCourt which reads from highscores.txt
	public ScoreCourt() {
		scores = new LinkedList<Score>();
		
		try {
			reader = new BufferedReader(new FileReader("files/highscores.txt"));
		} catch (FileNotFoundException e) {
			reader = null;
			current = null;
			hasCurrent = false;
		}
		
		hasCurrent = true;
		Score s = new Score(0, "");
		current = s;
		while (hasNext()) {
			next();
		}
		
		Collections.sort(scores);
	}
	
	// checks to see if the list of Scores contains a given Score
	public boolean contains(Score score) {
		return scores.contains(score);
	}
	
	// writes a given Score to highscores.txt
	public static void writeScore(Score s) {
		scores.add(s);
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("files/highscores.txt"));
			
			for(int i = 0; i < scores.size(); i++) {
				writer.write(scores.get(i).getPlayer(), 0, 
						scores.get(i).getPlayer().length());
				writer.newLine();
				
				String score = Integer.toString(scores.get(i).getScore());
				writer.write(score, 0, score.length());
				writer.newLine();
			}
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("Error");
		}
	}
	
	// draws the high scores
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("High Scores", 10, 10);
        if (scores.size() >= 3) {
        	for (int i = 0; i < 3; i++) {
        		Score s = scores.get(scores.size() - 1 - i);
            	g.drawString(s.getPlayer() + ": " + Integer.toString(s.getScore()), 
            			10, i * 20 + 30);
            }
        } else if (scores.size() == 2) {
        	for (int i = 0; i < 2; i++) {
        		Score s = scores.get(scores.size() - 1 - i);
            	g.drawString(s.getPlayer() + ": " + Integer.toString(s.getScore()), 
            			10, i * 20 + 30);
            }
        } else if (scores.size() == 1) {
        	Score s = scores.get(scores.size() - 1);
        	g.drawString(s.getPlayer() + ": " + Integer.toString(s.getScore()), 10, 30);
        }
    }
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
	//in constructor read file and add all scores and names to list
	//in paint method print out all names and scores
}
