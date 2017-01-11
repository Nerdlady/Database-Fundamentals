package bank.system.commands;

import bank.system.domain.BasicAccount;
import bank.system.domain.User;
import bank.system.domain.operations.BasicOperation;
import bank.system.domain.operations.Operation;
import bank.system.services.AccountService;
import bank.system.services.UserService;

import java.math.BigDecimal;

public class Deposit extends Command {


    public Deposit(boolean isUserLoggedIn, UserService userService, AccountService accountService, User loggedUser) {
        super(isUserLoggedIn, userService, accountService, loggedUser);
    }

    @Override
    public String execute(String... params) {
        if (!super.isUserLoggedIn()) {
            return "No login user";
        }

        BasicAccount account = this.getAccountService().getById(params[0]);
        Operation operation = new BasicOperation(account);
        BigDecimal balance = operation.depositMoney(new BigDecimal(params[1]));
        this.getAccountService().persist(account);
        return String.format("Account %s has balance of %s",account.getAccountNumber(),balance);
    }
}
