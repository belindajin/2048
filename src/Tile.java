
public class Tile {
	
	private int value;
	
	// creates a Tile
	public Tile() {
		this.value = 0;
	}
	
	// creates a Tile given a value
	public Tile(int value) {
		this.value = value;
	}
	
	// retrieves the value of a Tile
	public int getValue() {
		int v = this.value;
		return v;
	}
	
	// sets the value of a Tile to a given integer value
	public void setValue(int x) {
		this.value = x;
	}
	
	// checks to see if two Tiles are equal
	public boolean equals(Tile x) {
		if (x.getValue() == this.getValue()) {
			return true;
		} else {
			return false;
		}
	}
	
}
