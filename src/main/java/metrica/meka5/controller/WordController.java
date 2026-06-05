package metrica.meka5.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import metrica.meka5.service.TypingService;

@RestController
@RequestMapping("/api/words")
public class WordController {

    private final TypingService typingService;

    public WordController(TypingService typingService) {
        this.typingService = typingService;
    }

    @GetMapping("/common")
    public ResponseEntity<List<String>> getCommonWords(
            @RequestParam(name = "size", defaultValue = "15") int size,
            @RequestParam(name = "maintainCase", defaultValue = "true") boolean maintainCase,
            @RequestParam(name = "cleanPunctuationAndDiacritics", defaultValue = "false") boolean cleanPunctuationAndDiacritics) {

        List<String> wordsPackage = typingService.getRandomWordsPackage(size, maintainCase, cleanPunctuationAndDiacritics);
        return ResponseEntity.ok(wordsPackage);
    }

    @GetMapping("/sentence")
    public ResponseEntity<List<String>> getSentence(
            @RequestParam(name = "maintainCase", defaultValue = "true") boolean maintainCase,
            @RequestParam(name = "cleanPunctuationAndDiacritics", defaultValue = "false") boolean cleanPunctuationAndDiacritics) {

        String sentence = typingService.getSentence(maintainCase, cleanPunctuationAndDiacritics);

        List<String> words = Arrays.asList(sentence.split("\\s+"));

        return ResponseEntity.ok(words);
    }
}