package softuni.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Employee;
import softuni.repositories.EmployeeDao;
import softuni.services.interfaces.EmployeeService;

@Service
public class EmployeeServiceImpl  implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void persist(Employee employee) {
        this.employeeDao.saveAndFlush(employee);
    }
}
