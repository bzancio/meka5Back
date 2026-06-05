package metrica.meka5.service;

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

    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("[\\p{Punct}]");

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
        if (text == null) {
            return "";
        }
        String transformedText = text;

        if (cleanPunctuationAndDiacritics) {
            transformedText = removeDiacritics(transformedText);
            transformedText = PUNCTUATION_PATTERN.matcher(transformedText).replaceAll("");
        }

        if (!maintainCase) {
            transformedText = transformedText.toLowerCase();
        }
        return transformedText;
    }

    private String removeDiacritics(String text) {
        if (text == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(text.length());
        for (char c : text.toCharArray()) {
            switch (c) {
                case 'á': case 'Á': sb.append('a'); break;
                case 'é': case 'É': sb.append('e'); break;
                case 'í': case 'Í': sb.append('i'); break;
                case 'ó': case 'Ó': sb.append('o'); break;
                case 'ú': case 'Ú': sb.append('u'); break;
                case 'ü': case 'Ü': sb.append('u'); break;
                case 'ñ': case 'Ñ': sb.append('n'); break;
                case 'ç': case 'Ç': sb.append('c'); break;
                default: sb.append(c); break;
            }
        }
        return sb.toString();
    }
}
