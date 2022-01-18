package ravo.jean.aime.boot.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Dto base class
 *
 * @param <ID> the id type parameter
 * @author Jean Aimé
 */

public abstract class AbstractDto<ID> implements Serializable {
    /**
     * The ID of the target dto
     */

    protected ID id;

    /**
     * The current connected user
     */
    private String connectedUserId;

    public AbstractDto() {
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getConnectedUserId() {
        return connectedUserId;
    }

    public void setConnectedUserId(String connectedUserId) {
        this.connectedUserId = connectedUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDto<?> that = (AbstractDto<?>) o;
        return Objects.equals(id, that.id) && Objects.equals(connectedUserId, that.connectedUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, connectedUserId);
    }

    @Override
    public String toString() {
        return "AbstractDto{" +
                "id=" + id +
                ", connectedUserId='" + connectedUserId + '\'' +
                '}';
    }
}
