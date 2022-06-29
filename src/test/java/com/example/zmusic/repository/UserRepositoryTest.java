package com.example.zmusic.repository;

import com.example.zmusic.dto.RoleDto;
import com.example.zmusic.entity.User;
import com.example.zmusic.enums.Gender;
import com.example.zmusic.mapper.RoleMapper;
import com.example.zmusic.request.RoleCreateRequest;
import com.example.zmusic.service.RoleService;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

  @Autowired UserRepository userRepository;

  @Autowired RoleService roleService;

  @Autowired RoleMapper roleMapper;

  @Test
  void findByUsername() {
    User userToSave =
        User.builder()
            .username("jzheng")
            .nickname("jzheng")
            .gender(Gender.MALE)
            .password("123456")
            .lastLoginIp("127.0.0.1")
            .lastLoginTime(LocalDateTime.now())
            .locked(false)
            .enabled(true)
            .build();

    userRepository.save(userToSave);

    User foundUser = userRepository.findByUsername("jzheng").get();
    System.out.println(foundUser);
  }

  @Test
  public void test_deleteRole() {
    RoleCreateRequest roleCreateRequest = new RoleCreateRequest();
    roleCreateRequest.setName("123123");
    roleCreateRequest.setTitle("123123");
    RoleDto roleDto = roleService.create(roleMapper.toDto(roleCreateRequest));

    User userToSave =
        User.builder()
            .username("jzheng")
            .nickname("jzheng")
            .gender(Gender.MALE)
            .password("123456")
            .lastLoginIp("127.0.0.1")
            .lastLoginTime(LocalDateTime.now())
            .locked(false)
            .enabled(true)
            .roles(List.of(roleMapper.toEntity(roleDto)))
            .build();

    userRepository.save(userToSave);

    roleService.delete(roleDto.getId());
  }
}
