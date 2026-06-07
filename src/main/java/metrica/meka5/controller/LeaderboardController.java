package metrica.meka5.controller;

import metrica.meka5.dto.LeaderboardRequest;
import metrica.meka5.dto.LeaderboardResponse;
import metrica.meka5.model.Leaderboard;
import metrica.meka5.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping("/all")
    public ResponseEntity<List<LeaderboardRequest>> getAll () {
        List<LeaderboardRequest> response = leaderboardService.getScoreboard();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/level/{levelId}")
    public ResponseEntity<List<Leaderboard>> getByLevelId (@PathVariable Long levelId) {
        List<Leaderboard> response = leaderboardService.getLevelbyId(levelId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{levelId}")
    public ResponseEntity<List<Leaderboard>> getTop10ByLevelID (@PathVariable Long levelId) {
        List<Leaderboard> response = leaderboardService.getBestsLevelby(levelId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/best")
    public ResponseEntity<List<Leaderboard>> getByLevelAndUser(  @RequestParam Long levelId, @RequestParam Long userId) {
        List<Leaderboard> response = leaderboardService.getByLevelAndUser(levelId,userId);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register")
    public ResponseEntity<Object> registerScore(@RequestBody LeaderboardResponse leaderboarResponse) {
        leaderboardService.saveScore(leaderboarResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Object());
    }
}
