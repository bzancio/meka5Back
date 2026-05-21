package metrica.meka5.dto;

public class Entry {
    private Long id;
    private int score;
    private double time;
    private Long userId;
    private Long levelId;

    public Entry() {}

    public Entry(Long id, int score, double time, Long userId, Long levelId) {
        this.id = id;
        this.score = score;
        this.time = time;
        this.userId = userId;
        this.levelId = levelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}