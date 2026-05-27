package metrica.meka5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import metrica.meka5.service.TypingService;

@RestController
@RequestMapping("/api/words")
public class WordController {
	
	@Autowired
	private TypingService typingService;
	
	@GetMapping("/common")
	public ResponseEntity<List<String>> getCommonWords(@RequestParam(name = "size", defaultValue = "15") int size) {
		List<String> wordsPackage = typingService.getRandomWordsPackage(size);
		return ResponseEntity.ok(wordsPackage);
	}
	
    @GetMapping("/sentence")
    public ResponseEntity<String> get100 () {
        String words = typingService.getSentence();
        return ResponseEntity.status(HttpStatus.CREATED).body(words);
    }
}
