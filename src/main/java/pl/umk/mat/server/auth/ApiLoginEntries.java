package pl.umk.mat.server.auth;

import pl.umk.mat.server.users.User;
import pl.umk.mat.server.utils.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
public class ApiLoginEntries extends BaseEntity<Long> {
    @Column(unique = true, nullable = false)
    private String token;

    @Column(nullable = false)
    private Instant expiredAt;

    @ManyToOne
    private User user;

    public ApiLoginEntries(User user, String token, Instant expiredAt) {
        this.createdAt = Instant.now();
        this.user = user;
        this.token = token;
        this.expiredAt = expiredAt;
    }

    public ApiLoginEntries() {
        //JPA
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public Instant getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Instant expiredAt) {
        this.expiredAt = expiredAt;
    }
}
