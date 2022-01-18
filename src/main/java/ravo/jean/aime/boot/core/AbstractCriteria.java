package ravo.jean.aime.boot.core;

import java.util.Objects;

/**
 * The Data Transfert Object Base class
 *
 * @author Jean Aimé Ravomanana
 */

public class AbstractCriteria<ID> {

    /**
     * The identifer
     */
    protected ID id;

    /**
     * The id if the current user
     */
    protected Long connectedUserId;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Long getConnectedUserId() {
        return connectedUserId;
    }

    public void setConnectedUserId(Long connectedUserId) {
        this.connectedUserId = connectedUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCriteria<?> that = (AbstractCriteria<?>) o;
        return Objects.equals(id, that.id) && Objects.equals(connectedUserId, that.connectedUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, connectedUserId);
    }

    public AbstractCriteria() {
    }
}
