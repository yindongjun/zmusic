package com.example.zmusic.repository;

import com.example.zmusic.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, String> {
    Optional<File> findFirstByFileKey(String fileKey);
}
