package metrica.meka5.repository;

import metrica.meka5.models.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderboardRepository  extends JpaRepository<Leaderboard, Long> {

    List<Leaderboard> findByLevel_IdOrderByScoreDescTimeAsc(Long levelId);
    List<Leaderboard> findTop10ByLevel_IdOrderByScoreDescTimeAsc(Long levelId);
    List<Leaderboard> findByUser_IdAndLevel_IdOrderByScoreDescTimeAsc(Long usuarioId, Long levelId);

}
