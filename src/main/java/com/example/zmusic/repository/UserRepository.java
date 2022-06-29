package com.example.zmusic.repository;

import com.example.zmusic.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository
    extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
  Optional<User> findByUsername(String username);
}
