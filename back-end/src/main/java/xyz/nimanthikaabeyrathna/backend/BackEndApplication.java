package xyz.nimanthikaabeyrathna.backend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@SpringBootApplication
public class BackEndApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public MessageDigest sha256MessageDigest() throws NoSuchAlgorithmException {
		return MessageDigest.getInstance("SHA-256");
	}

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}

}
