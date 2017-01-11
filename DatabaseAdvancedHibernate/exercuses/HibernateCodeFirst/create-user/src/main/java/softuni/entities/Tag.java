package softuni.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "tags")
public class Tag implements Serializable {
    private Long id;
    private String tag;
    private Set<Album> albums;

    public Tag() {
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
    @softuni.annotations.Tag
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @ManyToMany
    @JoinTable(name = "albums_tags",
    joinColumns = @JoinColumn(name = "tag_id"),
    inverseJoinColumns = @JoinColumn(name = "album_id"))
    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
