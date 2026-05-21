package metrica.meka5.service;

import metrica.meka5.models.Leaderboard;
import metrica.meka5.repository.LeaderboardRepository;
import metrica.meka5.repository.LevelRepository;
import metrica.meka5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LeaderboardServiceImpl implements LeaderboardService{

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Leaderboard> getScoreboard() {
        return leaderboardRepository.findAll();
    }

    @Override
    public Leaderboard saveScoreboard(Leaderboard leaderboard) {
        return leaderboardRepository.save(leaderboard);
    }

    @Override
    public List<Leaderboard> getLevelbyId(Long levelId) {
        if (!levelRepository.existsById(levelId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El nivel no existe");
        }
        return leaderboardRepository.findByLevel_IdOrderByPuntuacionDescTiempoAsc(levelId);
    }

    @Override
    public List<Leaderboard> getBestsLevelby(Long levelId) {
        if (!levelRepository.existsById(levelId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El nivel no existe");
        }
        return leaderboardRepository.findTop10ByLevel_IdOrderByPuntuacionDescTiempoAsc(levelId);
    }

    @Override
    public List<Leaderboard> getByLevelAndUser(Long levelId, Long userId) {
        if (!levelRepository.existsById(levelId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El nivel no existe");
        }
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe");
        }
        return leaderboardRepository.findByUser_IdAndLevel_IdOrderByPuntuacionDescTiempoAsc(userId,levelId);
    }
}
