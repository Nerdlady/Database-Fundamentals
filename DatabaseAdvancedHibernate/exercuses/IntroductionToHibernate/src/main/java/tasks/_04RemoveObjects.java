package tasks;

import entities.softuniDatabase.TownsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _04RemoveObjects {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<TownsEntity> towns = entityManager.createQuery("SELECT t FROM TownsEntity AS t").getResultList();

        for (TownsEntity town : towns) {
            if (town.getName().length() > 5) {
                entityManager.detach(town);
            }
        }

        entityManager.getTransaction().begin();
        for (TownsEntity town : towns) {
            if (entityManager.contains(town)) {
                town.setName(town.getName().toLowerCase());
            }
        }
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
