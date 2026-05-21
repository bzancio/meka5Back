package metrica.meka5.service;

import metrica.meka5.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelServiceImpl implements LevelService{

    @Autowired
    private LevelRepository levelRepository;

    @Override
    public boolean LevelIdExists(long levelId) {
        return levelRepository.existsById(levelId);
    }
}
