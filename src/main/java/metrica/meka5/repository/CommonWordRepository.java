package metrica.meka5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import metrica.meka5.model.CommonWord;

@Repository
public interface CommonWordRepository extends JpaRepository<CommonWord, Long> {
	@Query(value = "SELECT * FROM common_words ORDER by RAND() LIMIT :limitSize", nativeQuery = true)
	List<CommonWord> findRandomWords(@Param("limitSize") int limitSize);
}
