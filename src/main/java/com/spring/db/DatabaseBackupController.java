package com.spring.db;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/database")
@RequiredArgsConstructor
public class DatabaseBackupController {


    private final DatabaseBackupService backupService;

    @PostMapping("/backup")
    public String backupDatabase() {
        try {
            return backupService.backupDatabase();
        } catch (IOException e) {
            return "Backup failed: " + e.getMessage();
        }
    }

    @PostMapping("/restore")
    public String restoreDatabase() {
        try {
            String lastBackupFile = backupService.getLastBackupFile();
            if (lastBackupFile != null) {
                return backupService.restoreDatabase(lastBackupFile);
            } else {
                return "لا يوجد اي نسخة أحتياطية";
            }
        } catch (IOException e) {
            return "Restore failed: " + e.getMessage();
        }
    }
}
