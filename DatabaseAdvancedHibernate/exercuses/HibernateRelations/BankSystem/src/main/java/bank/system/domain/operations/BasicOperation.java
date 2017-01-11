package bank.system.domain.operations;

import bank.system.domain.Account;

import java.math.BigDecimal;

public class BasicOperation implements Operation {
    protected Account account;

    public BasicOperation(Account account) {
        this.account = account;
    }

    @Override
    public BigDecimal withdrawMoney(BigDecimal moneyToWithdraw) {
        BigDecimal balance = this.account.getBalance();
        if (balance.compareTo(moneyToWithdraw) == -1) {
            return balance;
        }

        balance = balance.subtract(moneyToWithdraw);

        this.account.setBalance(balance);
        return balance;
    }

    @Override
    public BigDecimal depositMoney(BigDecimal deposit) {
        BigDecimal newBalance = deposit.add(this.account.getBalance());
        this.account.setBalance(newBalance);
        return newBalance;
    }
}
