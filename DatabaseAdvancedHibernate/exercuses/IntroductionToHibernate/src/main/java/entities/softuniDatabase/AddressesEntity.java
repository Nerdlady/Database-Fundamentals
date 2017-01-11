package entities.softuniDatabase;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "addresses", schema = "soft_uni", catalog = "")
public class AddressesEntity {
    private Integer addressId;
    private String addressText;
    private Integer townId;
    private TownsEntity townsByTownId;
    private Collection<EmployeesEntity> employeesByAddressId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "address_text")
    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }

    @Basic
    @Column(name = "town_id",insertable = false,updatable = false)
    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressesEntity that = (AddressesEntity) o;

        if (addressId != null ? !addressId.equals(that.addressId) : that.addressId != null) return false;
        if (addressText != null ? !addressText.equals(that.addressText) : that.addressText != null) return false;
        if (townId != null ? !townId.equals(that.townId) : that.townId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressId != null ? addressId.hashCode() : 0;
        result = 31 * result + (addressText != null ? addressText.hashCode() : 0);
        result = 31 * result + (townId != null ? townId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "town_id")
    public TownsEntity getTownsByTownId() {
        return townsByTownId;
    }

    public void setTownsByTownId(TownsEntity townsByTownId) {
        this.townsByTownId = townsByTownId;
    }

    @OneToMany(mappedBy = "addressesByAddressId")
    public Collection<EmployeesEntity> getEmployeesByAddressId() {
        return employeesByAddressId;
    }

    public void setEmployeesByAddressId(Collection<EmployeesEntity> employeesByAddressId) {
        this.employeesByAddressId = employeesByAddressId;
    }
}
