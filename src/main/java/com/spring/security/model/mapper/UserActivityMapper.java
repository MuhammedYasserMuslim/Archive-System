package com.spring.security.model.mapper;

import com.spring.model.entity.*;
import com.spring.security.model.dto.UserActivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserActivityMapper {


    UserActivity mapToUserActivity(Export export);

    UserActivity mapToUserActivity(Import export);

    UserActivity mapToUserActivity(Special special);

    UserActivity mapToUserActivity(DeanDecisions decisions);

    UserActivity mapToUserActivity(IncomingSigns signs);

    @Mapping(target = "summary", source = "deputation", qualifiedByName = "nameToSummary")
    UserActivity mapToUserActivity(Deputation deputation);

    @Named("nameToSummary")
    static String nameToSummary(Deputation deputation) {
        return deputation.getName().concat(" إلي " +deputation.getDeputationUniversity());
    }
}
