package softuni.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.entities.WizardDeposit;
import softuni.services.WizardDepositService;

import java.util.Calendar;

@Component
public class Console implements CommandLineRunner {

    private final WizardDepositService wizardDepositService;

    @Autowired
    public Console(WizardDepositService wizardDepositService) {
        this.wizardDepositService = wizardDepositService;
    }

    @Override
    public void run(String... strings) throws Exception {
        WizardDeposit dumbledore = new WizardDeposit();
        dumbledore.setFisrtName("Albuls");
        dumbledore.setLastName("Dumbledore");
        dumbledore.setAge(150);
        dumbledore.setMagicWandCreator("Antoich Paverell");
        dumbledore.setMagicWandSize(15);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,10,20);
        dumbledore.setDepositStartDate(calendar.getTime());
        calendar.set(2020,10,20);
        dumbledore.setDepositExpirationDate(calendar.getTime());
        dumbledore.setDepositAmount(2000.24);
        dumbledore.setDepositCharge(0.12);
        dumbledore.setDepositExpired(false);

        this.wizardDepositService.persist(dumbledore);
    }
}
