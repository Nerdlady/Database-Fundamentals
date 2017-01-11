package softuni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repository.builder.lib.RepositoryBuilder;

@SpringBootApplication
public class CreateUserApplication {

	public static void main(String[] args) {
		RepositoryBuilder.build("Dao");
		SpringApplication.run(CreateUserApplication.class, args);
	}
}
