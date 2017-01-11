package app.dao;

import app.domain.Author;
import app.domain.Book;
import app.domain.Category;
import app.domain.enums.AgeRestriction;
import app.domain.enums.EditionType;
import app.projections.ReducedBook;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Book findById(Long id);

    Iterable<Book> findAll();

    Iterable<Book> findAllBooksByReleaseDateAfter(Date date);

    Iterable<Book> findAllBooksByAuthorOrderByReleaseDateDescTitleAsc(Author author);

    Iterable<Book> findByAgeRestriction(AgeRestriction ageRestriction);

    Iterable<Book> findByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    Iterable<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lowerThan, BigDecimal greaterThan);

    Iterable<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(Date dateBefore, Date dateAfter);

    Iterable<Book> findAllByCategoriesIn(Iterable<Category> categories);

    Iterable<Book> findAllByReleaseDateBefore(Date date);

    Iterable<Book> findAllByTitleContains(String pattern);

    Iterable<Book> findAllByAuthor_LastNameStartsWith(String pattern);

    @Query("SELECT b FROM Book AS b WHERE LENGTH(b.title) > :length")
    Iterable<Book> findAllByTitleLengthMoreThan(@Param("length") Integer length);

    @Query("SELECT a, SUM(b.copies) AS copies FROM Book AS b INNER JOIN b.author AS a GROUP BY a ORDER BY copies DESC")
    Iterable<Object[]> findSumOfCopies();

    @Query("SELECT c,SUM(b.price * b.copies) AS profit FROM Book  AS b INNER JOIN b.categories AS c GROUP BY c.name ORDER BY profit DESC, c.name ASC")
    Iterable<Object[]> findAllByCategoriesWithProfit();

    @Query(value = "SELECT c, COUNT(b) AS bookCount FROM Book AS b INNER JOIN b.categories AS c GROUP BY c.name ORDER BY COUNT(b)")
    Iterable<Object[]> findCountOfBooksByCategories();

    Iterable<Book> findTop3ByCategoriesOrderByReleaseDateDescTitleAsc(Category category);

    @Query("SELECT b.title AS title,b.editionType AS editionType,b.ageRestriction AS ageRestriction,b.price AS price FROM Book AS b WHERE b.title = :title")
    Iterable<ReducedBook> findFieldsByTitle(@Param("title") String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book AS b SET b.copies = b.copies + :copies WHERE b.releaseDate > :date")
    Integer updateByDateWithCopie(@Param("copies") int copies,@Param("date") Date date);

    @Modifying
    @Transactional
    @Query("DELETE FROM Book AS b WHERE b.copies < :copies ")
    Integer removeBookWithCopiesLessThan(@Param("copies") Integer copies);


}
