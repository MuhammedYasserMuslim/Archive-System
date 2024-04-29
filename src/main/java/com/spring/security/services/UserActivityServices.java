package com.spring.security.services;

import com.spring.model.entity.*;
import com.spring.repository.*;
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
    private final DeanDecisionsRepository deanDecisionsRepository;
    private final IncomingSignsRepository incomingSignsRepository;
    private final UserActivityMapper userActivityMapper;


    /**
     * @return userActivity
     */
    public List<UserActivity> findUsersActivity() {
        List<UserActivity> activities = new ArrayList<>();
        for (Export export : exportRepository.findByYear()) {
            UserActivity userActivity = userActivityMapper.mapToUserActivity(export);
            userActivity.setType("صادر");
            activities.add(userActivity);
        }
        for (Import imports : importRepository.findByYear()) {
            UserActivity userActivity = userActivityMapper.mapToUserActivity(imports);
            userActivity.setType("وارد");
            activities.add(userActivity);
        }
        for (Special special : specialRepository.findByYear()) {
            UserActivity userActivity = userActivityMapper.mapToUserActivity(special);
            userActivity.setType("خاص");
            activities.add(userActivity);
        }
        for (DeanDecisions decisions : deanDecisionsRepository.findByYear()) {
            UserActivity userActivity = userActivityMapper.mapToUserActivity(decisions);
            userActivity.setType("قرارت عميد الكلية");
            activities.add(userActivity);
        }
        for (IncomingSigns signs : incomingSignsRepository.findAll()) {
            UserActivity userActivity = userActivityMapper.mapToUserActivity(signs);
            userActivity.setType("أشارات");
            activities.add(userActivity);
        }

        activities.sort(Comparator.comparing(UserActivity::getLastModifiedDate).reversed());

        return activities;
    }
}

