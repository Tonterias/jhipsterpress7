package web.jhp7.web.service.mapper;

import web.jhp7.web.domain.*;
import web.jhp7.web.service.dto.MessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Message and its DTO MessageDTO.
 */
@Mapper(componentModel = "spring", uses = {CommunityMapper.class, ProfileMapper.class})
public interface MessageMapper extends EntityMapper<MessageDTO, Message> {

//    @Mapping(source = "community.id", target = "communityId")
//    @Mapping(source = "profile.id", target = "profileId")
    MessageDTO toDto(Message message);

//    @Mapping(source = "communityId", target = "community")
//    @Mapping(source = "profileId", target = "profile")
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "community", ignore = true)
    Message toEntity(MessageDTO messageDTO);

    default Message fromId(Long id) {
        if (id == null) {
            return null;
        }
        Message message = new Message();
        message.setId(id);
        return message;
    }
}
