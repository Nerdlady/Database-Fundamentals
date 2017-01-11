package app.serviceImpl;

import app.dao.AuthorRepository;
import app.domain.Author;
import app.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Primary
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void save(Author author) {
        this.authorRepository.save(author);
    }

    @Override
    public void delete(Author author) {
        this.authorRepository.delete(author);
    }

    @Override
    public void delete(Long id) {
        this.authorRepository.delete(id);
    }

    @Override
    public Author findAuthor(Long id) {
        return this.authorRepository.findOne(id);
    }

    @Override
    public Iterable<Author> findAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Iterable<Author> findAllWitBookBefore(Date date) {
        return this.authorRepository.findAllAuthorsByReleaseDateBefore(date);
    }

    @Override
    public Iterable<Author> findAllOrderByCountOfBooks() {
        return this.authorRepository.findAllAuthorsOrderByCountBooksByAuthor();
    }

    @Override
    public Author findAuthorByLastName(String lastName) {
        return this.authorRepository.findAuthorByLastName(lastName);
    }

    @Override
    public Iterable<Author> findAllByFirstNameEndsWith(String pattern) {
        return this.authorRepository.findAllByFirstNameEndsWith(pattern);
    }

    @Override
    public Integer getAuthorsBooksCount(String firstName, String lastName) {
        return this.authorRepository.getAuthorsBooksCountProcedure(firstName,lastName);
    }
}
