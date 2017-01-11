package bank.system.domain.operations;

import java.math.BigDecimal;

public interface Operation {
    BigDecimal withdrawMoney(BigDecimal moneyToWithdraw);

    BigDecimal depositMoney(BigDecimal deposit);
}
