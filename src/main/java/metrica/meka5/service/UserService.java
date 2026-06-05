package metrica.meka5.service;

import metrica.meka5.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> getUsers();
    Optional<User> getUser(String token);
    boolean UserIdExists(long userId);
}
