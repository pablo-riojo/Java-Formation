package com.block11.block11uploaddownloadfiles.file.infrastructure.controller;

import com.block11.block11uploaddownloadfiles.file.application.FileSvc;
import com.block11.block11uploaddownloadfiles.file.domain.File;
import com.block11.block11uploaddownloadfiles.file.exception.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileSvc fileSvc;

    @GetMapping("/files/name/{filename:.+}")
    public Resource getFileByName(@PathVariable String filename) {
        return fileSvc.loadByName(filename);
    }

    @GetMapping("/files/{id}")
    public Resource getFileById(@PathVariable Long id) {
        return fileSvc.loadById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public File uploadFiles(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
            return fileSvc.store(file, type);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(FileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}