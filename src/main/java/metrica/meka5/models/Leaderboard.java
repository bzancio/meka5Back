package metrica.meka5.models;

import jakarta.persistence.*;

@Entity
@Table(name = "leaderboard")
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int puntuacion;

    @Column(nullable = false)
    private double tiempo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Users user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "level_id")
    private Levels level;

    public Leaderboard() {}

    public Leaderboard(int puntuacion, double tiempo, Users user, Levels level) {
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        this.user = user;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Levels getLevel() {
        return level;
    }

    public void setLevel(Levels level) {
        this.level = level;
    }
}
