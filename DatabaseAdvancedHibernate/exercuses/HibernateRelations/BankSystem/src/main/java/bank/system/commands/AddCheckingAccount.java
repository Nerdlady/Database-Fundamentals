package bank.system.commands;

import bank.system.domain.CheckingAccount;
import bank.system.domain.User;
import bank.system.generator.AccountNumberGenerator;
import bank.system.services.AccountService;
import bank.system.services.UserService;

import java.math.BigDecimal;

public class AddCheckingAccount extends Command {


    public AddCheckingAccount(boolean isUserLoggedIn, UserService userService, AccountService accountService, User loggedUser) {
        super(isUserLoggedIn, userService, accountService, loggedUser);
    }

    @Override
    public String execute(String... params) {
        if (!super.isUserLoggedIn()) {
            return "No login user";
        }

        User user = super.getLoggedUser();
        String accountNumber = AccountNumberGenerator.next();

        CheckingAccount account = new CheckingAccount();
        account.setAccountNumber(accountNumber);
        account.setUser(user);
        account.setBalance(new BigDecimal(params[0]));
        account.setFee(new BigDecimal(params[1]));

        super.getAccountService().persist(account);

        return String.format("Succesfully added account with number %s", accountNumber);
    }
}
