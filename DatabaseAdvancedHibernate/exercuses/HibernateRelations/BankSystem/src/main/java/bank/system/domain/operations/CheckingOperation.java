package bank.system.domain.operations;

import bank.system.domain.Account;
import bank.system.domain.CheckingAccount;

import java.math.BigDecimal;

public class CheckingOperation extends BasicOperation {

    public CheckingOperation(Account account) {
        super(account);
    }

   public BigDecimal deductFee(){
        CheckingAccount account = (CheckingAccount) super.account;
        BigDecimal fee = account.getFee();
        BigDecimal newBalace = account.getBalance().subtract(fee);
        account.setBalance(newBalace);
        return newBalace;
    }
}
