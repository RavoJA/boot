package ravo.jean.aime.boot.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

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

}
