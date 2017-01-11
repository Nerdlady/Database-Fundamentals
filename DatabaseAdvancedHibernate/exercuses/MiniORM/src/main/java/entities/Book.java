package entities;

import persistence.Column;
import persistence.Entity;
import persistence.Id;

import java.util.Date;

@Entity(name = "books")
public class Book {

    @Id
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "published_on")
    private Date publishedOn;

    @Column(name = "language")
    private String language;

    @Column(name = "is_hard_covered")
    private boolean isHardCovered;

    @Column(name = "rating")
    private int rating;

    public Book() {
    }

    public Book(String title, String author, Date publishedOn, String language, boolean isHardCovered,int rating) {
        this.title = title;
        this.author = author;
        this.publishedOn = publishedOn;
        this.language = language;
        this.isHardCovered = isHardCovered;
        this.setRating(rating);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(Date publishedOn) {
        this.publishedOn = publishedOn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isHardCovered() {
        return isHardCovered;
    }

    public void setHardCovered(boolean hardCovered) {
        isHardCovered = hardCovered;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
