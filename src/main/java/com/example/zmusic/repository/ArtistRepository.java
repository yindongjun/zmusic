package com.example.zmusic.repository;

import com.example.zmusic.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArtistRepository
        extends JpaRepository<Artist, String>, JpaSpecificationExecutor<Artist> {

}
