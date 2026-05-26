package metrica.meka5.controller;

import metrica.meka5.model.Level;
import metrica.meka5.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/levels")
public class LevelsController {

    @Autowired
    private LevelService levelService;

    @GetMapping("/all")
    public ResponseEntity<List<Level>> getAll () {
        List<Level> response = levelService.getLevels();
        return ResponseEntity.ok(response);
    }
}
