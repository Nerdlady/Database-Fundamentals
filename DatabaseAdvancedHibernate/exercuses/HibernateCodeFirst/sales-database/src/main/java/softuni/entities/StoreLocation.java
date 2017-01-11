package softuni.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "store_locations")
public class StoreLocation {
    private int id;
    private String locationName;
    private Set<Sale> salesInStore;

    public StoreLocation() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @OneToMany(mappedBy = "id",fetch = FetchType.EAGER)
    public Set<Sale> getSalesInStore() {
        return salesInStore;
    }

    public void setSalesInStore(Set<Sale> salesInStore) {
        this.salesInStore = salesInStore;
    }
}
