package metrica.meka5.service;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import metrica.meka5.dto.Response;

@Service
public class WordsServiceImpl implements WordsService{

	private final RestClient restClient;
	
	public WordsServiceImpl() {
		this.restClient = RestClient.create("https://rae-api.com");
	}

	public String get100Words() {
		return IntStream.range(0, 100)
				.parallel()
				.mapToObj(o -> getWordApi())
				.collect(Collectors.joining(" "));
	}
	
	private String getWordApi() {
		Response response = restClient.get()
				.uri("/api/random")
				.retrieve()
				.body(Response.class);
		
		if(response.isOk()) {
			return response.getData().getWord();
		}
		return "";
	}

}
