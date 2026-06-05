package metrica.meka5.service;

import metrica.meka5.model.User;
import metrica.meka5.repository.ActiveSessionRepository;
import metrica.meka5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActiveSessionRepository activeSessionRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean UserIdExists(long userId) {
        return userRepository.existsById(userId);
    }

	@Override
	public Optional<User> getUser(String token) {
		return activeSessionRepository.findUserByTokenSession(token);
	}
}
