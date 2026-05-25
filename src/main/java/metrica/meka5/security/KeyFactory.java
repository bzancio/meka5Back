package metrica.meka5.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.springframework.stereotype.Component;

@Component
public class KeyFactory {

	private static KeyFactory keyFactory;

	private PublicKey publicKey;
	private PrivateKey privateKey;

	private KeyFactory() throws NoSuchAlgorithmException {

		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);

		KeyPair keys = generator.generateKeyPair();

		publicKey = keys.getPublic();
		privateKey = keys.getPrivate();

	}

	public static KeyFactory getInstance() throws NoSuchAlgorithmException {
		
		if (keyFactory == null) {
			keyFactory = new KeyFactory();
		}
		
		return keyFactory;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	
}
