package com.example.zmusic.repository;

import com.example.zmusic.entity.File;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FileRepository
    extends JpaRepository<File, String>, JpaSpecificationExecutor<File> {
  Optional<File> findFirstByFileKey(String fileKey);
}
