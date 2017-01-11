package entities.softuniDatabase;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "towns", schema = "soft_uni", catalog = "")
public class TownsEntity {
    private Integer townId;
    private String name;
    private Collection<AddressesEntity> addressesByTownId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_id")
    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TownsEntity that = (TownsEntity) o;

        if (townId != null ? !townId.equals(that.townId) : that.townId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = townId != null ? townId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "townsByTownId")
    public Collection<AddressesEntity> getAddressesByTownId() {
        return addressesByTownId;
    }

    public void setAddressesByTownId(Collection<AddressesEntity> addressesByTownId) {
        this.addressesByTownId = addressesByTownId;
    }
}
