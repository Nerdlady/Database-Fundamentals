package bank.system;

import bank.system.commands.*;
import bank.system.domain.User;
import bank.system.services.AccountService;
import bank.system.services.UserService;

public class CommandExecutor {
    private User user;
    private boolean isLoggedIn;
    private UserService userService;
    private AccountService accountService;

    public CommandExecutor(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    public String run(String command, String[] params) {
        Command commandE = null;
        switch (command) {
            case "Register":
                commandE = new RegisterUser(this.isLoggedIn, this.userService, this.accountService,this.user);
                break;
            case "Login":
                commandE = new Login(this.isLoggedIn, this.userService, this.accountService,this.user);
                break;
            case "Logout":
                commandE = new Logout(this.isLoggedIn, this.userService, this.accountService,this.user);
                break;
            case "Add":
                switch (params[0]) {
                    case "SavingsAccount":
                        commandE = new AddSavingAccount(this.isLoggedIn, this.userService, this.accountService,this.user);
                        break;
                    case "CheckingAccount":
                        commandE = new AddCheckingAccount(this.isLoggedIn, this.userService, this.accountService,this.user);
                        break;
                }
                params = new String[]{params[1],params[2]};
                break;
            case "ListAccounts":
                commandE = new ListAccounts(this.isLoggedIn, this.userService, this.accountService,this.user);
                break;
            case "Deposit":
                commandE = new Deposit(this.isLoggedIn, this.userService, this.accountService,this.user);
                break;
            case "Withdraw":
                commandE = new Withdraw(this.isLoggedIn, this.userService, this.accountService,this.user);
                break;
            case "DeductFee":
                commandE = new DeductFee(this.isLoggedIn, this.userService, this.accountService,this.user);
                break;
            case "AddInterest":
                commandE = new AddInterest(this.isLoggedIn, this.userService, this.accountService,this.user);
                break;
        }
        String toReturn = "";
        if (commandE != null){
            toReturn = commandE.execute(params);
            this.isLoggedIn = commandE.isUserLoggedIn();
            this.user = commandE.getLoggedUser();
        }
        return toReturn;
    }
}
