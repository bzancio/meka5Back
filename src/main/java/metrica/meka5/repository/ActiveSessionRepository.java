package metrica.meka5.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import metrica.meka5.model.ActiveSession;
import metrica.meka5.model.User;

@Repository
public interface ActiveSessionRepository extends JpaRepository<ActiveSession, Long> {
	Optional<ActiveSession> findByTokenSessionAndExpirationDateAfter(String tokenSession, LocalDateTime now);
	@Query("SELECT s.user FROM ActiveSession s WHERE s.tokenSession = :tokenSession")
	Optional<User> findUserByTokenSession(@Param("tokenSession") String tokenSession);
	void deleteByExpirationDateBefore(LocalDateTime dateTime);
}
