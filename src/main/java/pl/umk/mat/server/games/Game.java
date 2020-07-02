package pl.umk.mat.server.games;

import pl.umk.mat.server.comments.Comment;
import pl.umk.mat.server.users.User;
import pl.umk.mat.server.utils.BaseEntity;
import pl.umk.mat.server.utils.enums.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

import static java.util.Collections.emptyList;

@Entity
public class Game extends BaseEntity<Long> {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String info;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private Type type = Type.INNE;

    @Column(nullable = false)
    private String image;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private List<Comment> comments = emptyList();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Game() {
        super();
    }

    public Game(NewGame newGame, String image, User user) {
        super();
        this.title = newGame.getTitle();
        this.info = newGame.getInfo();
        this.description = newGame.getDescription();
        this.type = newGame.getType();
        this.image = image;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }


    public String getImage() {
        return image;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public User getUser() {
        return user;
    }

    public void update(NewGame newGame, User user) {
        this.title = newGame.getTitle();
        this.info = newGame.getInfo();
        this.description = newGame.getDescription();
        this.type = newGame.getType();
        this.user = user;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
