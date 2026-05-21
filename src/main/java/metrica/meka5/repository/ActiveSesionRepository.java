package metrica.meka5.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import metrica.meka5.model.ActiveSesion;

@Repository
public interface ActiveSesionRepository extends JpaRepository<ActiveSesion, String> {
	Optional<ActiveSesion> findByTokenSessionAndExpirationDateAfter(String token, LocalDateTime now);
}
