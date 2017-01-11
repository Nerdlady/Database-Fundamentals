package tasks;

import entities.softuniDatabase.EmployeesEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _06DataRefresh {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EmployeesEntity employeesEntity = (EmployeesEntity) entityManager
                .createQuery("SELECT e FROM EmployeesEntity AS e WHERE e.id = 4").getSingleResult();

        employeesEntity.setFirstName(employeesEntity.getFirstName().toUpperCase());

        //  entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employeesEntity);
        entityManager.getTransaction().commit();

        System.out.println(employeesEntity.getFirstName());
        entityManager.close();
        entityManagerFactory.close();

    }
}
