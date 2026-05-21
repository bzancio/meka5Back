package metrica.meka5.service;

import org.springframework.stereotype.Service;

@Service
public interface LevelService {
    boolean LevelIdExists(long levelIDd);
}
