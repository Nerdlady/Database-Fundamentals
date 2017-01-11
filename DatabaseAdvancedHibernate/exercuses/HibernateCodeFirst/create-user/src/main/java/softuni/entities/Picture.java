package softuni.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "pictures")
public class Picture implements Serializable {
    private Long id;
    private String title;
    private String caption;
    private String path;
    private Set<Album> albums;

    public Picture() {
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
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Basic
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @ManyToMany
    @JoinTable(name = "pictures_albums",
    joinColumns = @JoinColumn(name = "picture_id"),
    inverseJoinColumns = @JoinColumn(name = "album_id"))
    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
