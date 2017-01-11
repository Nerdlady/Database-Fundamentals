package bank.system.generator;

import java.util.Random;

public class AccountNumberGenerator {
    private static final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String next(){
        Random random = new Random();
        String accountNumber = "";
        for (int i = 0; i < 10; i++) {
            accountNumber += SALTCHARS.charAt(random.nextInt(SALTCHARS.length()));
        }
        return accountNumber;
    }
}
