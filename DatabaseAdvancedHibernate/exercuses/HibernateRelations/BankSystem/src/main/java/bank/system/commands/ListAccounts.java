package bank.system.commands;

import bank.system.domain.BasicAccount;
import bank.system.domain.CheckingAccount;
import bank.system.domain.SavingAccount;
import bank.system.domain.User;
import bank.system.services.AccountService;
import bank.system.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class ListAccounts extends Command {


    public ListAccounts(boolean isUserLoggedIn, UserService userService, AccountService accountService, User loggedUser) {
        super(isUserLoggedIn, userService, accountService, loggedUser);
    }

    @Override
    public String execute(String... params) {
        if (!super.isUserLoggedIn()){
            return "No login user";
        }

        User user = super.getLoggedUser();
        List<BasicAccount> basicAccounts = super.getAccountService().getAllWithUser(user);

        List<SavingAccount>  savingAccounts = new ArrayList<>();
        List<CheckingAccount> checkingAccounts = new ArrayList<>();

        for (BasicAccount basicAccount : basicAccounts) {
            if (basicAccount instanceof SavingAccount){
                savingAccounts.add((SavingAccount)basicAccount);
            } else if (basicAccount instanceof CheckingAccount){
                checkingAccounts.add((CheckingAccount)basicAccount);
            }
        }
        StringBuilder builder = new StringBuilder();

        builder.append("Saving Accounts:").append(System.lineSeparator());
        for (SavingAccount savingAccount : savingAccounts) {
            builder.append(String.format("--%s %s",savingAccount.getAccountNumber(),savingAccount.getBalance())).append(System.lineSeparator());
        }

        builder.append("Checking Accounts:").append(System.lineSeparator());
        for (CheckingAccount checkingAccount : checkingAccounts) {
            builder.append(String.format("--%s %s",checkingAccount.getAccountNumber(),checkingAccount.getBalance())).append(System.lineSeparator());
        }
        return builder.toString();
    }
}
