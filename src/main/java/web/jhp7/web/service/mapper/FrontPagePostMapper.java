package web.jhp7.web.service.mapper;

import web.jhp7.web.domain.Post;
import web.jhp7.web.repository.PostRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapper for the entity Post and its DTO PostDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class FrontPagePostMapper {

    @Autowired
    private PostRepository postRepository;

    public Post postFromId(Long id) {
        if (id == null) {
            return null;
        }
        return postRepository.findById(id).orElse(new Post());
    }
    public Long idFromPost(Post post) {
        return post.getId();
    }
}
