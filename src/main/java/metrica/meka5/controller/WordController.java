package metrica.meka5.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
	public ResponseEntity<List<String>> getCommonWords(
			@RequestParam(name = "size", defaultValue = "15") int size,
			@RequestParam(name = "includePunctuation", defaultValue = "true") boolean includePunctuation,
			@RequestParam(name = "maintainCase", defaultValue = "true") boolean maintainCase,
			@RequestParam(name = "removeDiacritics", defaultValue = "false") boolean removeDiacritics) {
		List<String> wordsPackage = typingService.getRandomWordsPackage(size, includePunctuation, maintainCase, removeDiacritics);
		return ResponseEntity.ok(wordsPackage);
	}

	@GetMapping("/sentence")
	public ResponseEntity<List<String>> getSentence(
			@RequestParam(name = "includePunctuation", defaultValue = "true") boolean includePunctuation,
			@RequestParam(name = "maintainCase", defaultValue = "true") boolean maintainCase,
			@RequestParam(name = "removeDiacritics", defaultValue = "false") boolean removeDiacritics) {
		String sentence = typingService.getSentence(includePunctuation, maintainCase, removeDiacritics);
		List<String> words = Arrays.asList(sentence.split(" "));
		return ResponseEntity.status(HttpStatus.OK).body(words);
	}
}
