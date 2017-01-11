package bank.system.commands;

import bank.system.domain.User;
import bank.system.services.AccountService;
import bank.system.services.UserService;

public class Logout extends Command {


    public Logout(boolean isUserLoggedIn, UserService userService, AccountService accountService, User loggedUser) {
        super(isUserLoggedIn, userService, accountService, loggedUser);
    }

    @Override
    public String execute(String... params) {
        if (!super.isUserLoggedIn()){
            return "Cannot log out. No user was logged in.";
        }
        String username = super.getLoggedUser().getUsername();
        super.setUserLoggedIn(false);
        super.setLoggedUser(null);
        return String.format("User %s successfully logged out",username);
    }
}
