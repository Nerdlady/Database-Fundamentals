package bills.system.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "type",discriminatorType = DiscriminatorType.STRING)
public abstract class BasicBillingDetail implements BillingDetail {

    private User owner;
    private String number;

    public BasicBillingDetail() {
    }

    @ManyToOne
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    @Override
    public User getOwner() {
        return owner;
    }

    @Override
    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Id
    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }
}
