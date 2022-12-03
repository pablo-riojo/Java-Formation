package com.block11.block11uploaddownloadfiles.file.infrastructure.repository;

import com.block11.block11uploaddownloadfiles.file.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
