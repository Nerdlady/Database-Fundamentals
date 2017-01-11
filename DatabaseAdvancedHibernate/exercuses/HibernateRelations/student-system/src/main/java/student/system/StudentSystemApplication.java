package student.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repository.builder.lib.RepositoryBuilder;

@SpringBootApplication
public class StudentSystemApplication {

	public static void main(String[] args) {
		RepositoryBuilder.build();
		SpringApplication.run(StudentSystemApplication.class, args);
	}
}
