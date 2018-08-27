package web.jhp7.web.service.mapper;

import web.jhp7.web.domain.*;
import web.jhp7.web.service.dto.CalbumDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Calbum and its DTO CalbumDTO.
 */
@Mapper(componentModel = "spring", uses = {CommunityMapper.class})
public interface CalbumMapper extends EntityMapper<CalbumDTO, Calbum> {

    @Mapping(source = "community.user.id", target = "userId")
    CalbumDTO toDto(Calbum calbum);

    @Mapping(target = "photos", ignore = true)
    @Mapping(source = "userId", target = "user")
//    @Mapping(target = "community", ignore = true)
    Calbum toEntity(CalbumDTO calbumDTO);

    default Calbum fromId(Long id) {
        if (id == null) {
            return null;
        }
        Calbum calbum = new Calbum();
        calbum.setId(id);
        return calbum;
    }
}
