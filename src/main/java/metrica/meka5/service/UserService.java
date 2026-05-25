package metrica.meka5.service;

import metrica.meka5.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getUsers();
    boolean UserIdExists(long userId);
}
