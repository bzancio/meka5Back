package metrica.meka5.service;

import metrica.meka5.models.Leaderboard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaderboardService{
    List<Leaderboard> getScoreboard();
    Leaderboard saveScoreboard(Leaderboard leaderboard);
    List<Leaderboard> getLevelbyId(Long levelId);
    List<Leaderboard> getBestsLevelby(Long levelId);
    List<Leaderboard> getByLevelAndUser(Long levelId, Long userId);
}
