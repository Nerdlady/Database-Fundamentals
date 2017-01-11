package bills.system;

import bills.system.domain.BankAccount;
import bills.system.domain.CreditCard;
import bills.system.domain.User;
import bills.system.services.BasicBillingDetailService;
import bills.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Console implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Autowired
    private BasicBillingDetailService basicBillingDetailService;

    @Override
    public void run(String... strings) throws Exception {
        User user = new User();
        user.setFirstName("Gosho");
        user.setEmail("gosho@gosho.com");
        user.setLastName("Goshev");
        user.setPassword("goshotooOOoo");

        CreditCard billingDetail = new CreditCard();
        billingDetail.setNumber("876953187935");
        billingDetail.setOwner(user);
        billingDetail.setCardType("Cool");
        billingDetail.setExpirationMonth(5);
        billingDetail.setExpirationYear(2017);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setOwner(user);
        bankAccount.setNumber("000132584684");
        bankAccount.setBankName("OBB");
        bankAccount.setSwiftCode("BIO");

        this.userService.persist(user);
        this.basicBillingDetailService.persist(billingDetail);
        this.basicBillingDetailService.persist(bankAccount);
    }
}
