package pl.umk.mat.server.users;

import pl.umk.mat.server.comments.Comment;
import pl.umk.mat.server.games.Game;
import pl.umk.mat.server.utils.BaseEntity;
import pl.umk.mat.server.utils.enums.Role;

import javax.persistence.*;
import java.util.List;

import static java.util.Collections.emptyList;

@Entity
public class User extends BaseEntity<Long> {
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Game> games = emptyList();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private List<Comment> comments = emptyList();

    private String avatar = "avatar_default.jpg";

    public User() {
        super();
    }

    public User(String email, String firstName, String lastName, String password) {
        super();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
