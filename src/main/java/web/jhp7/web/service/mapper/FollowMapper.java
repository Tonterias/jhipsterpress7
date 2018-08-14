package web.jhp7.web.service.mapper;

import web.jhp7.web.domain.*;
import web.jhp7.web.service.dto.FollowDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Follow and its DTO FollowDTO.
 */
@Mapper(componentModel = "spring", uses = {CommunityMapper.class, ProfileMapper.class})
public interface FollowMapper extends EntityMapper<FollowDTO, Follow> {

    FollowDTO toDto(Follow follow);

    @Mapping(target = "cfollowed", ignore = true)
    @Mapping(target = "cfollowing", ignore = true)
    @Mapping(target = "followed", ignore = true)
    @Mapping(target = "following", ignore = true)
    Follow toEntity(FollowDTO followDTO);

    default Follow fromId(Long id) {
        if (id == null) {
            return null;
        }
        Follow follow = new Follow();
        follow.setId(id);
        return follow;
    }
}
