
public class Score implements Comparable<Object> {
	private int score;
	private String player;
	
	// creates a new Score given an integer and string
	public Score(int score, String player) {
		this.score = score;
		this.player = player;
	}
	
	// returns the score value
	public int getScore() {
		int s = score;
		return s;
	}
	
	// returns the player name
	public String getPlayer() {
		String p = player;
		return p;
	}
	
	@Override
	public boolean equals(Object o) {
		Score s = (Score) o;
		return (s.getScore() == this.getScore() && s.getPlayer().equals(this.getPlayer()));
	}
	
	@Override
	public int compareTo(Object o) {
		Score s = (Score) o;
		return (this.getScore() - s.getScore());
	}
}
