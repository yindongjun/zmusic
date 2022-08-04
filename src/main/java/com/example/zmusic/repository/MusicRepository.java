package com.example.zmusic.repository;

import com.example.zmusic.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MusicRepository
        extends JpaRepository<Music, String>, JpaSpecificationExecutor<Music> {

}
