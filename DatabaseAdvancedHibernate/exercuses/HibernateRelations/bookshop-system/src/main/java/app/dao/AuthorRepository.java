package app.dao;

import app.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Long> {
    Author findById(Long id);

    Iterable<Author> findAll();

    @Query("SELECT a FROM Author AS a JOIN a.booksByAuthor AS b WHERE b.releaseDate < :date")
    Iterable<Author> findAllAuthorsByReleaseDateBefore(@Param("date") Date date);

    @Query("SELECT a FROM Author AS a order by a.booksByAuthor.size desc ")
    Iterable<Author> findAllAuthorsOrderByCountBooksByAuthor();

    Author findAuthorByLastName(String lastName);

    Iterable<Author> findAllByFirstNameEndsWith(String pattern);

    @Procedure(name = "get_author_books_count")
    Integer getAuthorsBooksCountProcedure(@Param("firstName") String firstName,@Param("lastName") String lastName);
}
