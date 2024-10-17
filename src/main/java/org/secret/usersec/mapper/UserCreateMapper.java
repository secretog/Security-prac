package org.secret.usersec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.secret.usersec.database.entity.User;
import org.secret.usersec.dto.UserCreateDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserCreateMapper {
    UserCreateDto toDto(User user);
    User toEntity(UserCreateDto userCreateDto);
}
