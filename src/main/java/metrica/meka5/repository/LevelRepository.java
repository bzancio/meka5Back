package metrica.meka5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import metrica.meka5.model.Level;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
}
