package bank.system.domain;

import java.math.BigDecimal;

public interface Account {
    void setAccountNumber(String number);

    String getAccountNumber();

    void setBalance(BigDecimal balance);

    BigDecimal getBalance();
}
