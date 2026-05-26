package metrica.meka5.service;

import org.springframework.stereotype.Service;

import metrica.meka5.model.Level;

import java.util.List;

@Service
public interface LevelService {
    List<Level> getLevels();
    boolean LevelIdExists(long levelIDd);
}
