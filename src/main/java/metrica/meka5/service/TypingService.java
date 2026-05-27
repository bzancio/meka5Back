package metrica.meka5.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metrica.meka5.model.CommonWord;
import metrica.meka5.repository.CommonWordRepository;

@Service
public class TypingService {
	@Autowired
	private CommonWordRepository commonWordRepository;
	
	public List<String> getRandomWordsPackage(int packageSize) {
		List<CommonWord> commonWords = commonWordRepository.findRandomWords(packageSize);
		return commonWords.stream()
				.map(CommonWord::getWord)
				.collect(Collectors.toList());
	}
}
