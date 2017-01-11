package bills.system.domain;

public interface BillingDetail {
    String getNumber();

    void setNumber(String number);

    User getOwner();

    void setOwner(User user);
}
