package tasks;

import entities.softuniDatabase.EmployeesEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _11ConcurrentDatabaseChanges {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityManager entityManagerTwo = entityManagerFactory.createEntityManager();

        EmployeesEntity employee = (EmployeesEntity) entityManager.createQuery("SELECT e FROM EmployeesEntity AS e WHERE e.id = 1").getSingleResult();

        entityManager.getTransaction().begin();
        entityManagerTwo.getTransaction().begin();
        employee.setFirstName("Petrov");
//        entityManager.lock(employee, LockModeType.PESSIMISTIC_WRITE);
//        entityManagerTwo.lock(employee, LockModeType.PESSIMISTIC_WRITE);



        entityManager.getTransaction().commit();
        entityManagerTwo.getTransaction().commit();


        entityManager.close();
        entityManagerTwo.close();
        entityManagerFactory.close();
    }
}
