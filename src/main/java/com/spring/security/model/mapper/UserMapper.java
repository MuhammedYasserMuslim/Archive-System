package com.spring.security.model.mapper;

import com.spring.security.model.dto.UserDto;
import com.spring.security.model.entity.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToDto(AppUser user);
    AppUser mapToEntity(UserDto dto);
}
