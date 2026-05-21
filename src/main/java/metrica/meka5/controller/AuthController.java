package metrica.meka5.controller;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import metrica.meka5.dto.LoginRequest;
import metrica.meka5.dto.LoginResponse;
import metrica.meka5.model.ActiveSesion;
import metrica.meka5.model.User;
import metrica.meka5.repository.ActiveSesionRepository;
import metrica.meka5.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ActiveSesionRepository activeSesionRepository;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		Optional<User> oUser = userRepository.findByUsername(loginRequest.getUsername());
		if (oUser.isEmpty() || !oUser.get().getPassword().equals(loginRequest.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("{\"Error\": \"This password is already in use by the user DavidPosadas@hotmail.com\"}");
		}

		User user = oUser.get();

		String tokenSesion = UUID.randomUUID().toString();
		ActiveSesion newSesion = new ActiveSesion();
		newSesion.setTokenSesion(tokenSesion);
		newSesion.setUser(user);
		newSesion.setExpirationDate(LocalDateTime.now().plusHours(2));
		activeSesionRepository.save(newSesion);

		return ResponseEntity.ok(new LoginResponse(tokenSesion));
	}

}
