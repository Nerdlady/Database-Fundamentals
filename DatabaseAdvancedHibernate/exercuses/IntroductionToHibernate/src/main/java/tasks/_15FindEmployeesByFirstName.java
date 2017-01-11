package tasks;

import entities.softuniDatabase.EmployeesEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class _15FindEmployeesByFirstName {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pattern = reader.readLine();

        List<EmployeesEntity> employees = entityManager.createQuery("SELECT e FROM EmployeesEntity AS e").getResultList();

        for (EmployeesEntity employee : employees) {
            if (employee.getFirstName().toLowerCase().startsWith(pattern.toLowerCase())){
                System.out.printf("%s %s - %s - ($%s)%n",
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getJobTitle(),
                        employee.getSalary());
            }
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
