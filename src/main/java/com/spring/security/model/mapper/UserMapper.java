package com.spring.security.model.mapper;

import com.spring.security.model.dto.UserRequest;
import com.spring.security.model.dto.UserResponse;
import com.spring.security.model.entity.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse mapToDto(AppUser user);
    AppUser mapToEntity(UserRequest dto);
}
