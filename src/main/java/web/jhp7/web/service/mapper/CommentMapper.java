package web.jhp7.web.service.mapper;

import web.jhp7.web.domain.*;
import web.jhp7.web.service.dto.CommentDTO;

import javax.persistence.Lob;

import org.mapstruct.*;

/**
 * Mapper for the entity Comment and its DTO CommentDTO.
 */
@Mapper(componentModel = "spring", uses = {PostMapper.class, ProfileMapper.class, UserMapper.class})
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {

    @Mapping(source = "profile.id", target = "commentProfileId")
    @Mapping(source = "profile.user.id", target = "userId")
    @Mapping(source = "profile.user.firstName", target = "commentProfileUserFirstName")
    @Mapping(source = "profile.user.lastName", target = "commentProfileUserLastName")
    @Mapping(source = "profile.image", target = "profileImage")
    @Mapping(source = "profile.imageContentType", target = "profileImageContentType")
    @Mapping(source = "post.id", target = "postId")
    CommentDTO toDto(Comment comment);

    @Mapping(source = "commentProfileId", target = "profile.id")
    @Mapping(source = "userId", target = "profile.user.id")
    @Mapping(source = "commentProfileUserFirstName", target = "profile.user.firstName")
    @Mapping(source = "commentProfileUserLastName", target = "profile.user.lastName")
    @Mapping(source = "profileImage", target = "profile.image")
    @Mapping(source = "profileImageContentType", target = "profile.imageContentType")
    @Mapping(source = "postId", target = "post")
    Comment toEntity(CommentDTO commentDTO);

    default Comment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(id);
        return comment;
    }
}
