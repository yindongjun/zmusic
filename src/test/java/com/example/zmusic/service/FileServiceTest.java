package com.example.zmusic.service;

import cn.hutool.core.io.FileUtil;
import com.example.zmusic.dto.FileDto;
import com.example.zmusic.dto.UserDto;
import com.example.zmusic.enums.Gender;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.mapper.UserMapper;
import com.example.zmusic.request.UserCreateRequest;
import com.google.common.collect.Lists;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
@Slf4j
class FileServiceTest {

  @Autowired private FileService fileService;

  @Autowired private UserService userService;

  @Value("${app.upload.path}")
  private String uploadDir;

  private UserDto createUserDto;

  @Autowired private UserMapper userMapper;

  @BeforeEach
  public void init() {
    // mock create user
    try {
      createUserDto = userService.getByUsername("admin");
    } catch (Exception e) {
      UserCreateRequest userCreateRequest = new UserCreateRequest();
      userCreateRequest.setUsername("admin");
      userCreateRequest.setPassword("admin");
      userCreateRequest.setNickname("admin");
      userCreateRequest.setGender(Gender.MALE.name());

      createUserDto = userService.create(userMapper.toDto(userCreateRequest));
    }

    // mock security authentication
    List<SimpleGrantedAuthority> grantedAuthorities =
        Lists.newArrayList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken("admin", null, grantedAuthorities);

    UserDto admin = userService.getByUsername("admin");
    authentication.setDetails(admin);
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  @Test
  public void test_get() {
    try {
      BufferedInputStream inputStream =
          FileUtil.getInputStream("/Users/jiangzheng/Desktop/links.txt");
      MultipartFile mockFile = new MockMultipartFile("links.txt", inputStream);
      FileDto fileDto = fileService.create(mockFile);
      Assertions.assertEquals("txt", fileDto.getExt());
      Assertions.assertEquals("links.txt", fileDto.getName());

      FileDto findFileDto = fileService.get(fileDto.getId());
      Assertions.assertNotNull(findFileDto);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void test_search() {
    try {
      BufferedInputStream inputStream =
          FileUtil.getInputStream("/Users/jiangzheng/Desktop/links.txt");
      MultipartFile mockFile = new MockMultipartFile("links.txt", inputStream);
      FileDto fileDto = fileService.create(mockFile);
      Assertions.assertEquals("txt", fileDto.getExt());
      Assertions.assertEquals("links.txt", fileDto.getName());
    } catch (IOException e) {
      e.printStackTrace();
    }

    Page<FileDto> page = fileService.search(PageRequest.of(0, 10));
    Assertions.assertNotEquals(0, page.getTotalElements());
  }

  @Test
  public void test_create() {
    try {
      BufferedInputStream inputStream =
          FileUtil.getInputStream("/Users/jiangzheng/Desktop/links.txt");
      MultipartFile mockFile = new MockMultipartFile("links.txt", inputStream);
      FileDto fileDto = fileService.create(mockFile);
      Assertions.assertEquals("txt", fileDto.getExt());
      Assertions.assertEquals("links.txt", fileDto.getName());

      FileDto findFileDto = fileService.getByFileKey(fileDto.getFileKey());
      Assertions.assertNotNull(findFileDto);
      Assertions.assertEquals(fileDto.getName(), findFileDto.getName());
      Assertions.assertNotNull(findFileDto.getCreateUser());
      Assertions.assertEquals(
          findFileDto.getCreateUser().getUsername(), createUserDto.getUsername());

      log.info(fileDto.toString());
      log.info(findFileDto.toString());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void test_delete() {
    try {
      // 新增文件
      BufferedInputStream inputStream =
          FileUtil.getInputStream("/Users/jiangzheng/Desktop/links.txt");
      MultipartFile mockFile = new MockMultipartFile("links.txt", inputStream);
      FileDto fileDto = fileService.create(mockFile);
      Assertions.assertEquals("txt", fileDto.getExt());
      Assertions.assertEquals("links.txt", fileDto.getName());

      FileDto findFileDto = fileService.getByFileKey(fileDto.getFileKey());
      Assertions.assertNotNull(findFileDto);
      Assertions.assertEquals(fileDto.getName(), findFileDto.getName());

      log.info(fileDto.toString());
      log.info(findFileDto.toString());

      // 删除文件
      fileService.delete(fileDto.getId());

      // 检察数据库中是否存在文件
      Assertions.assertThrows(BizException.class, () -> fileService.get(fileDto.getId()));
      Assertions.assertThrows(
          BizException.class, () -> fileService.getByFileKey(fileDto.getFileKey()));

      // 判断文件是否存在
      String filePath =
          uploadDir
              + "/"
              + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
              + "/"
              + fileDto.getFileKey()
              + "."
              + fileDto.getExt();
      Assertions.assertFalse(FileUtil.exist(filePath));

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void test_update() {
    try {
      // 创建文件
      BufferedInputStream inputStream =
          FileUtil.getInputStream("/Users/jiangzheng/Desktop/links.txt");
      MultipartFile mockFile = new MockMultipartFile("links.txt", inputStream);
      FileDto fileDto = fileService.create(mockFile);
      Assertions.assertEquals("txt", fileDto.getExt());
      Assertions.assertEquals("links.txt", fileDto.getName());

      FileDto findFileDto = fileService.getByFileKey(fileDto.getFileKey());
      Assertions.assertNotNull(findFileDto);
      Assertions.assertEquals(fileDto.getName(), findFileDto.getName());

      // 更新文件
      BufferedInputStream updateFileIs =
          FileUtil.getInputStream("/Users/jiangzheng/Desktop/vue.js");
      MultipartFile updateMockFile = new MockMultipartFile("vue.js", updateFileIs);
      FileDto updatedFileDto = fileService.update(fileDto.getId(), updateMockFile);

      Assertions.assertEquals("vue.js", updatedFileDto.getName());
      Assertions.assertEquals("js", FileUtil.getSuffix(updatedFileDto.getFileKey()));
      Assertions.assertNotEquals(findFileDto.getFileKey(), updatedFileDto.getFileKey());

      Assertions.assertNotNull(updatedFileDto.getUpdateUser());
      Assertions.assertEquals(
          updatedFileDto.getUpdateUser().getUsername(), createUserDto.getUsername());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
