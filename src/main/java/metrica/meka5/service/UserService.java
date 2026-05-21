package metrica.meka5.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean UserIdExists(long userId);
}
