package com.spring.database;

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
public class DatabaseBackupService {

//    private  final String backupDirectory = "D:\\Archive_System\\backup\\";

    private  final String backupDirectory = "/home/muhammed/Desktop/Folder/";

    private final String dbName = "archive";

    @Value("${spring.datasource.username}")
    private  String dbUser ;

    @Value("${spring.datasource.password}")
    private  String dbPassword ;

    public String backupDatabase() throws IOException {
        removeOldBackups();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String backupFileName = backupDirectory + dbName + "_" + sdf.format(new Date()) + ".sql";

        String command = String.format("mysqldump -u%s -p%s %s -r %s", dbUser, dbPassword, dbName, backupFileName);

        Process process = Runtime.getRuntime().exec(command);

        try {
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                return "تم أخذ نسخة احتاطية بنجاح ";
            } else {
                return "فشل في اخذ نسخة احتياطية ";
            }
        } catch (InterruptedException e) {
            throw new IOException("Backup interrupted", e);
        }
    }

    public String restoreDatabase(String backupFileName) throws IOException {
        String command = String.format("mysql -u%s -p%s %s < %s", dbUser, dbPassword, dbName, backupDirectory + backupFileName);

        try {
            Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", command});

            int processComplete = process.waitFor();

            if (processComplete == 0) {
                return "تم استرجاع النسخة الاحتاطية بنجاح ";
            } else {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                    StringBuilder errorMsg = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        errorMsg.append(line).append(System.lineSeparator());
                    }
                    return "فشل استرجاع النسخة  " + errorMsg.toString();
                }
            }
        } catch (InterruptedException e) {
            throw new IOException("Restore interrupted", e);
        }
    }

    public String getLastBackupFile() {
        try {
            return Files.list(Paths.get(backupDirectory))
                    .filter(f -> f.toString().endsWith(".sql"))
                    .map(f -> f.getFileName().toString()).min((f1, f2) -> -f1.compareTo(f2))
                    .orElse(null);
        } catch (IOException e) {
            return null;
        }
    }
    private void removeOldBackups() throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(backupDirectory), "*.sql")) {
            for (Path entry : stream) {
                Files.delete(entry);
            }
        }
    }
}