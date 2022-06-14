package com.example.zmusic.service;

import com.example.zmusic.entity.Role;
import com.example.zmusic.entity.User;
import com.example.zmusic.enums.Gender;
import com.example.zmusic.repository.RoleRepository;
import com.example.zmusic.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootTest
@Slf4j
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void test_createUser() {
        Role role1 = Role.builder().title("ADMIN").name("ADMIN").build();
        Role saveRole1 = roleRepository.save(role1);
        Role role2 = Role.builder().title("USER").name("USER").build();
        Role saveRole2 = roleRepository.save(role2);
        Role role3 = Role.builder().title("SMALL_ADMIN").name("SMALL_ADMIN").build();
        Role saveRole3 = roleRepository.save(role3);

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

        user.setRoles(Arrays.asList(
                saveRole1
        ));
        User save1 = userRepository.save(user);
        log.info(save1.toString());


        user.setRoles(Arrays.asList(
                saveRole1, saveRole2, saveRole3
        ));

        User save2 = userRepository.save(user);
        log.info(save2.toString());
    }

}
