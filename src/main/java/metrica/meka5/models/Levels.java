package metrica.meka5.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "levels")
public class Levels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean uppercase;

    @Column(nullable = false)
    private boolean numbers;

    @Column(nullable = false)
    private boolean punctuation;

    @OneToMany(mappedBy = "level")
    private List<Leaderboard> leaderboard;

    public Levels() {}

    public Levels(boolean uppercase, boolean numbers, boolean punctuation) {
        this.uppercase = uppercase;
        this.numbers = numbers;
        this.punctuation = punctuation;
    }

    public Long getId() {
        return id;
    }

    public boolean isUppercase() {
        return uppercase;
    }

    public void setUppercase(boolean uppercase) {
        this.uppercase = uppercase;
    }

    public boolean isNumbers() {
        return numbers;
    }

    public void setNumbers(boolean numbers) {
        this.numbers = numbers;
    }

    public boolean isPunctuation() {
        return punctuation;
    }

    public void setPunctuation(boolean punctuation) {
        this.punctuation = punctuation;
    }

    public List<Leaderboard> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(List<Leaderboard> leaderboard) {
        this.leaderboard = leaderboard;
    }
}
