package web.jhp7.web.service.mapper;

import web.jhp7.web.domain.*;
import web.jhp7.web.service.dto.BlogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Blog and its DTO BlogDTO.
 */
@Mapper(componentModel = "spring", uses = {CommunityMapper.class, UserMapper.class})
public interface BlogMapper extends EntityMapper<BlogDTO, Blog> {

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "community.id", target = "communityId")
    BlogDTO toDto(Blog blog);

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "communityId", target = "community")
    @Mapping(target = "posts", ignore = true)
//    @Mapping(target = "community", ignore = true)
    Blog toEntity(BlogDTO blogDTO);

    default Blog fromId(Long id) {
        if (id == null) {
            return null;
        }
        Blog blog = new Blog();
        blog.setId(id);
        return blog;
    }
}

//TODO: ESTA DEBERIA DE SER LA FORMA CORRECTA PARA QUE FUNCIONE. PROBARLA
//@Mapping(source = "user.id", target = "userId")
//@Mapping(source = "community.id", target = "communityId")
//
//
//@Mapping(source = "userId", target = "user")
//@Mapping(source = "communityId", target = "community")


//@Mapping(source = "id", target = "userId")
//@Mapping(source = "community.id", target = "communityId")
//BlogDTO toDto(Blog blog);
//
//@Mapping(source = "userId", target = "id")
//@Mapping(source = "communityId", target = "community")
//@Mapping(target = "posts", ignore = true)
////@Mapping(target = "community", ignore = true)
//Blog toEntity(BlogDTO blogDTO);