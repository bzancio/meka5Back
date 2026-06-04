package metrica.meka5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import metrica.meka5.model.Level;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
	
	Optional<Level> findByUppercaseAndPunctuation(boolean uppercase, boolean punctuation);
}
