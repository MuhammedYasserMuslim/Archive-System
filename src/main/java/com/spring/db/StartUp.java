package com.spring.db;

import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.entity.FinancialYear;
import com.spring.repository.FinancialYearRepository;
import com.spring.security.model.entity.AppUser;
import com.spring.security.model.entity.Authority;
import com.spring.security.services.AuthorityService;
import com.spring.security.services.UserServices;
import com.spring.services.ArchiveFileServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class StartUp implements CommandLineRunner, ApplicationRunner {

    private final UserServices userServices;
    private final AuthorityService authorityService;
    private final FinancialYearRepository financialYearRepository;
    private final ArchiveFileServices archiveFileServices;

    @Override
    public void run(String... args) throws ParseException {
        if (financialYearRepository.findAll().isEmpty()) {
            for (int i = 2000; i < 2100; i++) {
                String from = "1-7-".concat(String.valueOf(i)).concat(" 00:00:00");
                String to = "30-6-".concat(String.valueOf(i + 1)).concat(" 23:59:59");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                FinancialYear financialYear = new FinancialYear(i + "-" + (i + 1),
                        dateFormat.parse(from),
                        dateFormat.parse(to));
                financialYearRepository.save(financialYear);

            }
        }

        List<Authority> authorities = authorityService.findAll();
        if (userServices.findAll().isEmpty()) {
            AppUser user = new AppUser("admin", ("1234"), 1, "admin", "admin");
            user.getAuthorities().add(authorities.get(0));
            user.getAuthorities().add(authorities.get(1));
            user.getAuthorities().add(authorities.get(2));
            userServices.save(user, 1);
            AppUser user0 = new AppUser("manager", ("456"), 1, "manager", "manager");
            user0.getAuthorities().add(authorities.get(1));
            user0.getAuthorities().add(authorities.get(2));
            userServices.save(user0, 1);
            AppUser user1 = new AppUser("user", ("789"), 1, "user", "user");
            user1.getAuthorities().add(authorities.get(2));
            userServices.save(user1, 1);
        }
    }

    @Override
    public void run(ApplicationArguments args) {
        if (authorityService.findAll().isEmpty()) {
            authorityService.insert(new Authority((byte) 1, "ROLE_ADMIN"));
            authorityService.insert(new Authority((byte) 2, "ROLE_MANAGER"));
            authorityService.insert(new Authority((byte) 3, "ROLE_USER"));
        }
    }
}
