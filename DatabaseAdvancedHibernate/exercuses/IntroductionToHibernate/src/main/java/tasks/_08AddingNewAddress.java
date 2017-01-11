package tasks;

import entities.softuniDatabase.AddressesEntity;
import entities.softuniDatabase.EmployeesEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _08AddingNewAddress {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        AddressesEntity addresss = new AddressesEntity();
        addresss.setAddressText("Vitoshka 15");
        entityManager.persist(addresss);

        EmployeesEntity employeesEntity = (EmployeesEntity) entityManager
                .createQuery("SELECT e FROM EmployeesEntity AS e WHERE e.lastName = 'Nakov'").getSingleResult();

        employeesEntity.setAddressesByAddressId(addresss);
        entityManager.persist(employeesEntity);
        entityManager.getTransaction().commit();

        EmployeesEntity nakov = (EmployeesEntity) entityManager
                .createQuery("SELECT e FROM EmployeesEntity AS e WHERE e.lastName = 'Nakov'").getSingleResult();

        System.out.println(nakov.getAddressesByAddressId().getAddressText());

        entityManager.close();
        entityManagerFactory.close();
    }
}
