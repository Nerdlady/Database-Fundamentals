package bills.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repository.builder.lib.RepositoryBuilder;

@SpringBootApplication
public class BillsPaymentSystemApplication {

	public static void main(String[] args) {

		RepositoryBuilder.build();
		SpringApplication.run(BillsPaymentSystemApplication.class, args);
	}
}
