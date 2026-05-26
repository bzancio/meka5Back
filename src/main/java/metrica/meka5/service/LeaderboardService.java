package metrica.meka5.service;

import org.springframework.stereotype.Service;

import metrica.meka5.model.Leaderboard;

import java.util.List;

@Service
public interface LeaderboardService{
    List<Leaderboard> getScoreboard();
    Leaderboard saveScoreboard(Leaderboard leaderboard);
    List<Leaderboard> getLevelbyId(Long levelId);
    List<Leaderboard> getBestsLevelby(Long levelId);
    List<Leaderboard> getByLevelAndUser(Long levelId, Long userId);
}
