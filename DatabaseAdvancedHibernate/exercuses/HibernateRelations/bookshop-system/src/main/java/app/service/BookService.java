package app.service;

import app.domain.Author;
import app.domain.Book;
import app.domain.Category;
import app.domain.enums.AgeRestriction;
import app.domain.enums.EditionType;
import app.projections.ReducedBook;

import java.math.BigDecimal;
import java.util.Date;

public interface BookService {
    void save(Book book);

    void delete(Book book);

    void delete(Long id);

    Book findBook(Long id);

    Iterable<Book> findBooks();

    Iterable<Book> findBooksAfter(Date date);

    Iterable<Book> findBookByAuthor(Author author);

    Iterable<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    Iterable<Book> findByEditionTypeAndCopies(EditionType editionType, int copies);

    Iterable<Book> findByPriceLowerThanOrGreaterThan(BigDecimal lowerThan, BigDecimal greaterThan);

    Iterable<Book> findByReleaseDateNot(Date dateBefore, Date dateAfter);

    Iterable<Book> findAllByCategoriesIn(Iterable<Category> categories);

    Iterable<Book> findAllByReleaseDateBefore(Date date);

    Iterable<Book> findAllByTitleContains(String pattern);

    Iterable<Book> findAllByAuthorLastNameStartsWith(String pattern);

    Iterable<Book> findAllByTitleLengthMoreThan(Integer length);

    Iterable<Object[]> findSumOfCopies();

    Iterable<Object[]> findAllByCategoriesWithProfit();

    Iterable<Object[]> findCountOfBooksByCategories();

    Iterable<Book> findTop3ByCategoriesOrderByReleaseDate(Category category);

    Iterable<ReducedBook> findFieldsByTitle(String title);

    Integer updateByDateWithCopie(int copies, Date date);

    Integer removeBookWithCopiesLessThan(Integer copies);
}
