package metrica.meka5.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "active_sessions")
public class ActiveSession {

	@Id
	private String tokenSession;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false)
	private LocalDateTime expirationDate;

	public ActiveSession() {
	}

	public String getTokenSession() {
		return tokenSession;
	}

	public void setTokenSesion(String tokenSesion) {
		this.tokenSession = tokenSesion;
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
