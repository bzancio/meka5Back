package metrica.meka5.controller;

import metrica.meka5.model.User;
import metrica.meka5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll () {
        List<User> response = userService.getUsers();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/user")
    public ResponseEntity<?> getNameByToken (@RequestParam("token") String token) {
        Optional<User> response = userService.getUser(token);
        if(response.isPresent()) {
        	return ResponseEntity.ok(response.get().getUsername());
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje","Sesion no valida o expirada"));
        }
    }
}
