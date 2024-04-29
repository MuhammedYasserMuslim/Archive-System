package com.spring.security.model.mapper;

import com.spring.model.entity.*;
import com.spring.security.model.dto.UserActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserActivityMapper {


   UserActivity mapToUserActivity (Export export);
   UserActivity mapToUserActivity (Import export);
   UserActivity mapToUserActivity (Special special);
   UserActivity mapToUserActivity (DeanDecisions decisions);
   UserActivity mapToUserActivity (IncomingSigns signs);
}
