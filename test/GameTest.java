import static org.junit.Assert.*;

import org.junit.Test;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {
	
    @Test
    public void mergeRight() {
    	Grid g = new Grid();
    	g.getTile(1, 0).setValue(2);
    	g.getTile(0, 1).setValue(4);
    	g.getTile(2, 1).setValue(4);
    	g.getTile(0, 2).setValue(8);
    	g.getTile(1, 3).setValue(8);
    	g.getTile(2, 3).setValue(4);
    	g.mergeRight();
    	assertEquals(g.getTile(3, 1).getValue(), 8);
    	assertEquals(g.getTile(3, 2).getValue(), 8);
    	assertEquals(g.getTile(0, 0).getValue(), 0);
    	assertEquals(g.getTile(1, 0).getValue(), 0);
    	assertEquals(g.getTile(2, 0).getValue(), 0);
    	assertEquals(g.getTile(0, 1).getValue(), 0);
    	assertEquals(g.getTile(1, 1).getValue(), 0);
    	assertEquals(g.getTile(2, 1).getValue(), 0);
    	assertEquals(g.getTile(0, 2).getValue(), 0);
    	assertEquals(g.getTile(1, 2).getValue(), 0);
    	assertEquals(g.getTile(2, 2).getValue(), 0);
    	assertEquals(g.getTile(0, 3).getValue(), 0);
    	assertEquals(g.getTile(1, 3).getValue(), 0);
    	assertEquals(g.getTile(2, 3).getValue(), 8);
    	assertEquals(g.getTile(3, 3).getValue(), 4);
    	assertEquals(g.getTile(3, 0).getValue(), 2);
    }
    
    @Test
    public void mergeLeft() {
    	Grid g = new Grid();
    	g.getTile(1, 0).setValue(2);
    	g.getTile(0, 1).setValue(4);
    	g.getTile(2, 1).setValue(4);
    	g.getTile(0, 2).setValue(8);
    	g.getTile(1, 3).setValue(8);
    	g.getTile(2, 3).setValue(4);
    	g.mergeLeft();
    	assertEquals(g.getTile(0, 0).getValue(), 2);
    	assertEquals(g.getTile(1, 0).getValue(), 0);
    	assertEquals(g.getTile(2, 0).getValue(), 0);
    	assertEquals(g.getTile(3, 0).getValue(), 0);
    	assertEquals(g.getTile(0, 1).getValue(), 8);
    	assertEquals(g.getTile(1, 1).getValue(), 0);
    	assertEquals(g.getTile(2, 1).getValue(), 0);
    	assertEquals(g.getTile(3, 1).getValue(), 0);
    	assertEquals(g.getTile(0, 2).getValue(), 8);
    	assertEquals(g.getTile(1, 2).getValue(), 0);
    	assertEquals(g.getTile(2, 2).getValue(), 0);
    	assertEquals(g.getTile(3, 2).getValue(), 0);
    	assertEquals(g.getTile(0, 3).getValue(), 8);
    	assertEquals(g.getTile(1, 3).getValue(), 4);
    	assertEquals(g.getTile(2, 3).getValue(), 0);
    	assertEquals(g.getTile(3, 3).getValue(), 0);
    }
    
    @Test
    public void mergeUp() {
    	Grid g = new Grid();
    	g.getTile(0, 1).setValue(2);
    	g.getTile(1, 0).setValue(4);
    	g.getTile(1, 2).setValue(4);
    	g.getTile(2, 3).setValue(8);
    	g.getTile(3, 1).setValue(8);
    	g.getTile(3, 2).setValue(4);
    	g.mergeUp();
    	assertEquals(g.getTile(0, 0).getValue(), 2);
    	assertEquals(g.getTile(1, 0).getValue(), 8);
    	assertEquals(g.getTile(2, 0).getValue(), 8);
    	assertEquals(g.getTile(3, 0).getValue(), 8);
    	assertEquals(g.getTile(0, 1).getValue(), 0);
    	assertEquals(g.getTile(1, 1).getValue(), 0);
    	assertEquals(g.getTile(2, 1).getValue(), 0);
    	assertEquals(g.getTile(3, 1).getValue(), 4);
    	assertEquals(g.getTile(0, 2).getValue(), 0);
    	assertEquals(g.getTile(1, 2).getValue(), 0);
    	assertEquals(g.getTile(2, 2).getValue(), 0);
    	assertEquals(g.getTile(3, 2).getValue(), 0);
    	assertEquals(g.getTile(0, 3).getValue(), 0);
    	assertEquals(g.getTile(1, 3).getValue(), 0);
    	assertEquals(g.getTile(2, 3).getValue(), 0);
    	assertEquals(g.getTile(3, 3).getValue(), 0);
    }
    
    @Test
    public void mergeDown() {
    	Grid g = new Grid();
    	g.getTile(0, 1).setValue(2);
    	g.getTile(1, 0).setValue(4);
    	g.getTile(1, 2).setValue(4);
    	g.getTile(2, 3).setValue(8);
    	g.getTile(3, 1).setValue(8);
    	g.getTile(3, 2).setValue(4);
    	g.mergeDown();
    	assertEquals(g.getTile(0, 0).getValue(), 0);
    	assertEquals(g.getTile(1, 0).getValue(), 0);
    	assertEquals(g.getTile(2, 0).getValue(), 0);
    	assertEquals(g.getTile(3, 0).getValue(), 0);
    	assertEquals(g.getTile(0, 1).getValue(), 0);
    	assertEquals(g.getTile(1, 1).getValue(), 0);
    	assertEquals(g.getTile(2, 1).getValue(), 0);
    	assertEquals(g.getTile(3, 1).getValue(), 0);
    	assertEquals(g.getTile(0, 2).getValue(), 0);
    	assertEquals(g.getTile(1, 2).getValue(), 0);
    	assertEquals(g.getTile(2, 2).getValue(), 0);
    	assertEquals(g.getTile(3, 2).getValue(), 8);
    	assertEquals(g.getTile(0, 3).getValue(), 2);
    	assertEquals(g.getTile(1, 3).getValue(), 8);
    	assertEquals(g.getTile(2, 3).getValue(), 8);
    	assertEquals(g.getTile(3, 3).getValue(), 4);
    }
    
    @Test
    public void scoreCourtReader() {
    	ScoreCourt s = new ScoreCourt();
    	Score josh = new Score(280, "Josh");
    	assertTrue(s.contains(josh));
    }
    
    @Test
    public void win() {
    	Grid g = new Grid();
    	assertFalse(g.win());
    	g.getTile(2, 2).setValue(2048);
    	assertTrue(g.win());
    }
    
    @Test
    public void gameOver() {
    	Grid g = new Grid();
    	g.generate();
    	assertFalse(g.gameOver());
    	for (int x = 0; x < 4; x++) {
    		for (int y = 0; y < 4; y++) {
    			g.getTile(x, y).setValue(x + y + 1);
    		}
    	}
    	assertTrue(g.gameOver());
    }
    
    @Test
    public void scoreCourtWriter() {
    	ScoreCourt s = new ScoreCourt();
    	Score josh = new Score(10, "Josh");
    	ScoreCourt.writeScore(josh);
    	assertTrue(s.contains(josh));
    }
    
    @Test
    public void generate() {
    	Grid g = new Grid();
    	g.generate();
    	int total = 0;
    	for (int x = 0; x < 4; x++) {
    		for (int y = 0; y < 4; y++) {
    			total += g.getTile(x, y).getValue();
    		}
    	}
    	assertTrue(total == 2 || total == 4);
    }
}
