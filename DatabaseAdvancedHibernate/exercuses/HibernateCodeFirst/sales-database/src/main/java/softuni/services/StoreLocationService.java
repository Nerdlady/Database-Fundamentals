package softuni.services;

import softuni.entities.StoreLocation;

import java.util.List;

public interface StoreLocationService {
    void persist(StoreLocation storeLocation);
    List<StoreLocation> getAll();
}
