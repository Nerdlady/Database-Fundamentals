package tasks;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _05ContainsEmployee {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query =  entityManager
                .createQuery("SELECT e FROM EmployeesEntity AS e WHERE concat(e.firstName,' ',e.lastName) = :name");
        query.setParameter("name",name);

        if (query.getResultList().size() > 0){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
