package bank.system.domain;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value =  "Checking")
public class CheckingAccount extends BasicAccount {
    private BigDecimal fee;

    public CheckingAccount() {
    }

    @Basic
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
