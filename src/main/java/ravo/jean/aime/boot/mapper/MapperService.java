package ravo.jean.aime.boot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Perform a mapping between two class
 *
 * @param <E> the entity type
 * @param <DTO>> the dto type
 * @author Jean Aimé
 *
 * <div>
 *     How to implement it
 *     //@Mapper
 *     public interface CarMapper extends MapperService<Car, CarDto> {
 *     CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );
 * }
 * </div>
 *
 */

public interface MapperService<E, DTO> {
    /**
     * Map the provided dto to entity
     */
    E mapToEntity(DTO dto);

    /**
     * Map the entity to Dto
     */
    DTO mapToDto(E entity);

}
