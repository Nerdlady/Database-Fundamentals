package bank.system.commands;

import bank.system.domain.User;
import bank.system.services.AccountService;
import bank.system.services.UserService;

public class Login extends Command {


    public Login(boolean isUserLoggedIn, UserService userService, AccountService accountService, User loggedUser) {
        super(isUserLoggedIn, userService, accountService, loggedUser);
    }

    @Override
    public String execute(String... params) {
        String username = params[0];
        String password = params[1];
        User user = super.getUserService().getByUsername(username);

        if (super.isUserLoggedIn()){
            return "User already login";
        }

        if (user == null || !user.getPassword().equals(password)){
            return "Incorrect username / password";
        }

        super.setUserLoggedIn(true);
        super.setLoggedUser(user);
        return String.format("Succesfully logged in %s",username);
    }
}
