package metrica.meka5.service;

import metrica.meka5.dto.LeaderboardRequest;
import metrica.meka5.dto.LeaderboardResponse;
import metrica.meka5.model.ActiveSession;
import metrica.meka5.model.Leaderboard;
import metrica.meka5.model.Level;
import metrica.meka5.model.User;
import metrica.meka5.repository.ActiveSessionRepository;
import metrica.meka5.repository.LeaderboardRepository;
import metrica.meka5.repository.LevelRepository;
import metrica.meka5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderboardServiceImpl implements LeaderboardService{

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ActiveSessionRepository activeSessionRepository;

    @Override
    public List<LeaderboardRequest> getScoreboard() {
        List<Leaderboard> leaderboard = leaderboardRepository.findAll();
        return leaderboard.stream()
        		.map(l -> new LeaderboardRequest(
        				l.getUser().getUsername(), 
        				l.getScore(), 
        				l.getTime(), 
        				l.getWpm(), 
        				l.getLevel().isUppercase(), 
        				l.getLevel().isPunctuation()))
        		.collect(Collectors.toList());
    }

    @Override
    public Leaderboard saveScoreboard(Leaderboard leaderboard) {
        return leaderboardRepository.save(leaderboard);
    }

    @Override
    public List<Leaderboard> getLevelbyId(Long levelId) {
        if (!levelRepository.existsById(levelId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Level not found");
        }
        return leaderboardRepository.findByLevel_IdOrderByScoreDescTimeAsc(levelId);
    }

    @Override
    public List<Leaderboard> getBestsLevelby(Long levelId) {
        if (!levelRepository.existsById(levelId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Level not found");
        }
        return leaderboardRepository.findTop10ByLevel_IdOrderByScoreDescTimeAsc(levelId);
    }

    @Override
    public List<Leaderboard> getByLevelAndUser(Long levelId, Long userId) {
        if (!levelRepository.existsById(levelId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Level not found");
        }
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return leaderboardRepository.findByUser_IdAndLevel_IdOrderByScoreDescTimeAsc(userId,levelId);
    }

	@Override
	@Transactional
	public Leaderboard saveScore(LeaderboardResponse leaderboardResponse) {
		
		User usuario = activeSessionRepository.findByTokenSession(leaderboardResponse.getToken()).orElseThrow(() -> new RuntimeException("Sesion no valida o expirada"));
		Level level = levelRepository.findByUppercaseAndPunctuation(leaderboardResponse.isUppercase(), leaderboardResponse.isPunctuation()).orElseThrow(() -> new RuntimeException("El nivel no existe"));

		Leaderboard leaderboard = new Leaderboard(leaderboardResponse.getScore(), leaderboardResponse.getTime(), leaderboardResponse.getWpm(), usuario, level);
		return leaderboardRepository.save(leaderboard);
	}
}
