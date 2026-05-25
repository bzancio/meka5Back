package metrica.meka5.controller;

import metrica.meka5.security.KeyFactory;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/key")
public class KeyController {
	
	@Autowired
	private KeyFactory keyFactory;
	
	
	@GetMapping("/public")
	public String getPublicKey() {
		return Base64.getEncoder().encodeToString(keyFactory.getPublicKey().getEncoded());
	}

}
