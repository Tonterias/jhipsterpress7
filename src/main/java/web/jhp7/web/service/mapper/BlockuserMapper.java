package web.jhp7.web.service.mapper;

import web.jhp7.web.domain.*;
import web.jhp7.web.service.dto.BlockuserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Blockuser and its DTO BlockuserDTO.
 */
@Mapper(componentModel = "spring", uses = {CommunityMapper.class, ProfileMapper.class})
public interface BlockuserMapper extends EntityMapper<BlockuserDTO, Blockuser> {

    @Mapping(source = "cblockeduser.id", target = "cblockeduserId")
    @Mapping(source = "cblockinguser.id", target = "cblockinguserId")
    @Mapping(source = "blockeduser.id", target = "blockeduserId")
    @Mapping(source = "blockinguser.id", target = "blockinguserId")
    BlockuserDTO toDto(Blockuser blockuser);

    @Mapping(source = "cblockeduserId", target = "cblockeduser")
    @Mapping(source = "cblockinguserId", target = "cblockinguser")
    @Mapping(source = "blockeduserId", target = "blockeduser")
    @Mapping(source = "blockinguserId", target = "blockinguser")
    Blockuser toEntity(BlockuserDTO blockuserDTO);

    default Blockuser fromId(Long id) {
        if (id == null) {
            return null;
        }
        Blockuser blockuser = new Blockuser();
        blockuser.setId(id);
        return blockuser;
    }
}
