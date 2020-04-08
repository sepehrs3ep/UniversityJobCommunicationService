package ir.khu.jaobshaar.utils;

import ir.khu.jaobshaar.utils.validation.ErrorCodes;
import ir.khu.jaobshaar.utils.validation.ResponseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private Path fileStorageLocation;

    public String storeFile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString();
            Path targetLocation = this.fileStorageLocation.resolve(fileName + ".pdf");
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String replaceFile(MultipartFile file, String oldResume) {
        try {
            loadFileAsResource(oldResume).getFile().delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storeFile(file);
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName + ".pdf");
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new ResponseException(ErrorCodes.ERROR_CODE_RESUME_IS_NOT_EXIST, "resume.not.find");
            }
        } catch (MalformedURLException ex) {
            throw new ResponseException(ErrorCodes.ERROR_CODE_MALFORMED_URL_EXCEPTION, "");
        }
    }
}
