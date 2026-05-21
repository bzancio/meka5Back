package metrica.meka5.service;

import metrica.meka5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean UserIdExists(long userId) {
        return userRepository.existsById(userId);
    }
}
