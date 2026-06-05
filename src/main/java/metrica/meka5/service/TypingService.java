package metrica.meka5.service;

import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import metrica.meka5.dto.SentenceKanyeResponse;
import metrica.meka5.model.CommonWord;
import metrica.meka5.repository.CommonWordRepository;

@Service
public class TypingService {

    private final RestClient restClient = RestClient.create("https://api.kanye.rest/");

    @Autowired
    private CommonWordRepository commonWordRepository;

    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("[\\p{P}]");

    private static final Pattern DIACRITICS_PATTERN = Pattern.compile("\\p{M}");

    public List<String> getRandomWordsPackage(int packageSize, boolean maintainCase, boolean cleanPunctuationAndDiacritics) {
        List<CommonWord> commonWords = commonWordRepository.findRandomWords(packageSize);
        return commonWords.stream()
                .map(CommonWord::getWord)
                .map(word -> transformWord(word, maintainCase, cleanPunctuationAndDiacritics))
                .collect(Collectors.toList());
    }

    public String getSentence(boolean maintainCase, boolean cleanPunctuationAndDiacritics) {
        String quote = restClient.get()
                .retrieve()
                .body(SentenceKanyeResponse.class)
                .getQuote();
        return transformWord(quote, maintainCase, cleanPunctuationAndDiacritics);
    }

    private String transformWord(String text, boolean maintainCase, boolean cleanPunctuationAndDiacritics) {
        if (text == null || text.isBlank()) {
            return "";
        }

        String transformedText = text;

        if (cleanPunctuationAndDiacritics) {
            transformedText = Normalizer.normalize(transformedText, Normalizer.Form.NFD);

            transformedText = DIACRITICS_PATTERN.matcher(transformedText).replaceAll("");
            transformedText = PUNCTUATION_PATTERN.matcher(transformedText).replaceAll("");
        }

        if (!maintainCase) {
            transformedText = transformedText.toLowerCase();
        }

        return Normalizer.normalize(transformedText, Normalizer.Form.NFC);
    }
}