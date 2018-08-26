package web.jhp7.web.service.mapper;

import web.jhp7.web.domain.*;
import web.jhp7.web.service.dto.PostDTO;
import web.jhp7.web.service.dto.UserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Post and its DTO PostDTO.
 */
@Mapper(componentModel = "spring", uses = {UrllinkMapper.class, BlogMapper.class, 
		ProfileMapper.class, CustomTagMapper.class, CommentMapper.class, UrllinkMapper.class, UserMapper.class})
public interface PostMapper extends EntityMapper<PostDTO, Post> {

    @Mapping(source = "blog.id", target = "blogId")
    @Mapping(source = "blog.title", target = "blogTitle")
    @Mapping(source = "profile.id", target = "profileId")
    @Mapping(source = "profile.user.id", target = "userId")
//    @Mapping(source = "id", target = "userDTO")
    PostDTO toDto(Post post);

    @Mapping(source = "blogId", target = "blog")
    @Mapping(source = "profileId", target = "profile")
    @Mapping(source = "userId", target = "id")
//    @Mapping(source = "userId", target = "id")
//    @Mapping(target = "profile", ignore = true)
//    @Mapping(target = "userDTO", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "urllink", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "topics", ignore = true)
    Post toEntity(PostDTO postDTO);

    default Post fromId(Long id) {
        if (id == null) {
            return null;
        }
        Post post = new Post();
        post.setId(id);
        return post;
    }
}
