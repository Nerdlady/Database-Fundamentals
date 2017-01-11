package bills.system.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
//@DiscriminatorValue(value = "Credit Card")
@Table(name = "credit_cards")
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends BasicBillingDetail {

    private String cardType;
    private Integer expirationMonth;
    private Integer expirationYear;

    public CreditCard() {
    }

    @Basic
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Basic
    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    @Basic
    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }
}
