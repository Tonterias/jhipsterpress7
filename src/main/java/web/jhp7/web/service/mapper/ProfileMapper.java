package web.jhp7.web.service.mapper;

import web.jhp7.web.domain.*;
import web.jhp7.web.service.dto.ProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Profile and its DTO ProfileDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ProfileMapper extends EntityMapper<ProfileDTO, Profile> {

    ProfileDTO toDto(Profile profile);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "followeds", ignore = true)
    @Mapping(target = "followings", ignore = true)
    @Mapping(target = "interests", ignore = true)
    @Mapping(target = "activities", ignore = true)
    @Mapping(target = "celebs", ignore = true)
    Profile toEntity(ProfileDTO profileDTO);

    default Profile fromId(Long id) {
        if (id == null) {
            return null;
        }
        Profile profile = new Profile();
        profile.setId(id);
        return profile;
    }
}
