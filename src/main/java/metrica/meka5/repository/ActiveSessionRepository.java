package metrica.meka5.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import metrica.meka5.model.ActiveSession;

@Repository
public interface ActiveSessionRepository extends JpaRepository<ActiveSession, Long> {
	Optional<ActiveSession> findByTokenSessionAndExpirationDateAfter(String tokenSession, LocalDateTime now);
	void deleteByExpirationDateBefore(LocalDateTime dateTime);
}
