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
import org.springframework.transaction.annotation.Transactional;
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

    private final BaseDataServices baseDataServices;

    /**
     * @param id,file,pathType
     * @return file name
     * used to store file in Image Entity
     */
    public String storeFile(File file, int id, String pathType) {
        this.fileStorageLocation = Paths.get(baseDataServices.findBaseData() + pathType).toAbsolutePath().normalize();
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

    /**
     * @param multipartFile
     * @return File
     * used to convert MultipartFile to File
     * @author mohamed yasser
     */
    public File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            log.error("Error converting the multi-part file to file= ", ex.getMessage());
        }
        return file;
    }

    /**
     * @param id,pathType,imagePath,file used to save file in folders in file system
     */
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
            if (imageRepository.findByImagePath(image.getImagePath()).isPresent())
                throw new FileStorageException("This file is already exist");
            else
                imageRepository.save(image);

        }
    }

    /**
     * @param name
     * @throws IOException used to get file from file system
     */

    public byte[] getFileFromFileSystem(String name) throws IOException {
        Optional<Image> image = imageRepository.findByName(name);
        String filePath = baseDataServices.findBaseData() + image.get().getImagePath();
        return Files.readAllBytes(new File(filePath).toPath());
    }


    /**
     * @param imagePath used to delete image by imagePath
     */
    @Transactional
    public void deleteByImagePath(String imagePath) {
        if (imageRepository.findByImagePath(imagePath).isPresent())
            imageRepository.deleteByImagePath(imagePath);
        else
            throw new RecordNotFountException("invalid path");
    }

    /**
     * @param imagePath used to find image by imagePath
     * @return Image
     */
    public Image findByImagePath(String imagePath) {
        if (imageRepository.findByImagePath(imagePath).isPresent())
            return imageRepository.findByImagePath(imagePath).get();
        else
            throw new RecordNotFountException("invalid path");
    }


}
