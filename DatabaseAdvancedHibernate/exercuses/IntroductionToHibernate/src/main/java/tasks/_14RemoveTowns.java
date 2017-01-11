package tasks;

import entities.softuniDatabase.AddressesEntity;
import entities.softuniDatabase.EmployeesEntity;
import entities.softuniDatabase.TownsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class _14RemoveTowns {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String townName = reader.readLine();

        Query andressesQuery = entityManager.createQuery("SELECT a FROM AddressesEntity AS a WHERE a.townsByTownId.name = :townName");
        andressesQuery.setParameter("townName", townName);
        List<AddressesEntity> addresses = andressesQuery.getResultList();


        int deletedAddressesCount = 0;
        Query removeEmployeeAdress = entityManager
                .createQuery("SELECT e FROM EmployeesEntity AS e WHERE e.addressesByAddressId.addressId = :adressId");

        for (AddressesEntity address : addresses) {
            removeEmployeeAdress.setParameter("adressId", address.getAddressId());
            List<EmployeesEntity> employees = removeEmployeeAdress.getResultList();

            for (EmployeesEntity employee : employees) {
                entityManager.getTransaction().begin();
                employee.setAddressesByAddressId(null);
                employee.setAddressId(null);
                entityManager.persist(employee);
                entityManager.getTransaction().commit();
            }
            entityManager.getTransaction().begin();
            entityManager.remove(address);
            entityManager.getTransaction().commit();
            deletedAddressesCount++;
        }

        Query townToDeleteQuery = entityManager.createQuery("SELECT t FROM TownsEntity AS t WHERE t.name = :townName");
        townToDeleteQuery.setParameter("townName", townName);

        TownsEntity townToDelete = (TownsEntity) townToDeleteQuery.getSingleResult();

        entityManager.getTransaction().begin();
        entityManager.remove(townToDelete);
        entityManager.getTransaction().commit();

        System.out.printf("%d address in %s was deleted%n", deletedAddressesCount, townName);

        entityManager.close();
        entityManagerFactory.close();
    }
}
