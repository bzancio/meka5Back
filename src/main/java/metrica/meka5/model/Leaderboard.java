package metrica.meka5.model;

import jakarta.persistence.*;

@Entity
@Table(name = "leaderboard")
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private double time;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "level_id")
    private Level level;

    public Leaderboard() {}

    public Leaderboard(int puntuacion, double tiempo, User user, Level level) {
        this.score = puntuacion;
        this.time = tiempo;
        this.user = user;
        this.level = level;
    }

    public Long getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
