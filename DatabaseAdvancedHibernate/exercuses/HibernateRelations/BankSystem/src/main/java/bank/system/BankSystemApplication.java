package bank.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repository.builder.lib.RepositoryBuilder;

@SpringBootApplication
public class BankSystemApplication {

	public static void main(String[] args) {

		RepositoryBuilder.build();
		SpringApplication.run(BankSystemApplication.class, args);
	}
}
