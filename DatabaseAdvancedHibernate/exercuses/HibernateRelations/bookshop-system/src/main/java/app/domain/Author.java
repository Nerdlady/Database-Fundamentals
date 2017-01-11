package app.domain;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "authors")
@NamedStoredProcedureQuery(name = "get_author_books_count", procedureName = "up_get_number_of_book_by_author",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "firstName", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "lastName", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "count", type = Integer.class)
        })
public class Author {

    @Expose
    @XmlAttribute
    private Long id;
    @Expose
    @XmlElement
    private String firstName;
    @Expose
    @XmlElement
    private String lastName;

    @Expose
    @XmlElementWrapper(name = "books")
    @XmlElement
    private Set<Book> booksByAuthor;

    public Author() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null && firstName.length() < 5) {
            throw new IllegalArgumentException("First name too short");
        }
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    public Set<Book> getBooksByAuthor() {
        return booksByAuthor;
    }

    public void setBooksByAuthor(Set<Book> booksByAuthor) {
        this.booksByAuthor = booksByAuthor;
    }
}
