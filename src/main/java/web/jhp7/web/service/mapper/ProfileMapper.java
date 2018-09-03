package web.jhp7.web.service.mapper;

import web.jhp7.web.domain.*;
import web.jhp7.web.service.dto.ProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Profile and its DTO ProfileDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CustomInterestMapper.class, CustomActivityMapper.class, CustomCelebMapper.class})
public interface ProfileMapper extends EntityMapper<ProfileDTO, Profile> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
//    @Mapping(source = "interest.interestName", target = "profileInterests")
//    @Mapping(source = "activity.activityName", target = "profileActivities")
//    @Mapping(source = "celeb.celebName", target = "profileCelebs")
    ProfileDTO toDto(Profile profile);

//    @Mapping(target = "user", ignore = true)
    @Mapping(source = "userId", target = "user")
//    @Mapping(source = "profileInterests", target = "interest.interestName")
//    @Mapping(source = "profileActivities", target = "activity")
//    @Mapping(source = "profileCelebs", target = "celeb.celebName")
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
