package tasks;

import entities.softuniDatabase.EmployeesEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class _07EmployeeQueries {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Part One
        Query query = entityManager.createQuery("SELECT e.firstName FROM EmployeesEntity AS e WHERE e.salary > 50000");

        List<String> employeesName = query.getResultList();

        for (String name : employeesName) {
            System.out.println(name);
        }

        // Part Two
        Query queryTwo = entityManager.createQuery("SELECT e FROM EmployeesEntity AS e " +
                "JOIN DepartmentsEntity AS de ON e.departmentId = de.departmentId " +
                "WHERE de.name = 'Research and Development'");

        List<EmployeesEntity> employees = queryTwo.getResultList();

        employees.stream().sorted((a,b) -> {
            int result = a.getSalary().compareTo(b.getSalary());
            if (result == 0){
                result = b.getFirstName().compareTo(a.getFirstName());
            }
            return result;
        }).forEach(a -> System.out.printf("%s %s from %s  - $%.2f%n",
                a.getFirstName(),a.getLastName(),a.getDepartmentsByDepartmentId().getName(),a.getSalary()));
        entityManager.close();
        entityManagerFactory.close();
    }

}
