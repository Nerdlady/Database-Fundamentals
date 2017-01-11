package softuni.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Customer;
import softuni.repositories.CustomerDao;
import softuni.services.interfaces.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void persist(Customer customer) {
        this.customerDao.saveAndFlush(customer);
    }
}
