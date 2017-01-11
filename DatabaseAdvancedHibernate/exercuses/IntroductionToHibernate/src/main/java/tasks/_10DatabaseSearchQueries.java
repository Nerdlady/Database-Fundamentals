package tasks;

import entities.softuniDatabase.AddressesEntity;
import entities.softuniDatabase.DepartmentsEntity;
import entities.softuniDatabase.EmployeesEntity;
import entities.softuniDatabase.EmployeesProjectsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _10DatabaseSearchQueries {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Part 1
        System.out.println("--- PART 1 ---");
        List<EmployeesEntity> employees = entityManager
                .createQuery("SELECT e FROM EmployeesEntity AS e " +
                        "INNER JOIN EmployeesProjectsEntity AS ep " +
                        "ON ep.employeeId = e.employeeId " +
                        "INNER JOIN ProjectsEntity AS p " +
                        "ON p.projectId = ep.projectId " +
                        "WHERE DATE_FORMAT(p.startDate,'%Y')>= 2001 AND DATE_FORMAT(p.startDate,'%Y') <= 2003")
                .getResultList();

        for (EmployeesEntity employee : employees) {
            System.out.printf("Name: %s %s Manager name: %s %s%n" +
                            "Projects:%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmployeesByManagerId().getFirstName(),
                    employee.getEmployeesByManagerId().getLastName());


            for (EmployeesProjectsEntity employeesProjectsEntity : employee.getEmployeesProjectsesByEmployeeId()) {
                System.out.printf(" Project name: %s Start date: %s End date: %s%n",
                        employeesProjectsEntity.getProjectsByProjectId().getName(),
                        employeesProjectsEntity.getProjectsByProjectId().getStartDate(),
                        employeesProjectsEntity.getProjectsByProjectId().getEndDate());
            }
        }

        // Part 2
        System.out.println("--- PART 2 ---");
        List<AddressesEntity> addresses = entityManager.createQuery("SELECT DISTINCT a FROM AddressesEntity AS a " +
                "INNER JOIN EmployeesEntity AS e " +
                "ON e.addressId = a.addressId " +
                "INNER JOIN TownsEntity AS t " +
                "ON t.townId = a.townId").getResultList();

        addresses.stream().sorted((a, b) -> {
            int result = Integer.compare(b.getEmployeesByAddressId().size(), a.getEmployeesByAddressId().size());
            if (result == 0) {
                result = a.getTownsByTownId().getName().compareTo(b.getTownsByTownId().getName());
            }
            return result;
        }).limit(10).forEach(a -> System.out.printf("%s %s - %d employees%n",
                a.getAddressText(),
                a.getTownsByTownId().getName(),
                a.getEmployeesByAddressId().size()));


        // Part 3
        System.out.println("--- PART 3 ---");
        EmployeesEntity employee = (EmployeesEntity) entityManager
                .createQuery("SELECT e FROM EmployeesEntity AS e WHERE e.id = 147").getSingleResult();

        System.out.printf("Name: %s %s Job title: %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        employee.getEmployeesProjectsesByEmployeeId()
                .stream()
                .sorted((a, b) -> a.getProjectsByProjectId().getName().compareTo(b.getProjectsByProjectId().getName()))
                .forEach(a -> System.out.printf(" Project name: %s%n", a.getProjectsByProjectId().getName()));

        // Part 4
        System.out.println("--- PART 4 ---");

        List<DepartmentsEntity> departments = entityManager
                .createQuery("SELECT d FROM DepartmentsEntity AS d WHERE d.employeesByDepartmentId.size > 5")
                .getResultList();

        System.out.println(departments.size());

        departments.stream()
                .sorted((a, b) -> Integer.compare(a.getEmployeesByDepartmentId().size(), b.getEmployeesByDepartmentId().size()))
                .forEach(a -> System.out.printf("--%s - Manager: %s,Employyes: %d%n",
                        a.getName(),
                        a.getEmployeesByManagerId().getLastName(),
                        a.getEmployeesByDepartmentId().size()));


        entityManager.close();
        entityManagerFactory.close();

    }
}
