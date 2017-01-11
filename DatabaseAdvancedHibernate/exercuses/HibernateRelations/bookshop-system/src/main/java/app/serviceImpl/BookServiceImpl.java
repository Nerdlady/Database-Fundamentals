package app.serviceImpl;

import app.dao.BookRepository;
import app.domain.Author;
import app.domain.Book;
import app.domain.Category;
import app.domain.enums.AgeRestriction;
import app.domain.enums.EditionType;
import app.projections.ReducedBook;
import app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Primary
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public void save(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        this.bookRepository.delete(book);
    }

    @Override
    public void delete(Long id) {
        this.bookRepository.delete(id);
    }

    @Override
    public Book findBook(Long id) {
        return this.bookRepository.findOne(id);
    }

    @Override
    public Iterable<Book> findBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Iterable<Book> findBooksAfter(Date date) {
        return this.bookRepository.findAllBooksByReleaseDateAfter(date);
    }

    @Override
    public Iterable<Book> findBookByAuthor(Author author) {
        return this.bookRepository.findAllBooksByAuthorOrderByReleaseDateDescTitleAsc(author);
    }

    @Override
    public Iterable<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository.findByAgeRestriction(ageRestriction);
    }

    @Override
    public Iterable<Book> findByEditionTypeAndCopies(EditionType editionType, int copies) {
        return this.bookRepository.findByEditionTypeAndCopiesLessThan(editionType,copies);
    }

    @Override
    public Iterable<Book> findByPriceLowerThanOrGreaterThan(BigDecimal lowerThan, BigDecimal greaterThan) {
        return this.bookRepository.findByPriceLessThanOrPriceGreaterThan(lowerThan,greaterThan);
    }

    @Override
    public Iterable<Book> findByReleaseDateNot(Date dateBefore, Date dateAfter) {
        return this.bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(dateBefore,dateAfter);
    }

    @Override
    public Iterable<Book> findAllByCategoriesIn(Iterable<Category> categories) {
        return this.bookRepository.findAllByCategoriesIn(categories);
    }

    @Override
    public Iterable<Book> findAllByReleaseDateBefore(Date date) {
        return this.bookRepository.findAllByReleaseDateBefore(date);
    }

    @Override
    public Iterable<Book> findAllByTitleContains(String pattern) {
        return this.bookRepository.findAllByTitleContains(pattern);
    }

    @Override
    public Iterable<Book> findAllByAuthorLastNameStartsWith(String pattern) {
        return this.bookRepository.findAllByAuthor_LastNameStartsWith(pattern);
    }

    @Override
    public Iterable<Book> findAllByTitleLengthMoreThan(Integer length) {
        return this.bookRepository.findAllByTitleLengthMoreThan(length);
    }

    @Override
    public Iterable<Object[]> findSumOfCopies() {
        return this.bookRepository.findSumOfCopies();
    }

    @Override
    public Iterable<Object[]> findAllByCategoriesWithProfit() {
        return this.bookRepository.findAllByCategoriesWithProfit();
    }

    @Override
    public Iterable<Object[]> findCountOfBooksByCategories() {
        return this.bookRepository.findCountOfBooksByCategories();
    }

    @Override
    public Iterable<Book> findTop3ByCategoriesOrderByReleaseDate(Category category) {
        return this.bookRepository.findTop3ByCategoriesOrderByReleaseDateDescTitleAsc(category);
    }

    @Override
    public Iterable<ReducedBook> findFieldsByTitle(String title) {
        return this.bookRepository.findFieldsByTitle(title);
    }

    @Override
    public Integer updateByDateWithCopie(int copies, Date date) {
        return this.bookRepository.updateByDateWithCopie(copies,date);
    }

    @Override
    public Integer removeBookWithCopiesLessThan(Integer copies) {
        return this.bookRepository.removeBookWithCopiesLessThan(copies);
    }
}
