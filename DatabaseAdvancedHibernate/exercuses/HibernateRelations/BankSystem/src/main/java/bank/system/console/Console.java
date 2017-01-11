package bank.system.console;

import bank.system.CommandExecutor;
import bank.system.services.AccountService;
import bank.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
@Transactional
public class Console implements CommandLineRunner {


    private final UserService userService;
    private final AccountService accountService;

    private CommandExecutor commandExecutor;

    @Autowired
    public Console(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }


    @Override
    public void run(String... strings) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        this.commandExecutor = new CommandExecutor(this.userService,this.accountService);
        while (true){
            String line = reader.readLine();

            if (line.equals("End")){
                break;
            }

            String[] params = line.split("\\s+");
            String command = params[0];
            String[] finalParams = new String[params.length - 1];
            for (int i = 1; i < params.length; i++) {
                finalParams[i-1] = params[i];
            }
            String output = this.commandExecutor.run(command,finalParams);
            System.out.println(output);
        }


    }
}
