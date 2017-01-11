package softuni.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "customers")
public class Customer {
    private	Long id;
    private	String name;
    private	String email;
    private	String creditCardNumber;
    private Set<Sale> salesForCustomer;

    public Customer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @OneToMany(mappedBy = "id",fetch = FetchType.EAGER)
    public Set<Sale> getSalesForCustomer() {
        return salesForCustomer;
    }

    public void setSalesForCustomer(Set<Sale> salesForCustomer) {
        this.salesForCustomer = salesForCustomer;
    }
}
