package com.example.zmusic.repository;

import com.example.zmusic.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository
    extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {
  Optional<Role> findFirstByName(String name);
}
