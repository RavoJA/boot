package ravo.jean.aime.boot.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ravo.jean.aime.boot.core.AbstractCriteria;
import ravo.jean.aime.boot.domain.AbstractEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * The QueryDSL extension for JPA repository
 *
 * @param <E>  the Entity type
 * @param <ID> the identifer type
 * @param <SC> the search criteria type
 * @author Jean Aimé R.
 */
public interface QueryDSLRepository<E extends AbstractEntity<ID>, ID extends Serializable,
        SC extends AbstractCriteria<ID>> {

    /**
     * Feth one record by criteria if exist
     *
     * @param searchCriteria the search criteria parameter
     */
    Optional<E> fetchOne(SC searchCriteria);

    /**
     * Feth All record by criteria
     *
     * @param searchCriteria the search criteria parameter
     */
    List<E> findAll(SC searchCriteria);

    /**
     * Feth All record by Page by criteria
     *
     * @param pageable       pageable parameter
     * @param searchCriteria the search criteria parameter
     */
    Page<E> findAll(SC searchCriteria, Pageable pageable);

    /**
     * Count record by criteria
     *
     * @param searchCriteria the search criteria parameter
     */
    Long count(SC searchCriteria);

}
