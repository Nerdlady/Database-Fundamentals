package bank.system.commands;

import bank.system.domain.CheckingAccount;
import bank.system.domain.User;
import bank.system.domain.operations.CheckingOperation;
import bank.system.services.AccountService;
import bank.system.services.UserService;

import java.math.BigDecimal;

public class DeductFee extends Command {

    public DeductFee(boolean isUserLoggedIn, UserService userService, AccountService accountService, User loggedUser) {
        super(isUserLoggedIn, userService, accountService, loggedUser);
    }

    @Override
    public String execute(String... params) {
        if (!super.isUserLoggedIn()) {
            return "No login user";
        }

        CheckingAccount account = (CheckingAccount) this.getAccountService().getById(params[0]);
        CheckingOperation operation = new CheckingOperation(account);
        BigDecimal balance = operation.deductFee();
        super.getAccountService().persist(account);
        return String.format("Account %s has balance of %s",account.getAccountNumber(),balance);
    }
}
