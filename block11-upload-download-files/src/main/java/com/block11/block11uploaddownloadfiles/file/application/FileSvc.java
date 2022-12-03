package com.block11.block11uploaddownloadfiles.file.application;

import com.block11.block11uploaddownloadfiles.file.domain.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface FileSvc {
    void init();
    File store(MultipartFile file, String type);
    Resource loadByName(String filename);
    Resource loadById(Long id);
    void deleteAll();
}