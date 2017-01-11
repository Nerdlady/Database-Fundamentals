package softuni.entities;

import softuni.entities.enums.Color;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "albums")
public class Album implements Serializable {
    private Long id;
    private String name;
    private Color backgroundColor;
    private Boolean isPublic;
    private Set<Picture> pictures;
    private Set<User> users;
    private Set<Tag> tags;

    public Album() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Basic
    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pictures_albums",
            inverseJoinColumns = @JoinColumn(name = "picture_id"),
            joinColumns = @JoinColumn(name = "album_id"))
    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    @ManyToMany()
    @JoinTable(name = "users_albums",
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            joinColumns = @JoinColumn(name = "album_id"))
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @ManyToMany
    @JoinTable(name = "albums_tags",
            inverseJoinColumns = @JoinColumn(name = "tag_id"),
            joinColumns = @JoinColumn(name = "album_id"))
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
