package com.spring.services;


import com.spring.exception.FileStorageException;
import com.spring.exception.RecordNotFountException;
import com.spring.model.entity.Export;
import com.spring.model.entity.Image;
import com.spring.model.entity.Import;
import com.spring.model.entity.Special;
import com.spring.repository.ImageRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
@Getter
public class FileUploadService {

    private Path fileStorageLocation;

    private final ImageRepository imageRepository;
    private final String basePath = "E:\\ArchiveSystem\\Front_End\\Archive\\src\\assets\\";

    public String storeFile(File file, int id, String pathType) {

        // create uploaded path
        this.fileStorageLocation = Paths.get(basePath + pathType).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
                    ex);
        }
        String fileName = StringUtils.cleanPath(id + "-" + file.getName());
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            InputStream targetStream = new FileInputStream(file);
            Files.copy(targetStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            updateImagePath(id, pathType, pathType + "\\" + fileName, file);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            log.error("Error converting the multi-part file to file= ", ex.getMessage());
        }
        return file;
    }

    private void updateImagePath(int id, String pathType, String imagePath, File file) {

        if (pathType.contains("imports") || pathType.contains("exports") || pathType.contains("specials")) {
            Image image = new Image();
            image.setImagePath("assets\\".concat(imagePath));
            image.setName(file.getName());
            if (pathType.equals("imports"))
                image.setAnImport(new Import(id));
            if (pathType.equals("exports"))
                image.setExport(new Export(id));
            if (pathType.equals("specials"))
                image.setSpecial(new Special(id));
            imageRepository.save(image);

        }
    }

    public byte[] getFileFromFileSystem(String name) throws IOException {
        Optional<Image> image = imageRepository.findByName(name);
        String filePath = basePath + image.get().getImagePath();
        return Files.readAllBytes(new File(filePath).toPath());
    }


    public void deleteByImagePath(String imagePath) {
        if (imageRepository.findByImagePath(imagePath).isPresent())
            imageRepository.deleteByImagePath(imagePath);
        else
            throw new RecordNotFountException("invalid path");
    }

    public Image findByImagePath(String imagePath) {
        if (imageRepository.findByImagePath(imagePath).isPresent())
            return imageRepository.findByImagePath(imagePath).get();
        else
            throw new RecordNotFountException("invalid path");
    }

}
