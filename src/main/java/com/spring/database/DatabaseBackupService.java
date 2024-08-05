package com.spring.database;

import com.spring.services.BaseDataServices;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Log4j2
public class DatabaseBackupService {


    private final String dbName = "archive";

    @Value("${spring.datasource.username}")
    private  String dbUser ;

    @Value("${spring.datasource.password}")
    private  String dbPassword ;

    private final BaseDataServices baseDataServices;

    public DatabaseBackupService(BaseDataServices baseDataServices) {
        this.baseDataServices = baseDataServices;
    }

    public String backupDatabase() throws IOException {
        removeOldBackups();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String backupFileName = baseDataServices.findBackupPath() + dbName + "_" + sdf.format(new Date()) + ".sql";
        String command = String.format("mysqldump -u%s -p%s %s -r %s", dbUser, dbPassword, dbName, backupFileName);
        Process process = Runtime.getRuntime().exec(command);
        try {
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                return "تم أخذ نسخة احتاطية بنجاح ";
            } else {
                return  "فشل في اخذ نسخة احتياطية ";
            }
        } catch (InterruptedException e) {
            throw new IOException("Backup interrupted", e);
        }
    }

    public String restoreDatabase() throws IOException {
        String backupFileName = getLastBackupFile();
        if (backupFileName == null) {
            return "لا توجد ملفات نسخ احتياطي للاسترجاع";
        }
        String backupFilePath = baseDataServices.findBackupPath() + backupFileName;
        String command = String.format("mysql -u%s -p%s %s < \"%s\"", dbUser, dbPassword, dbName, backupFilePath);
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                return "تم استرجاع النسخة الاحتياطية بنجاح";
            } else {
                return "فشل استرجاع النسخة " + output.toString();
            }
        } catch (InterruptedException e) {
            throw new IOException("Restore interrupted", e);
        }
    }



    public String getLastBackupFile() {
        try {
            return Files.list(Paths.get(baseDataServices.findBackupPath()))
                    .filter(f -> f.toString().endsWith(".sql"))
                    .map(f -> f.getFileName().toString()).min((f1, f2) -> -f1.compareTo(f2))
                    .orElse(null);
        } catch (IOException e) {
            return null;
        }
    }
    private void removeOldBackups() throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(baseDataServices.findBackupPath()), "*.sql")) {
            for (Path entry : stream) {
                Files.delete(entry);
            }
        }
    }
}