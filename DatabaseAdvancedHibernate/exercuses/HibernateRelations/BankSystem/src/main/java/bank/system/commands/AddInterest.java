package bank.system.commands;

import bank.system.domain.SavingAccount;
import bank.system.domain.User;
import bank.system.domain.operations.SavingOperation;
import bank.system.services.AccountService;
import bank.system.services.UserService;

import java.math.BigDecimal;

public class AddInterest extends Command {


    public AddInterest(boolean isUserLoggedIn, UserService userService, AccountService accountService, User loggedUser) {
        super(isUserLoggedIn, userService, accountService, loggedUser);
    }

    @Override
    public String execute(String... params) {
        if (!super.isUserLoggedIn()) {
            return "No login user";
        }

        SavingAccount account = (SavingAccount) this.getAccountService().getById(params[0]);
        SavingOperation operation = new SavingOperation(account);
        BigDecimal balance = operation.addInterest();
        super.getAccountService().persist(account);
        return String.format("Account %s has balance of %s",account.getAccountNumber(),balance);
    }
}
