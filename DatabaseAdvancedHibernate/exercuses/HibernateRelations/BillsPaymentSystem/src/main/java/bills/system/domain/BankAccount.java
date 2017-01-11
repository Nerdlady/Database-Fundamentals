package bills.system.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
//@DiscriminatorValue(value = "Bank Account")
@Table(name = "bank_accounts")
@PrimaryKeyJoinColumn(name = "id")
public class BankAccount extends BasicBillingDetail {
    private String bankName;
    private String swiftCode;

    public BankAccount() {
    }

    @Basic
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Basic
    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
