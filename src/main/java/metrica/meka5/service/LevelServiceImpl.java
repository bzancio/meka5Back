package metrica.meka5.service;

import metrica.meka5.models.Level;
import metrica.meka5.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService{

    @Autowired
    private LevelRepository levelRepository;

    @Override
    public List<Level> getLevels() {
        return levelRepository.findAll();
    }

    @Override
    public boolean LevelIdExists(long levelId) {
        return levelRepository.existsById(levelId);
    }
}
