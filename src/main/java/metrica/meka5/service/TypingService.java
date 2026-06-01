package metrica.meka5.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	
	public List<String> getRandomWordsPackage(int packageSize) {
		List<CommonWord> commonWords = commonWordRepository.findRandomWords(packageSize);
		return commonWords.stream()
				.map(CommonWord::getWord)
				.collect(Collectors.toList());
	}
	
	public String getSentence() {
		return restClient.get()
				.retrieve()
				.body(SentenceKanyeResponse.class)
				.getQuote();

	}

}
