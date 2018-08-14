package web.jhp7.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import web.jhp7.web.domain.Post;
import web.jhp7.web.domain.*; // for static metamodels
import web.jhp7.web.repository.PostRepository;
import web.jhp7.web.service.dto.PostCriteria;

import web.jhp7.web.service.dto.PostDTO;
import web.jhp7.web.service.mapper.PostMapper;

/**
 * Service for executing complex queries for Post entities in the database.
 * The main input is a {@link PostCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PostDTO} or a {@link Page} of {@link PostDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PostQueryService extends QueryService<Post> {

    private final Logger log = LoggerFactory.getLogger(PostQueryService.class);

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    public PostQueryService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    /**
     * Return a {@link List} of {@link PostDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PostDTO> findByCriteria(PostCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Post> specification = createSpecification(criteria);
        return postMapper.toDto(postRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PostDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PostDTO> findByCriteria(PostCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Post> specification = createSpecification(criteria);
        return postRepository.findAll(specification, page)
            .map(postMapper::toDto);
    }

    /**
     * Function to convert PostCriteria to a {@link Specification}
     */
    private Specification<Post> createSpecification(PostCriteria criteria) {
        Specification<Post> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Post_.id));
            }
            if (criteria.getCreationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationDate(), Post_.creationDate));
            }
            if (criteria.getPublicationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPublicationDate(), Post_.publicationDate));
            }
            if (criteria.getHeadline() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHeadline(), Post_.headline));
            }
            if (criteria.getLeadtext() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLeadtext(), Post_.leadtext));
            }
            if (criteria.getBodytext() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBodytext(), Post_.bodytext));
            }
            if (criteria.getQuote() != null) {
                specification = specification.and(buildStringSpecification(criteria.getQuote(), Post_.quote));
            }
            if (criteria.getConclusion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConclusion(), Post_.conclusion));
            }
            if (criteria.getCommentId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCommentId(), Post_.comments, Comment_.id));
            }
            if (criteria.getUrllinkId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getUrllinkId(), Post_.urllink, Urllink_.id));
            }
            if (criteria.getBlogId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getBlogId(), Post_.blog, Blog_.id));
            }
            if (criteria.getProfileId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getProfileId(), Post_.profile, Profile_.id));
            }
            if (criteria.getTagId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTagId(), Post_.tags, Tag_.id));
            }
            if (criteria.getTopicId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTopicId(), Post_.topics, Topic_.id));
            }
        }
        return specification;
    }

}
