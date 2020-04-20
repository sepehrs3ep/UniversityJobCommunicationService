package ir.khu.jaobshaar.service.mapper;

import ir.khu.jaobshaar.entity.enums.PersonRuleTypeConverter;
import ir.khu.jaobshaar.entity.model.User;
import ir.khu.jaobshaar.service.domain.UserDomain;
import ir.khu.jaobshaar.service.dto.user.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = PersonRuleTypeConverter.class)
public interface UserMapper extends EntityMapperBase<UserDTO, UserDomain, User> {
    @Override
    default User createNew() {
        return new User();
    }

}
