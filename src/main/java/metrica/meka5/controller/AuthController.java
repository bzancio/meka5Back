package metrica.meka5.controller;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import metrica.meka5.dto.LoginRequest;
import metrica.meka5.dto.LoginResponse;
import metrica.meka5.dto.ChangePasswordRequest; // Import the new DTO
import metrica.meka5.model.ActiveSession;
import metrica.meka5.model.User;
import metrica.meka5.repository.ActiveSessionRepository;
import metrica.meka5.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ActiveSessionRepository activeSesionRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody LoginRequest registerRequest) {

		if (registerRequest.getUsername() == null || registerRequest.getUsername().trim().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Username cannot be empty\"}");
		}

		if (registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Password cannot be empty\"}");
		}

		Optional<User> oUser = userRepository.findByUsername(registerRequest.getUsername());
		if (oUser.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{\"error\": \"This username is already in use\"}");
		}

		User newUser = new User();
		newUser.setUsername(registerRequest.getUsername());
		String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());
		newUser.setPassword(hashedPassword);
		userRepository.save(newUser);

		return ResponseEntity.ok("{\"message\": \"Register success\"}");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

		if (loginRequest.getUsername() == null || loginRequest.getUsername().trim().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Username cannot be empty\"}");
		}

		if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Password cannot be empty\"}");
		}

		Optional<User> oUser = userRepository.findByUsername(loginRequest.getUsername());

		if (oUser.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), oUser.get().getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Invalid username or password\"}");
		}

		User user = oUser.get();

		String tokenSesion = UUID.randomUUID().toString();
		ActiveSession newSession = new ActiveSession();
		newSession.setTokenSesion(tokenSesion);
		newSession.setUser(user);
		newSession.setExpirationDate(LocalDateTime.now().plusHours(2));
		activeSesionRepository.save(newSession);

		return ResponseEntity.ok(new LoginResponse(tokenSesion));
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationHeader) {
		
		if (authorizationHeader == null || authorizationHeader.trim().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{\"error\": \"Authorization header is missing\"}");
		}

		String token = authorizationHeader.replace("Bearer ", "").trim();

		Optional<ActiveSession> oSession = activeSesionRepository.findByTokenSessionAndExpirationDateAfter(token, LocalDateTime.now());

		if (oSession.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\": \"Session not found or already expired\"}");
		}

		activeSesionRepository.delete(oSession.get());

		return ResponseEntity.ok("{\"message\": \"Logout success\"}");
	}

	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
		if (changePasswordRequest.getCurrentUsername() == null || changePasswordRequest.getCurrentUsername().trim().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Current username cannot be empty\"}");
		}
		if (changePasswordRequest.getCurrentPassword() == null || changePasswordRequest.getCurrentPassword().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Current password cannot be empty\"}");
		}
		if (changePasswordRequest.getNewPassword() == null || changePasswordRequest.getNewPassword().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"New password cannot be empty\"}");
		}

		Optional<User> oUser = userRepository.findByUsername(changePasswordRequest.getCurrentUsername());
		if (oUser.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"User not found\"}");
		}

		User user = oUser.get();

		if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Invalid current password\"}");
		}

		if (changePasswordRequest.getNewUsername() != null && !changePasswordRequest.getNewUsername().trim().isEmpty() &&
			!changePasswordRequest.getNewUsername().equals(user.getUsername())) {
			
			Optional<User> existingUserWithNewUsername = userRepository.findByUsername(changePasswordRequest.getNewUsername());
			if (existingUserWithNewUsername.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\": \"New username is already in use\"}");
			}
			user.setUsername(changePasswordRequest.getNewUsername());
		}

		String newHashedPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());
		user.setPassword(newHashedPassword);

		userRepository.save(user);

		return ResponseEntity.ok("{\"message\": \"Password and/or username updated successfully\"}");
	}
}
