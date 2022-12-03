package com.block11.block11uploaddownloadfiles.file.application;

import com.block11.block11uploaddownloadfiles.file.domain.File;
import com.block11.block11uploaddownloadfiles.file.exception.StorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@Slf4j
public class FileSvcImpl implements FileSvc {
    private final Path root = Paths.get("storage");

    @Autowired
    FileDBSvc fileDBSvc;

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Error initiating storage" + e);
        }
    }

    @Override
    public File store(MultipartFile file, String type) {
        String extension = StringUtils
                .cleanPath(Objects.requireNonNull(file.getContentType()))
                .split("/")[1];

        if (extension.equals(type)) {
            try{
                Files.copy(
                        file.getInputStream(),
                        this.root.resolve(Objects.requireNonNull(file.getOriginalFilename()))
                        );
            } catch (IOException e) {
                throw new StorageException("Could not store the file: " + e.getMessage());
            }

            return fileDBSvc.store(file, root);
        }

        throw new StorageException("Category file doesn't match " + type );
    }

    @Override
    public Resource loadByName(String filename) {
        Resource resource;

        try {
            Path file = root.resolve(filename);
            resource = new UrlResource((file.toUri()));
        } catch (MalformedURLException e) {
            throw new StorageException(e.getMessage());
        }

        if (!resource.exists() || !resource.isReadable()) throw new StorageException("Cannot read the file: ");

        return resource;
    }

    @Override
    public Resource loadById(Long id) {
        Resource resource;

        try {
            String filename = fileDBSvc.getFileById(id).getName();
            Path file = root.resolve(filename);

            resource = new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            throw new StorageException(e.getMessage());
        }

        if (!resource.exists() || !resource.isReadable()) throw new StorageException("Cannot read the file: ");

        return resource;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }
}
