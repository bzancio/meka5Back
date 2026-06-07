package metrica.meka5.dto;

public class LeaderboardResponse {

    private int score;
    private double time;
    private double wpm;
    private String token;
    private boolean uppercase;
    private boolean punctuation;
    
	public LeaderboardResponse(int score, double time, double wpm, String token, boolean uppercase, boolean punctuation) {
		super();
		this.score = score;
		this.time = time;
		this.wpm = wpm;
		this.token = token;
		this.uppercase = uppercase;
		this.punctuation = punctuation;
	}
	public LeaderboardResponse() {
		super();
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
