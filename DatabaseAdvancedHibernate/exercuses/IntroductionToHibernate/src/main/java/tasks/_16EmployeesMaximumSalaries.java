package tasks;

import entities.softuniDatabase.DepartmentsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class _16EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<DepartmentsEntity> departments = entityManager
                .createQuery("SELECT d FROM DepartmentsEntity AS d")
                .getResultList();

        for (DepartmentsEntity department : departments) {
            BigDecimal maxSalary = department.getEmployeesByDepartmentId()
                    .stream().max((a, b) -> a.getSalary().compareTo(b.getSalary())).get().getSalary();

            if (maxSalary.compareTo(new BigDecimal("30000")) == -1 || maxSalary.compareTo(new BigDecimal("70000")) == 1) {
                System.out.printf("%s - %s%n", department.getName(), maxSalary);
            }

        }

        entityManager.close();
        entityManagerFactory.close();

    }
}
