package metrica.meka5.controller;

import metrica.meka5.models.Leaderboard;
import metrica.meka5.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping("/all")
    public ResponseEntity<List<Leaderboard>> getAll () {
        List<Leaderboard> response = leaderboardService.getScoreboard();
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<Leaderboard> create(@RequestBody Leaderboard leaderboard) {
        Leaderboard saved = leaderboardService.saveScoreboard(leaderboard);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/level/{levelId}")
    public ResponseEntity<List<Leaderboard>> getByLevelId (@PathVariable Long levelId) {
        List<Leaderboard> response = leaderboardService.getLevelbyId(levelId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // Cambiar
    }

    @GetMapping("/user/{levelId}")
    public ResponseEntity<List<Leaderboard>> getTop10ByLevelID (@PathVariable Long levelId) {
        List<Leaderboard> response = leaderboardService.getBestsLevelby(levelId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // Cambiar
    }

    @GetMapping("/best")
    public ResponseEntity<List<Leaderboard>> getByLevelAndUser(  @RequestParam Long levelId, @RequestParam Long userId) {
        List<Leaderboard> response = leaderboardService.getByLevelAndUser(levelId,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // Cambiar
    }
}
