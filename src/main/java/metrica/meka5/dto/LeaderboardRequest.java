package metrica.meka5.dto;

public class LeaderboardRequest {

    private String Username;
    private int score;
    private double time;
    private double wpm;
    private boolean uppercase;
    private boolean punctuation;
    
	public LeaderboardRequest(String username, int score, double time, double wpm, boolean uppercase,
			boolean punctuation) {
		super();
		this.Username = username;
		this.score = score;
		this.time = time;
		this.wpm = wpm;
		this.uppercase = uppercase;
		this.punctuation = punctuation;
	}
	public LeaderboardRequest() {
		super();
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public double getWpm() {
		return wpm;
	}
	public void setWpm(double wpm) {
		this.wpm = wpm;
	}
	public boolean isUppercase() {
		return uppercase;
	}
	public void setUppercase(boolean uppercase) {
		this.uppercase = uppercase;
	}
	public boolean isPunctuation() {
		return punctuation;
	}
	public void setPunctuation(boolean punctuation) {
		this.punctuation = punctuation;
	}
   
    

}
