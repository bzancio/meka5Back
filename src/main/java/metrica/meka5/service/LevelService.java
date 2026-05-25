package metrica.meka5.service;

import metrica.meka5.models.Level;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LevelService {
    List<Level> getLevels();
    boolean LevelIdExists(long levelIDd);
}
