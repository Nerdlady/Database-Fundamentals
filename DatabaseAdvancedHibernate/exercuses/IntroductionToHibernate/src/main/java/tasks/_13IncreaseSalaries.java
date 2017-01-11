package tasks;

import entities.softuniDatabase.EmployeesEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class _13IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<EmployeesEntity> employees = entityManager.createQuery("SELECT e FROM EmployeesEntity AS e " +
                "WHERE e.departmentsByDepartmentId.name = 'Engineering' " +
                "OR e.departmentsByDepartmentId.name = 'Tool Design' " +
                "OR e.departmentsByDepartmentId.name = 'Marketing' " +
                "OR e.departmentsByDepartmentId.name = 'Information Services'").getResultList();

        entityManager.getTransaction().begin();
        for (EmployeesEntity employee : employees) {
            BigDecimal newSalary = employee.getSalary().multiply(new BigDecimal("1.12"));
            employee.setSalary(newSalary);
            System.out.printf("%s %s ($%s)%n",employee.getFirstName(),employee.getLastName(),employee.getSalary());
        }
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
