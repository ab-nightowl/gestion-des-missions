package dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Application démarrée via Spring Boot.
 */
@SpringBootApplication
@CrossOrigin
@EnableScheduling
public class App {

	/**
	 * Démarrage de l'application Web.
	 *
	 * @param args
	 *            argument du programme
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "DELETE", "PUT")
						.allowedHeaders("Content-Type");
			}
		};
	}
}
