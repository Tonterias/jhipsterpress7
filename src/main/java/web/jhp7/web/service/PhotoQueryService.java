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

import web.jhp7.web.domain.Photo;
import web.jhp7.web.domain.*; // for static metamodels
import web.jhp7.web.repository.PhotoRepository;
import web.jhp7.web.service.dto.PhotoCriteria;

import web.jhp7.web.service.dto.PhotoDTO;
import web.jhp7.web.service.mapper.PhotoMapper;

/**
 * Service for executing complex queries for Photo entities in the database.
 * The main input is a {@link PhotoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PhotoDTO} or a {@link Page} of {@link PhotoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PhotoQueryService extends QueryService<Photo> {

    private final Logger log = LoggerFactory.getLogger(PhotoQueryService.class);

    private final PhotoRepository photoRepository;

    private final PhotoMapper photoMapper;

    public PhotoQueryService(PhotoRepository photoRepository, PhotoMapper photoMapper) {
        this.photoRepository = photoRepository;
        this.photoMapper = photoMapper;
    }

    /**
     * Return a {@link List} of {@link PhotoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PhotoDTO> findByCriteria(PhotoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Photo> specification = createSpecification(criteria);
        return photoMapper.toDto(photoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PhotoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PhotoDTO> findByCriteria(PhotoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Photo> specification = createSpecification(criteria);
        return photoRepository.findAll(specification, page)
            .map(photoMapper::toDto);
    }

    /**
     * Function to convert PhotoCriteria to a {@link Specification}
     */
    private Specification<Photo> createSpecification(PhotoCriteria criteria) {
        Specification<Photo> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Photo_.id));
            }
            if (criteria.getCreationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationDate(), Photo_.creationDate));
            }
            if (criteria.getAlbumId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getAlbumId(), Photo_.album, Album_.id));
            }
            if (criteria.getCalbumId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCalbumId(), Photo_.calbum, Calbum_.id));
            }
        }
        return specification;
    }

}
