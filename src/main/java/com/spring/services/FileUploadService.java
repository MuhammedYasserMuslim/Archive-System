package com.spring.services;


import com.spring.exception.FileStorageException;
import com.spring.exception.IOEException;
import com.spring.exception.RecordNotFountException;
import com.spring.model.entity.*;
import com.spring.repository.ImageRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Getter
public class FileUploadService {

    private Path fileStorageLocation;

    private final ImageRepository imageRepository;

    private final BaseDataServices baseDataServices;

    /**
     * @param id,file,pathType store file in Image Entity
     * @return file name
     */
    public String storeFile(File file, int id, String pathType) {
        this.fileStorageLocation = Paths.get(baseDataServices.findImagePath() + pathType).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
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
     * @param multipartFile convert to file
     * @return File
     */
    public File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            throw new IOEException("Error converting the multi-part file to file ");
        }
        return file;
    }

    /**
     * @param id,pathType,imagePath,file used to save file in folders in file system
     */
    private void updateImagePath(int id, String pathType, String imagePath, File file) {

        if (pathType.equals("imports") || pathType.equals("exports") || pathType.equals("specials") || pathType.equals("decisions")) {
            Image image = new Image();
            image.setImagePath("images\\".concat(imagePath));
            image.setName(id + "-" + pathType + "-" + file.getName());
            if (pathType.equals("imports"))
                image.setAnImport(new Import(id));
            if (pathType.equals("exports"))
                image.setExport(new Export(id));
            if (pathType.equals("specials"))
                image.setSpecial(new Special(id));
            if (pathType.equals("decisions"))
                image.setDeanDecisions(new DeanDecisions(id));
            if (imageRepository.findByImagePath(image.getImagePath()).isEmpty())
                imageRepository.save(image);

        }
    }

    /**
     * @param name find file from file system by
     * @throws IOException used to get file from file system
     */

    public byte[] getFileFromFileSystem(String name) throws IOException {
        Image image = imageRepository.findByName(name).orElseThrow();
        String filePath = baseDataServices.findImagePath() + image.getImagePath();
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
     * @param imagePath find image by imagePath
     * @return Image
     */
    public Image findByImagePath(String imagePath) {
        if (imageRepository.findByImagePath(imagePath).isPresent())
            return imageRepository.findByImagePath(imagePath).get();
        else
            throw new RecordNotFountException("invalid path");
    }

    /**
     * @param specialId to add image
     * @param importId to find import file by
     * used to add images in import file to special file
     */
    void convertImageImport(int specialId, int importId) {
        imageRepository.convertImageImport(specialId, importId);
    }

    /**
     * @param specialId to add image
     * @param exportId to find export file by
     * used to add images in export file to special file
     */
    void convertImageExport(int specialId, int exportId) {
        imageRepository.convertImageExport(specialId, exportId);
    }

}
