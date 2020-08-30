import java.io.*;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.*;

public class Grid {
	
	private Tile[][] array = new Tile[4][4];
	private int score;
	
	// merge helper function given two Tiles
	private void merge(Tile x, Tile y) {
		int xv = x.getValue();
		int yv = y.getValue();
		if (xv == yv) {
			y.setValue(2 * yv);
			x.setValue(0);
			score += (yv * 2);
		}
	}
	
	// Grid constructor (sets each Tile value to 0)
	public Grid() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				array[x][y] = new Tile(0);
			}
		}
		score = 0;
	}
	
	// gets Tile at given location
	public Tile getTile(int x, int y) {
		Tile t = array[x][y];
		return t;
	}
	
	// gets score
	public int getScore() {
		int s = score;
		return s;
	}
	
	// sets score to given integer
	private void setScore(int s) {
		score = s;
	}
	
	// returns a copy of the current Grid
	public Grid copyGrid() {
		Grid g = new Grid();
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				g.getTile(x, y).setValue(this.getTile(x, y).getValue());
			}
		}
		g.setScore(this.getScore());
		return g;
	}
	
	// moves Tile values as far right as possible
	private void moveRight() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				int value = array[3 - x][y].getValue();
				for (int i = 4 - x; i < 4; i++) {
					if (array[i][y].getValue() == 0) {
						array[i][y].setValue(value);
						array[i - 1][y].setValue(0);
					}
				}
			}
		}
	}
	
	// moves Tile values right, merges values, and then moves new values right
	public boolean mergeRight() {
		Grid g = copyGrid();
		
		moveRight();
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 3; x++) {
				merge(array[2 - x][y], array[3 - x][y]);
			}
		}
		moveRight();
		
		return !this.equals(g);
	}
	
	// moves Tile values as far left as possible
	private void moveLeft() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				int value = array[x][y].getValue();
				for (int i = x - 1; i >= 0; i--) {
					if (array[i][y].getValue() == 0) {
						array[i][y].setValue(value);
						array[i + 1][y].setValue(0);
					}
				}
			}
		}
	}
	
	// moves Tile values left, merges values, and then moves new values left
	public boolean mergeLeft() {
		Grid g = copyGrid();
		
		moveLeft();
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 3; x++) {
				merge(array[x + 1][y], array[x][y]);
			}
		}
		moveLeft();
		
		return !this.equals(g);
	}
	
	// moves Tile values as far up as possible
	private void moveUp() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				int value = array[x][y].getValue();
				for (int i = y - 1; i >= 0; i--) {
					if (array[x][i].getValue() == 0) {
						array[x][i].setValue(value);
						array[x][i + 1].setValue(0);
					}
				}
			}
		}
	}
	
	// moves Tile values up, merges values, and then moves new values up
	public boolean mergeUp() {
		Grid g = copyGrid();
		
		moveUp();
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 3; y++) {
				merge(array[x][y + 1], array[x][y]);
			}
		}
		moveUp();
		
		return !this.equals(g);
	}
	
	// moves Tile values as far down as possible
	private void moveDown() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				int value = array[x][3 - y].getValue();
				for (int i = 4 - y; i < 4; i++) {
					if (array[x][i].getValue() == 0) {
						array[x][i].setValue(value);
						array[x][i - 1].setValue(0);
					}
				}
			}
		}
	}
	
	// moves Tile values down, merges values, and then moves new values down
	public boolean mergeDown() {
		Grid g = copyGrid();
		
		moveDown();
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 3; y++) {
				merge(array[x][2 - y], array[x][3 - y]);
			}
		}
		moveDown();
		
		return !this.equals(g);
	}
	
	// randomly changes the value of a random Tile with current value of 0 to either 2 or 4
	public boolean generate() {
		int value = 0;
		double randomValue = Math.random();
		if (randomValue < 0.7) {
			value = 2;
		} else {
			value = 4;
		}
		
		LinkedList<int[]> empty = new LinkedList<int[]>();
		
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (array[x][y].getValue() == 0) {
					int[] coordinates = {x, y};
					empty.add(coordinates);
				}
			}
		}
		
		if (empty.isEmpty()) {
			return false;
		} else {
			int randomCoordinate = (int) Math.floor(Math.random() * empty.size());
			for (int i = 0; i < randomCoordinate - 1; i++) {
				empty.remove();
			}
			
			int[] coordinate = empty.remove();
			array[coordinate[0]][coordinate[1]].setValue(value);
			
			return true;
		}
	}
	
	// checks if two Grids are equal to one another
	public boolean equals(Grid g) {
		boolean b = true;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (!this.getTile(x, y).equals(g.getTile(x, y))) {
					b = false;
				}
			}
		}
		return b;
	}
	
	// checks to see if any Tiles can still be merged; if not, returns true
	public boolean gameOver() {
		Grid g = this.copyGrid();
		if (!(g.mergeDown() || g.mergeUp() || g.mergeRight() || g.mergeRight())) {
			return true;
		} else {
			return false;
		}
	}
	
	// checks to see if the game has been won (if any Tile has a value of 2048)
	public boolean win() {
		boolean b = false;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (this.getTile(x, y).getValue() == 2048) {
					b = true;
				}
			}
		}
		return b;
	}
	
	// draws the Grid
	public void draw(Graphics g) {
		try {
			BufferedImage tile00 = ImageIO.read
					(new File("files/" + array[0][0].getValue() + ".png"));
			BufferedImage tile10 = ImageIO.read
					(new File("files/" + array[1][0].getValue() + ".png"));
			BufferedImage tile20 = ImageIO.read
					(new File("files/" + array[2][0].getValue() + ".png"));
			BufferedImage tile30 = ImageIO.read
					(new File("files/" + array[3][0].getValue() + ".png"));
			BufferedImage tile01 = ImageIO.read
					(new File("files/" + array[0][1].getValue() + ".png"));
			BufferedImage tile11 = ImageIO.read
					(new File("files/" + array[1][1].getValue() + ".png"));
			BufferedImage tile21 = ImageIO.read
					(new File("files/" + array[2][1].getValue() + ".png"));
			BufferedImage tile31 = ImageIO.read
					(new File("files/" + array[3][1].getValue() + ".png"));
			BufferedImage tile02 = ImageIO.read
					(new File("files/" + array[0][2].getValue() + ".png"));
			BufferedImage tile12 = ImageIO.read
					(new File("files/" + array[1][2].getValue() + ".png"));
			BufferedImage tile22 = ImageIO.read
					(new File("files/" + array[2][2].getValue() + ".png"));
			BufferedImage tile32 = ImageIO.read
					(new File("files/" + array[3][2].getValue() + ".png"));
			BufferedImage tile03 = ImageIO.read
					(new File("files/" + array[0][3].getValue() + ".png"));
			BufferedImage tile13 = ImageIO.read
					(new File("files/" + array[1][3].getValue() + ".png"));
			BufferedImage tile23 = ImageIO.read
					(new File("files/" + array[2][3].getValue() + ".png"));
			BufferedImage tile33 = ImageIO.read
					(new File("files/" + array[3][3].getValue() + ".png"));
			
			g.drawImage(tile00, 10, 30, 80, 80, null);
			g.drawImage(tile10, 100, 30, 80, 80, null);
			g.drawImage(tile20, 190, 30, 80, 80, null);
			g.drawImage(tile30, 280, 30, 80, 80, null);
			g.drawImage(tile01, 10, 120, 80, 80, null);
			g.drawImage(tile11, 100, 120, 80, 80, null);
			g.drawImage(tile21, 190, 120, 80, 80, null);
			g.drawImage(tile31, 280, 120, 80, 80, null);
			g.drawImage(tile02, 10, 210, 80, 80, null);
			g.drawImage(tile12, 100, 210, 80, 80, null);
			g.drawImage(tile22, 190, 210, 80, 80, null);
			g.drawImage(tile32, 280, 210, 80, 80, null);
			g.drawImage(tile03, 10, 300, 80, 80, null);
			g.drawImage(tile13, 100, 300, 80, 80, null);
			g.drawImage(tile23, 190, 300, 80, 80, null);
			g.drawImage(tile33, 280, 300, 80, 80, null);
			
		} catch (IOException e) {
			System.out.println("Error");
		}
	}
	
}
