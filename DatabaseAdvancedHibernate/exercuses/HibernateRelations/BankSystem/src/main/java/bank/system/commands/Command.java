package bank.system.commands;

import bank.system.domain.User;
import bank.system.services.AccountService;
import bank.system.services.UserService;

public abstract class Command implements Executable {
    private boolean isUserLoggedIn;
    private UserService userService;
    private AccountService accountService;
    private User loggedUser;

    public Command(boolean isUserLoggedIn, UserService userService, AccountService accountService,User loggedUser) {
        this.isUserLoggedIn = isUserLoggedIn;
        this.userService = userService;
        this.accountService = accountService;
        this.setLoggedUser(loggedUser);
    }

    public boolean isUserLoggedIn() {
        return isUserLoggedIn;
    }

    protected void setUserLoggedIn(boolean userLoggedIn) {
        isUserLoggedIn = userLoggedIn;
    }

    protected UserService getUserService() {
        return userService;
    }

    protected void setUserService(UserService userService) {
        this.userService = userService;
    }

    protected AccountService getAccountService() {
        return accountService;
    }

    protected void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
