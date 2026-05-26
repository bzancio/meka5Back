package metrica.meka5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
	
	@Bean
	public WebMvcConfigurer CorsConfigurer() {
		return new WebMvcConfigurer() {
			@SuppressWarnings("unused")
			public void addCorsMapping(CorsRegistry register) {
				register.addMapping("/**")
					.allowedOrigins("https://localhost:4200")
					.allowedMethods("GET", "POST", "OPTIONS")
					.allowedHeaders("*")
					.allowCredentials(true);
			}
		};
	}
	

}
