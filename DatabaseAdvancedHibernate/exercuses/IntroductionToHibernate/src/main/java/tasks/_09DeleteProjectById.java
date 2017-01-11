package tasks;

import entities.softuniDatabase.EmployeesEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _09DeleteProjectById {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EmployeesEntity employeesEntity = (EmployeesEntity) entityManager
                .createQuery("SELECT e FROM EmployeesEntity AS e WHERE e.id = 2").getSingleResult();
        entityManager.getTransaction().begin();
        entityManager.remove(employeesEntity);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
