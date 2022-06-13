package com.example.zmusic.service;

import com.example.zmusic.entity.Role;
import com.example.zmusic.entity.User;
import com.example.zmusic.enums.Gender;
import com.example.zmusic.repository.RoleRepository;
import com.example.zmusic.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void test_createUser() {
        Role role = Role.builder().title("ADMIN").name("ADMIN").build();
        Role saveRole = roleRepository.save(role);

        User user = User.builder()
                .username("jzheng")
                .nickname("zheng")
                .password("123456")
                .gender(Gender.MALE)
                .locked(false)
                .enabled(true)
                .lastLoginTime(LocalDateTime.now())
                .lastLoginIp("127.0.0.1")
                .build();
        userRepository.save(user);


        user.setRoles(Arrays.asList(
                saveRole
        ));

        userRepository.save(user);
    }

}
