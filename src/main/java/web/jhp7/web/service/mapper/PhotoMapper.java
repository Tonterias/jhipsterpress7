package web.jhp7.web.service.mapper;

import web.jhp7.web.domain.*;
import web.jhp7.web.service.dto.PhotoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Photo and its DTO PhotoDTO.
 */
@Mapper(componentModel = "spring", uses = {AlbumMapper.class, CalbumMapper.class})
public interface PhotoMapper extends EntityMapper<PhotoDTO, Photo> {

    @Mapping(source = "album.user.id", target = "albumUserId")
    @Mapping(source = "calbum.community.user.id", target = "cAlbumUserId")
    PhotoDTO toDto(Photo photo);

//    @Mapping(target = "album", ignore = true)
//    @Mapping(target = "calbum", ignore = true)
    @Mapping(source = "albumUserId", target = "album")
    @Mapping(source = "cAlbumUserId", target = "calbum")
    Photo toEntity(PhotoDTO photoDTO);

    default Photo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Photo photo = new Photo();
        photo.setId(id);
        return photo;
    }
}
