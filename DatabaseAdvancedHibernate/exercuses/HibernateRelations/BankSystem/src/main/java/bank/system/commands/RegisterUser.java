package bank.system.commands;

import bank.system.domain.User;
import bank.system.services.AccountService;
import bank.system.services.UserService;

import javax.validation.ValidationException;

public class RegisterUser  extends Command {


    public RegisterUser(boolean isUserLoggedIn, UserService userService, AccountService accountService, User loggedUser) {
        super(isUserLoggedIn, userService, accountService, loggedUser);
    }

    @Override
    public String execute(String... params) {
        String username = params[0];
        String password = params[1];
        String email = params[2];
        User user = new User();
        try {
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            super.getUserService().persist(user);
        } catch (IllegalArgumentException e){
            return e.getLocalizedMessage();
           // e.printStackTrace();
        }
        return String.format("%s was registered in the system",username);
    }
}
