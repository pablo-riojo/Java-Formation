package com.block11.block11uploaddownloadfiles.file.application;

import com.block11.block11uploaddownloadfiles.file.domain.File;
import com.block11.block11uploaddownloadfiles.file.infrastructure.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

@Service
public class FileDBSvcImpl implements FileDBSvc {
    @Autowired
    FileRepository fileRepository;

    @Override
    public File store(MultipartFile fileInput, Path root) {
        File file = new File(
                StringUtils.cleanPath(
                        Objects.requireNonNull(fileInput.getOriginalFilename())
                ),
                fileInput.getContentType(),
                root.toString()
        );

        return fileRepository.save(file);
    }

    @Override
    public File getFileById(Long id) {
        return fileRepository.getReferenceById(id);
    }

    @Override
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public void deleteFile(Long id) {
        fileRepository.delete(
                fileRepository.getReferenceById(id)
        );
    }
}
