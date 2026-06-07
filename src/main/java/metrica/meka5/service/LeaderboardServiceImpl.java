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
        				(l.getTime()+l.getWpm())/2,
        				l.getLevel().isUppercase(), 
        				l.getLevel().isPunctuation()))
        		.sorted((l1,l2) -> Double.compare(
        				((l2.getScore()+l2.getWpm())/2),
        				((l1.getScore()+l1.getWpm())/2)))
        		.limit(50)
        		.collect(Collectors.toList());
    }


	@Override
	@Transactional
	public Leaderboard saveScore(LeaderboardResponse leaderboardResponse) {
		
		User usuario = activeSessionRepository.findUserByTokenSession(leaderboardResponse.getToken()).orElseThrow(() -> new RuntimeException("Sesion no valida o expirada"));
		Level level = levelRepository.findByUppercaseAndPunctuation(leaderboardResponse.isUppercase(), leaderboardResponse.isPunctuation()).orElseThrow(() -> new RuntimeException("El nivel no existe"));

		Leaderboard leaderboard = new Leaderboard(leaderboardResponse.getScore(), leaderboardResponse.getTime(), leaderboardResponse.getWpm(), usuario, level);
		return leaderboardRepository.save(leaderboard);
	}


	@Override
	public List<LeaderboardRequest> getMyScoreboard(String token) {
		
		User usuario = activeSessionRepository.findUserByTokenSession(token).orElseThrow(() -> new RuntimeException("Sesion no valida o expirada"));
		List<Leaderboard> leaderboard = leaderboardRepository.findAll();
        return leaderboard.stream()
        		.filter(l -> l.getUser().getId().equals(usuario.getId()))
        		.map(l -> new LeaderboardRequest(
        				l.getUser().getUsername(), 
        				l.getScore(), 
        				l.getTime(), 
        				l.getWpm(), 
        				(l.getTime()+l.getWpm())/2,
        				l.getLevel().isUppercase(), 
        				l.getLevel().isPunctuation()))
        		.sorted((l1,l2) -> Double.compare(
        				((l2.getScore()+l2.getWpm())/2),
        				((l1.getScore()+l1.getWpm())/2)))
        		.limit(50)
        		.collect(Collectors.toList());
	}
}
