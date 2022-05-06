package com.company.Furniture.storage;

import com.company.Furniture.ApplicationController;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

public class FileSystemStorageService implements StorageService {
    private final String uploadPath;

    public FileSystemStorageService(String uploadPath){
        this.uploadPath = uploadPath;
    }

    private String createPath(File path, MultipartFile file){
        String uuidName = UUID.randomUUID().toString();
        return path.getAbsolutePath() + "/" + uuidName + "_" + file.getOriginalFilename();
    }

    @Override
    public String store(MultipartFile file) {
        File uploadPath = new File(this.uploadPath);
        if(!uploadPath.exists()){
            uploadPath.mkdir();
        }
        String fileName = createPath(uploadPath, file);
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + fileName);
            }
            if (fileName.contains("..")) {
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + fileName);
            }
            file.transferTo(new File(fileName));
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + fileName, e);
        }
        return fileName;

    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(new File(uploadPath));
    }

}
