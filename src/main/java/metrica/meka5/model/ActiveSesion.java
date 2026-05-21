package metrica.meka5.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "active_sesion")
public class ActiveSesion {

	@Id
	private String tokenSesion;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false)
	private LocalDateTime expirationDate;

	public ActiveSesion() {
	}

	public String getTokenSesion() {
		return tokenSesion;
	}

	public void setTokenSesion(String tokenSesion) {
		this.tokenSesion = tokenSesion;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}
}
