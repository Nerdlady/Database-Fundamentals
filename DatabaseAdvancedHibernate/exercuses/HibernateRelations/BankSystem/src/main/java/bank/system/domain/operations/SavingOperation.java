package bank.system.domain.operations;

import bank.system.domain.Account;
import bank.system.domain.SavingAccount;

import java.math.BigDecimal;

public class SavingOperation extends BasicOperation {

    public SavingOperation(Account account) {
        super(account);
    }

    public BigDecimal addInterest() {
        SavingAccount account = (SavingAccount) super.account;
        BigDecimal interest = account.getInterestRate();
        BigDecimal toAdd = account.getBalance().multiply(interest);
        BigDecimal newBalance = account.getBalance().add(toAdd);
        account.setBalance(newBalance);
        return newBalance;
    }
}
