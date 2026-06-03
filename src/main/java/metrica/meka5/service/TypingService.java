package metrica.meka5.service;

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

	public List<String> getRandomWordsPackage(int packageSize, boolean includePunctuation, boolean maintainCase) {
		List<CommonWord> commonWords = commonWordRepository.findRandomWords(packageSize);
		return commonWords.stream()
				.map(CommonWord::getWord)
				.map(word -> transformWord(word, includePunctuation, maintainCase))
				.collect(Collectors.toList());
	}
	
	public String getSentence(boolean includePunctuation, boolean maintainCase) {
		String quote = Objects.requireNonNull(restClient.get()
                        .retrieve()
                        .body(SentenceKanyeResponse.class))
				.getQuote();
		return transformWord(quote, includePunctuation, maintainCase);
	}

    private String transformWord(String text, boolean includePunctuation, boolean maintainCase) {
        if (text == null) {
            return "";
        }
        String transformedText = text;

        if (!includePunctuation) {
            transformedText = PUNCTUATION_PATTERN.matcher(transformedText).replaceAll("");
        }

        if (!maintainCase) {
            transformedText = transformedText.toLowerCase();
        }
        return transformedText;
    }
}
