package softuni.entities;

import softuni.entities.enums.Role;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "users_albums")
public class UserAlbum implements Serializable {

    private UserAlbumId userAlbumId;
    private Role role;

    public UserAlbum() {
    }


    @EmbeddedId
    public UserAlbumId getUserAlbumId() {
        return userAlbumId;
    }

    public void setUserAlbumId(UserAlbumId userAlbumId) {
        this.userAlbumId = userAlbumId;
    }

    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
