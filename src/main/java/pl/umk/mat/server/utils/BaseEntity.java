package pl.umk.mat.server.utils;

import org.springframework.data.domain.Persistable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity<T extends Serializable> implements Persistable<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected T id;
    protected Instant createdAt;

    public BaseEntity() {
        createdAt = Instant.now();
    }

    @Override
    public T getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
