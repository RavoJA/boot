package ravo.jean.aime.boot.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ravo.jean.aime.boot.annotation.BootInternalAnnotationservice;
import ravo.jean.aime.boot.core.AbstractCriteria;
import ravo.jean.aime.boot.core.GeneratorUtils;
import ravo.jean.aime.boot.domain.AbstractEntity;
import ravo.jean.aime.boot.errorhandler.AppResponseEntityException;
import ravo.jean.aime.boot.querydsl.QueryDSLRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Abstract calss for managing a dynamic search with QueryDsl
 *
 * @param <SC> search criteria type for search
 * @param <E>  entity type
 * @param <RP> the repository
 * @author Jean Aimé R
 */

@Transactional
@Service
public abstract class AbstractSearchCriteriaServiceImpl<E extends AbstractEntity<ID>, ID extends Serializable,
        RP extends JpaRepository<E, ID>, SC extends AbstractCriteria<ID>>
        implements SearchCriteriaService<E, ID, SC> {
    Logger log = Logger.getLogger("MyLog");
    /**
     * the managed entity name
     */
    private final String targetEntity;

    private final BootInternalAnnotationservice bootInternalAnnotationservice;


    public AbstractSearchCriteriaServiceImpl(String targetEntity, BootInternalAnnotationservice bootInternalAnnotationservice) {
        this.targetEntity = targetEntity;
        this.bootInternalAnnotationservice = bootInternalAnnotationservice;
    }

    /**
     * The repository must be redefined for each reel implementation
     */
    public abstract RP repository();

    @Override
    public Optional<E> fetchOneByCriteria(SC searchCriteria) {
        QueryDSLRepository repo = (QueryDSLRepository) repository();
        log.info(String.format("[Fecthing by criteria], getting one %s", targetEntity));
        return repo.fetchOne(searchCriteria);
    }

    @Override
    public List<E> fetchAllByAndCriteria(SC searchCriteria) {
        QueryDSLRepository repo = (QueryDSLRepository) repository();
        log.info(String.format("[Fecthing by criteria] getting all %s", targetEntity));
        return repo.findAll(searchCriteria);
    }

    @Override
    public Page<E> findAll(SC searchCriteria, Pageable pageable) {
        QueryDSLRepository repo = (QueryDSLRepository) repository();
        log.info("[Fecthing by criteria] getting all by page " + targetEntity);
        return repo.findAll(searchCriteria, pageable);
    }

    @Override
    public Long count(SC searchCriteria) {
        QueryDSLRepository repo = (QueryDSLRepository) repository();
        log.info("[Fecthing by criteria] count record  " + targetEntity);
        return repo.count(searchCriteria);
    }

    @Override
    public E saveRecord(E entity) throws AppResponseEntityException, IllegalAccessException {

        try {
            bootInternalAnnotationservice.makeUpperCase(entity, entity.getClass());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        log.info("[Validating] data of " + entity);
        E entityValidate = validate(entity);

        log.info("[Ckeking unique value] ");

        List<E> uniqueChecks = bootInternalAnnotationservice.checkUniqValue(entity, entity.getClass());

        if (entity.getId() != null && !entity.getId().toString().isEmpty()) {
            if (!uniqueChecks.isEmpty()) {
                if (entity.getId().equals(uniqueChecks.get(0).getId())) {
                    log.info("[Updating] " + targetEntity);
                    return repository().save(entityValidate);
                } else {
                    throw new AppResponseEntityException("Duplicated record");
                }
            } else {
                log.info("[Updating] " + targetEntity);
                return repository().save(entityValidate);
            }
        } else {
            if (!uniqueChecks.isEmpty())
                throw new AppResponseEntityException("Duplicated record");
            if (entity.getId() instanceof String) {
                entity.setId((ID) GeneratorUtils.uuid());
            } else {
                entity.setId((ID) GeneratorUtils.longUuid());
            }
            log.info("[Saving] new " + targetEntity);
            return repository().save(entityValidate);
        }
    }

    @Override
    public List<E> fetchAllRecord() {
        log.info("[Fecthing all record] of " + targetEntity);
        return repository().findAll();
    }

    @Override
    public Page<E> fetchAllRecordByPage(Pageable pageable) {
        return repository().findAll(pageable);
    }

    @Override
    public E fetchOneRecordById(ID id) throws AppResponseEntityException {
        log.info("[Fetching record ] " + targetEntity + " with ID " + id);
        return repository().findById(id)
                .orElseThrow(() -> new AppResponseEntityException("Unable to find " + targetEntity + " with ID " + id));
    }

    @Override
    public ID deleteRecord(ID id, boolean softDelete) throws AppResponseEntityException {
        if (!softDelete) {
            log.info("[Deleting record ] physically " + targetEntity + " with ID " + id);
            repository().deleteById(id);
            repository().flush();
        } else {
            E entity = fetchOneRecordById(id);
            log.info("[Deleting record ], executing soft delete " + targetEntity + " with ID " + id);
            entity.setDeleted(true);
            repository().save(entity);
        }
        return id;
    }

}
