import connector.Connector;
import entities.Book;
import entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            Connector.initConnection("mysql", "root", "1234", "localhost", "3306", "school");
            Connection connection = Connector.getConnection();
            EntityManager entityManager = new EntityManager(connection);


            //  11.	Fetch Users
            User user = new User("Ivan","pass",26,new SimpleDateFormat("yyyy-MM-dd").parse("2014-08-08"),new Date(),true);

            entityManager.persist(user);
            User user1 = new User("Pesho","pass",17,new SimpleDateFormat("yyyy-MM-dd").parse("2009-12-05"),new SimpleDateFormat("yyyy-MM-dd").parse("2009-12-05"),false);
            entityManager.persist(user1);

            User user2 = new User("Mariika","pass",20,new SimpleDateFormat("yyyy-MM-dd").parse("2013-05-18"),new SimpleDateFormat("yyyy-MM-dd").parse("2013-05-18"),false);
            entityManager.persist(user2);

            String where = " registration_date > '2010-01-01'";
            for (User arg : entityManager.find(User.class, where, " age > 17")) {
                System.out.println(arg.getUsername() + " " + arg.getPassword());
            }

            // 12.	Add New Entity
            Book book1 = new Book("The Lord of the Rings","J. R. R. Tolkien",new Date(),"English",true,10);
            Book book2 = new Book("The Hobbit","J. R. R. Tolkien",new Date(),"English",true,8);
            Book book3 = new Book("Harry Potter ","J. K. Rowling",new Date(),"English",true,7);
            Book book4 = new Book("The Hunger Games  ","Suzanne Collins ",new Date(),"English",false,5);
            entityManager.persist(book1);
            entityManager.persist(book2);
            entityManager.persist(book3);
            entityManager.persist(book4);

            Book book5 = new Book("0123456789012345678901234567897777777777","numbers",new Date(),"English",true,1);
            entityManager.persist(book5);
            for (Book book : entityManager.find(Book.class, "CHAR_LENGTH(title) > 30", "is_hard_covered = 1")) {
                String changedTitle = book.getTitle().substring(0, 30);
                book.setTitle(changedTitle);
                entityManager.persist(book);
            }

            Book bookRating = new Book("Book rating update","Update",new Date(),"English",false,5);
            entityManager.persist(bookRating);

            // 13.	Update Entity
            List<Book> books = new ArrayList<>();

            for (Book book : entityManager.find(Book.class)) {
                books.add(book);
            }

            books.stream().sorted((a, b) -> Integer.compare(b.getRating(), a.getRating())).limit(3).sorted((a,b) -> {
                int result = Integer.compare(b.getRating(),a.getRating());
                if (result == 0){
                    result = a.getTitle().compareTo(b.getTitle());
                }
                return result;
            }).forEach(a -> {
                System.out.println("Tittle: " + a.getTitle() + " Author: " + a.getAuthor() + " Raithing: " + a.getRating());
            });

            // 14.	Update Records
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int year =  Integer.parseInt(reader.readLine());

            int affectedRows =  0;
            for (Book book : entityManager.find(Book.class,"published_on >= " + year,"is_hard_covered = 1")) {
                String titleToUpper = book.getTitle().toUpperCase();
                book.setTitle(titleToUpper);
                entityManager.persist(book);
                affectedRows++;
            }

            System.out.printf("Books released after 2000 year: %d%n",affectedRows);
            for (Book book : entityManager.find(Book.class,"published_on >= " + year,"is_hard_covered = 1")) {
                String title = book.getTitle();
                System.out.println(title);
            }

            // 15.	Delete Records
            int deletedRows = 0;
            for (Book book : entityManager.find(Book.class,"rating < 2")) {
               entityManager.delete(Book.class,book.getId());
                deletedRows++;
            }
            System.out.printf("%d Books has been deleted from the database.",deletedRows);

            // 16.	Delete Inactive Users
            String username = reader.readLine();
            User currentUser = entityManager.findFirst(User.class, "username = '" + username + "'");
            if (currentUser != null && currentUser.getUsername() != null) {
                Date lastOnline = currentUser.getLastLoginInTime();
                Date timeNow = new Date();

                long nowMil = timeNow.getTime();
                long lastTimiMil = lastOnline.getTime();

                long seconds = (nowMil - lastTimiMil) / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;
                long weeks = days / 7;
                long mounts = weeks / 4;
                long years = mounts / 12;

                String time;
                if (seconds < 1) {
                    time = "less than a second";
                } else if (minutes < 1) {
                    time = "less than a minute";
                } else if (hours < 1) {
                    time = minutes + " minutes ago";
                } else if (days < 1) {
                    time = hours + "hours ago";
                } else if (weeks < 1) {
                    time = days + " days ago";
                } else if (mounts < 1) {
                    time = weeks + " weeks ago";
                } else if (years < 1) {
                    time = mounts + " months ago";
                } else {
                    time = "more than a year";
                    currentUser.setActive(false);
                    entityManager.persist(currentUser);
                }

                System.out.printf("User %s was last online %s.%n", currentUser.getUsername(), time);

                if (!currentUser.isActive()) {
                    System.out.println("Would you like to delete that user? (yes/no)");
                    String answer = reader.readLine();
                    if (answer.equals("yes")) {
                        entityManager.delete(User.class, currentUser.getId());
                        System.out.printf("User %s was successfully deleted from the database.", currentUser.getUsername());
                    }
                }
            } else {
                System.out.println("No user with username " + username);
            }


        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
