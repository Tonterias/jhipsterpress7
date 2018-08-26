package web.jhp7.web.service.mapper;

import web.jhp7.web.domain.*;
import web.jhp7.web.service.dto.AlbumDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Album and its DTO AlbumDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AlbumMapper extends EntityMapper<AlbumDTO, Album> {

    @Mapping(source = "user.id", target = "userId")
    AlbumDTO toDto(Album album);

    @Mapping(target = "photos", ignore = true)
    @Mapping(source = "userId", target = "user")
//    @Mapping(target = "user", ignore = true)
    Album toEntity(AlbumDTO albumDTO);

    default Album fromId(Long id) {
        if (id == null) {
            return null;
        }
        Album album = new Album();
        album.setId(id);
        return album;
    }
}
