package metrica.meka5.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import metrica.meka5.repository.ActiveSessionRepository;

@Service
public class SessionCleanupService {

    @Autowired
    private ActiveSessionRepository activeSessionRepository;

    @Transactional
    @Scheduled(fixedRate = 3600000) 
    public void cleanupExpiredSessions() {
        LocalDateTime now = LocalDateTime.now();
        
        activeSessionRepository.deleteByExpirationDateBefore(now);
        
        System.out.println("--- Tarea automática: Sesiones caducadas limpiadas a las " + now + " ---");
    }
}