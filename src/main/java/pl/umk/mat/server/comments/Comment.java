package pl.umk.mat.server.comments;

import pl.umk.mat.server.games.Game;
import pl.umk.mat.server.users.User;
import pl.umk.mat.server.utils.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends BaseEntity<Long> {
    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Comment() {
        super();
    }

    public Comment(NewComment newComment, User user, Game game) {
        super();
        this.description = newComment.getDescription();
        this.user = user;
        this.game = game;
    }

    public String getDescription() {
        return description;
    }

    public Game getGame() {
        return game;
    }

    public User getUser() {
        return user;
    }
}
