package com.example.zmusic.service;

import cn.hutool.core.io.FileUtil;
import com.example.zmusic.dto.FileDto;
import com.example.zmusic.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@Slf4j
class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Value("${app.upload.path}")
    private String uploadDir;

    @Test
    public void test_get() {
        try {
            BufferedInputStream inputStream = FileUtil.getInputStream("/Users/jiangzheng/Desktop/links.txt");
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
            BufferedInputStream inputStream = FileUtil.getInputStream("/Users/jiangzheng/Desktop/links.txt");
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
            BufferedInputStream inputStream = FileUtil.getInputStream("/Users/jiangzheng/Desktop/links.txt");
            MultipartFile mockFile = new MockMultipartFile("links.txt", inputStream);
            FileDto fileDto = fileService.create(mockFile);
            Assertions.assertEquals("txt", fileDto.getExt());
            Assertions.assertEquals("links.txt", fileDto.getName());

            FileDto findFileDto = fileService.getByFileKey(fileDto.getFileKey());
            Assertions.assertNotNull(findFileDto);
            Assertions.assertEquals(fileDto.getName(), findFileDto.getName());

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
            BufferedInputStream inputStream = FileUtil.getInputStream("/Users/jiangzheng/Desktop/links.txt");
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
            fileService.deleteById(fileDto.getId());

            // 检察数据库中是否存在文件
            Assertions.assertThrows(BizException.class, () -> fileService.get(fileDto.getId()));
            Assertions.assertThrows(BizException.class, () -> fileService.getByFileKey(fileDto.getFileKey()));

            // 判断文件是否存在
            String filePath = uploadDir + "/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + "/" + fileDto.getFileKey() + "." + fileDto.getExt();
            Assertions.assertFalse(FileUtil.exist(filePath));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_update() {
        try {
            // 创建文件
            BufferedInputStream inputStream = FileUtil.getInputStream("/Users/jiangzheng/Desktop/links.txt");
            MultipartFile mockFile = new MockMultipartFile("links.txt", inputStream);
            FileDto fileDto = fileService.create(mockFile);
            Assertions.assertEquals("txt", fileDto.getExt());
            Assertions.assertEquals("links.txt", fileDto.getName());

            FileDto findFileDto = fileService.getByFileKey(fileDto.getFileKey());
            Assertions.assertNotNull(findFileDto);
            Assertions.assertEquals(fileDto.getName(), findFileDto.getName());

            // 更新文件
            BufferedInputStream updateFileIs = FileUtil.getInputStream("/Users/jiangzheng/Desktop/vue.js");
            MultipartFile updateMockFile = new MockMultipartFile("vue.js", updateFileIs);
            FileDto updatedFileDto = fileService.update(fileDto.getId(), updateMockFile);

            Assertions.assertEquals("vue.js", updatedFileDto.getName());
            Assertions.assertEquals("js", FileUtil.getSuffix(updatedFileDto.getFileKey()));
            Assertions.assertNotEquals(findFileDto.getFileKey(), updatedFileDto.getFileKey());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}