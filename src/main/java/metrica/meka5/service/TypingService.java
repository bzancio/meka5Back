package metrica.meka5.service;

import java.text.Normalizer;
import java.util.List;
import java.util.Objects;
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

    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("\\p{Punct}");
    private static final Pattern DIACRITIC_PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

	public List<String> getRandomWordsPackage(int packageSize, boolean includePunctuation, boolean maintainCase, boolean removeDiacritics) {
		List<CommonWord> commonWords = commonWordRepository.findRandomWords(packageSize);
		return commonWords.stream()
				.map(CommonWord::getWord)
				.map(word -> transformWord(word, includePunctuation, maintainCase, removeDiacritics))
				.collect(Collectors.toList());
	}
	
	public String getSentence(boolean includePunctuation, boolean maintainCase, boolean removeDiacritics) {
		String quote = Objects.requireNonNull(restClient.get()
                        .retrieve()
                        .body(SentenceKanyeResponse.class))
				.getQuote();
		return transformWord(quote, includePunctuation, maintainCase, removeDiacritics);
	}

    private String transformWord(String text, boolean includePunctuation, boolean maintainCase, boolean removeDiacritics) {
        if (text == null) {
            return "";
        }
        String transformedText = text;

        if (removeDiacritics) {
            transformedText = removeDiacritics(transformedText);
        }

        if (!includePunctuation) {
            transformedText = PUNCTUATION_PATTERN.matcher(transformedText).replaceAll("");
        }

        if (!maintainCase) {
            transformedText = transformedText.toLowerCase();
        }
        return transformedText;
    }

    private String removeDiacritics(String text) {
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return DIACRITIC_PATTERN.matcher(normalized).replaceAll("");
    }
}
