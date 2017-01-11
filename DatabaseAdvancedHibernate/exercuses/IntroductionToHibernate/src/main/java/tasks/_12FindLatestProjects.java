package tasks;

import entities.softuniDatabase.ProjectsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _12FindLatestProjects {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<ProjectsEntity> projects = entityManager.createQuery("SELECT p FROM ProjectsEntity AS p ORDER BY p.startDate DESC").setMaxResults(10).getResultList();

         projects.stream().sorted((a,b) -> a.getName().compareTo(b.getName())).forEach(project ->
                 System.out.printf("Project name: %s , Description: %s, Start date: %s, End date: %s%n",
                 project.getName(),
                 project.getDescription(),
                 project.getStartDate(),
                 project.getEndDate()));

        entityManager.close();
        entityManagerFactory.close();
    }
}
