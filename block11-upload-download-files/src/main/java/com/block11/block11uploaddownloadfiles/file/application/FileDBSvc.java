package com.block11.block11uploaddownloadfiles.file.application;

import com.block11.block11uploaddownloadfiles.file.domain.File;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface FileDBSvc {
    File store(MultipartFile file, Path root);
    File getFileById(Long id);
    List<File> getAllFiles();
    void deleteFile(Long id);
}
