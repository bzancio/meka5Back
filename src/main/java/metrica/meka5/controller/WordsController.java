package metrica.meka5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import metrica.meka5.service.WordsService;

@Controller
@RequestMapping("/api/words")
public class WordsController {
	
	@Autowired
	private WordsService wordsService;
	
    @GetMapping("/all")
    public ResponseEntity<String> get100 () {
        String words = wordsService.get100Words();
        return ResponseEntity.status(HttpStatus.CREATED).body(words);
    }
	

}
