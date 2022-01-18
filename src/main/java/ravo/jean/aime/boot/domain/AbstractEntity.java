package ravo.jean.aime.boot.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The entity base class
 *
 * @param <ID> the id type parameter
 */

@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {
    /**
     * The ID of the target entity
     */
    @Id
    private ID id;

    /**
     * The created date of the record
     */
    @Column(name = "creation_date")
    protected LocalDateTime dateCreation = LocalDateTime.now();

    /**
     * Indicate if the record id deleted or not
     */
    @Column(name = "deleted")
    protected boolean deleted = false;

    public AbstractEntity() {
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return deleted == that.deleted && Objects.equals(id, that.id) && Objects.equals(dateCreation, that.dateCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCreation, deleted);
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                ", dateCreation=" + dateCreation +
                ", deleted=" + deleted +
                '}';
    }


}
