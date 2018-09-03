package web.jhp7.web.service.mapper;

import org.mapstruct.Mapper;

import web.jhp7.web.domain.Activity;
import web.jhp7.web.service.dto.CustomActivityDTO;

/**
 * Mapper for the entity Activity and its DTO ActivityDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomActivityMapper extends EntityMapper<CustomActivityDTO, Activity> {



    default Activity fromId(Long id) {
        if (id == null) {
            return null;
        }
        Activity activity = new Activity();
        activity.setId(id);
        return activity;
    }
}
