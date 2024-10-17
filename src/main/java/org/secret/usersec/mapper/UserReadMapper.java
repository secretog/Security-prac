package org.secret.usersec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.secret.usersec.database.entity.User;
import org.secret.usersec.dto.UserReadDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserReadMapper {
    UserReadDto toDto(User user);
    User toEntity(UserReadDto userReadDto);
}
