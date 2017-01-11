package tasks;

import entities.softuniDatabase.AddressesEntity;
import entities.softuniDatabase.DepartmentsEntity;
import entities.softuniDatabase.EmployeesEntity;
import entities.softuniDatabase.TownsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class _03CreateObjects {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        EmployeesEntity manager = (EmployeesEntity) entityManager.createQuery("SELECT em FROM EmployeesEntity AS em WHERE em.id=1").getSingleResult();

        DepartmentsEntity training = new DepartmentsEntity();
        training.setName("Training");
        training.setEmployeesByManagerId(manager.getEmployeesByManagerId());

        entityManager.getTransaction().begin();

        entityManager.persist(training);
        entityManager.getTransaction().commit();

        TownsEntity burgas = new TownsEntity();
        burgas.setName("Burgas");

        entityManager.getTransaction().begin();
        entityManager.persist(burgas);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();

        AddressesEntity firstAddress = new AddressesEntity();
        firstAddress.setAddressText("Mladost 3");
        firstAddress.setTownsByTownId(burgas);

        AddressesEntity secondAddress = new AddressesEntity();
        secondAddress.setAddressText("Mladost 2");
        secondAddress.setTownsByTownId(burgas);

        AddressesEntity thirdAddress = new AddressesEntity();
        thirdAddress.setAddressText("Mladost 1");
        thirdAddress.setTownsByTownId(burgas);

        entityManager.persist(firstAddress);
        entityManager.persist(secondAddress);
        entityManager.persist(thirdAddress);
        entityManager.getTransaction().commit();


        EmployeesEntity peshoEmployee = new EmployeesEntity();
        peshoEmployee.setFirstName("Pesho");
        peshoEmployee.setMiddleName("Petrov");
        peshoEmployee.setLastName("Petrov");
        peshoEmployee.setJobTitle("Java trainer");
        peshoEmployee.setDepartmentsByDepartmentId(training);
        peshoEmployee.setEmployeesByManagerId(manager);
        peshoEmployee.setHireDate(new Timestamp(45656498L));
        peshoEmployee.setSalary(new BigDecimal("2500"));
        peshoEmployee.setAddressesByAddressId(firstAddress);

        EmployeesEntity goshoEmployee = new EmployeesEntity();
        goshoEmployee.setFirstName("Gosho");
        goshoEmployee.setMiddleName("Georgiev");
        goshoEmployee.setLastName("Georgiev");
        goshoEmployee.setJobTitle("PHP trainer");
        goshoEmployee.setDepartmentsByDepartmentId(training);
        goshoEmployee.setEmployeesByManagerId(manager);
        goshoEmployee.setHireDate(new Timestamp(4564848L));
        goshoEmployee.setSalary(new BigDecimal("2000"));
        goshoEmployee.setAddressesByAddressId(secondAddress);

        EmployeesEntity ivanEmployee = new EmployeesEntity();
        ivanEmployee.setFirstName("Ivan");
        ivanEmployee.setMiddleName("Ivanov");
        ivanEmployee.setLastName("Ivanov");
        ivanEmployee.setJobTitle("JS trainer");
        ivanEmployee.setDepartmentsByDepartmentId(training);
        ivanEmployee.setEmployeesByManagerId(manager);
        ivanEmployee.setHireDate(new Timestamp(4564868L));
        ivanEmployee.setSalary(new BigDecimal("2000"));
        ivanEmployee.setAddressesByAddressId(thirdAddress);

        EmployeesEntity stoqnEmployee = new EmployeesEntity();
        stoqnEmployee.setFirstName("Stoqn");
        stoqnEmployee.setMiddleName("Petrov");
        stoqnEmployee.setLastName("Stoqnov");
        stoqnEmployee.setJobTitle("C# trainer");
        stoqnEmployee.setDepartmentsByDepartmentId(training);
        stoqnEmployee.setEmployeesByManagerId(manager);
        stoqnEmployee.setHireDate(new Timestamp(45489458L));
        stoqnEmployee.setSalary(new BigDecimal("1500"));
        stoqnEmployee.setAddressesByAddressId(thirdAddress);

        EmployeesEntity milkoEmployee = new EmployeesEntity();
        milkoEmployee.setFirstName("Milko");
        milkoEmployee.setMiddleName("Petrov");
        milkoEmployee.setLastName("Petrov");
        milkoEmployee.setJobTitle("C++ trainer");
        milkoEmployee.setDepartmentsByDepartmentId(training);
        milkoEmployee.setEmployeesByManagerId(manager);
        milkoEmployee.setHireDate(new Timestamp(456489498L));
        milkoEmployee.setSalary(new BigDecimal("3000"));
        milkoEmployee.setAddressesByAddressId(firstAddress);

        entityManager.getTransaction().begin();
        entityManager.persist(peshoEmployee);
        entityManager.persist(goshoEmployee);
        entityManager.persist(ivanEmployee);
        entityManager.persist(stoqnEmployee);
        entityManager.persist(milkoEmployee);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

    }
}
