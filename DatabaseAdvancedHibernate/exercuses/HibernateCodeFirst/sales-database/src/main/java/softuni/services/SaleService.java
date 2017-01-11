package softuni.services;

import softuni.entities.Sale;

import java.util.List;

public interface SaleService {
    void persist(Sale sale);
    List<Sale> getAll();
}
