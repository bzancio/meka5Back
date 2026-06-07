package metrica.meka5.service;

import org.springframework.stereotype.Service;

import metrica.meka5.dto.LeaderboardRequest;
import metrica.meka5.dto.LeaderboardResponse;
import metrica.meka5.model.Leaderboard;

import java.util.List;

@Service
public interface LeaderboardService{
    List<LeaderboardRequest> getScoreboard();
    List<LeaderboardRequest> getMyScoreboard(String token);    
    Leaderboard saveScore(LeaderboardResponse leaderboardResponse);
}
