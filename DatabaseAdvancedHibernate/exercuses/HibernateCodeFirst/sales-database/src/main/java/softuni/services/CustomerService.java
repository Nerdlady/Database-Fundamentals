package softuni.services;

import softuni.entities.Customer;

import java.util.List;

public interface CustomerService {
    void persist(Customer customer);

    List<Customer> getAll();
}
