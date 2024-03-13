package com.spring.security.services;

import com.spring.model.entity.Export;
import com.spring.model.entity.Import;
import com.spring.model.entity.Special;
import com.spring.repository.ExportRepository;
import com.spring.repository.ImportRepository;
import com.spring.repository.SpecialRepository;
import com.spring.security.model.dto.UserActivity;
import com.spring.security.model.mapper.UserActivityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserActivityServices {

    private final ExportRepository exportRepository;
    private final ImportRepository importRepository;
    private final SpecialRepository specialRepository;
    private final UserActivityMapper userActivityMapper;


    /**
     * @return userActivity
     */
    public List<UserActivity> findUsersActivity() {
        List<UserActivity> activities = new ArrayList<>();
        for (Export export : exportRepository.findAll()) {
            UserActivity userActivity = userActivityMapper.mapToUserActivity(export);
            userActivity.setType("صادر");
            activities.add(userActivity);
        }
        for (Import imports : importRepository.findAll()) {
            UserActivity userActivity = userActivityMapper.mapToUserActivity(imports);
            userActivity.setType("وارد");
            activities.add(userActivity);
        }
        for (Special special : specialRepository.findAll()) {
            UserActivity userActivity = userActivityMapper.mapToUserActivity(special);
            userActivity.setType("خاص");
            activities.add(userActivity);
        }

        activities.sort(Comparator.comparing(UserActivity::getLastModifiedDate).reversed());

        return activities;
    }
}

