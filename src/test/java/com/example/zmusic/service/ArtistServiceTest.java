package com.example.zmusic.service;

import cn.hutool.core.io.FileUtil;
import com.example.zmusic.ZmusicApplication;
import com.example.zmusic.dto.ArtistDto;
import com.example.zmusic.dto.FileDto;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.mapper.ArtistMapper;
import com.example.zmusic.request.ArtistCreateRequest;
import com.example.zmusic.request.ArtistUpdateRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;

@SpringBootTest(classes = ZmusicApplication.class)
@Slf4j
class ArtistServiceTest {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ArtistMapper artistMapper;

    @SneakyThrows
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    public void test_search() {
        // mock file
        BufferedInputStream fileIs = FileUtil.getInputStream("/Users/jiangzheng/Desktop/vue.js");
        MultipartFile mockFile = new MockMultipartFile("vue.js", "vue.js", "application/js", fileIs);
        FileDto file = fileService.create(mockFile);

        // mock artist
        ArtistCreateRequest request = new ArtistCreateRequest();
        request.setName("周杰伦");
        request.setRemark("Jay Chou");
        request.setCoverId(file.getId());

        ArtistDto artistDto = artistMapper.toDto(request);

        ArtistDto savedArtist = artistService.create(artistDto);

        Page<ArtistDto> artistPage = artistService.search(PageRequest.of(0, 10));
        Assertions.assertNotEquals(artistPage.getTotalElements(), 0);
    }

    @SneakyThrows
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    public void test_create() {
        // mock file
        BufferedInputStream fileIs = FileUtil.getInputStream("/Users/jiangzheng/Desktop/vue.js");
        MultipartFile mockFile = new MockMultipartFile("vue.js", "vue.js", "application/js", fileIs);
        FileDto file = fileService.create(mockFile);

        // mock artist
        ArtistCreateRequest request = new ArtistCreateRequest();
        request.setName("周杰伦");
        request.setRemark("Jay Chou");
        request.setCoverId(file.getId());

        ArtistDto artistDto = artistMapper.toDto(request);
        ArtistDto savedArtist = artistService.create(artistDto);

        Assertions.assertNotNull(savedArtist.getId());

        ArtistDto findArtist = artistService.get(savedArtist.getId());
        Assertions.assertNotNull(findArtist);
        Assertions.assertNotNull(findArtist.getCover());
        Assertions.assertNotNull(findArtist.getCover().getId());
    }

    @SneakyThrows
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    public void test_update() {
        // mock file
        BufferedInputStream fileIs = FileUtil.getInputStream("/Users/jiangzheng/Desktop/vue.js");
        MultipartFile mockFile = new MockMultipartFile("vue.js", "vue.js", "application/js", fileIs);
        FileDto file = fileService.create(mockFile);

        // mock artist
        ArtistCreateRequest request = new ArtistCreateRequest();
        request.setName("周杰伦");
        request.setRemark("Jay Chou");
        request.setCoverId(file.getId());
        ArtistDto artistDto = artistMapper.toDto(request);
        ArtistDto savedArtist = artistService.create(artistDto);

        // update artist
        BufferedInputStream fileIs2 = FileUtil.getInputStream("/Users/jiangzheng/Desktop/vue.js");
        MultipartFile mockFile2 = new MockMultipartFile("vue.js", "vue.js", "application/js", fileIs2);
        FileDto file2 = fileService.create(mockFile2);

        ArtistUpdateRequest artistUpdateRequest = new ArtistUpdateRequest();
        artistUpdateRequest.setName("ColdPlay");
        artistUpdateRequest.setRemark("ColdPlay");
        artistUpdateRequest.setCoverId(file2.getId());

        artistService.update(savedArtist.getId(), artistMapper.toDto(artistUpdateRequest));

        ArtistDto findArtist = artistService.get(savedArtist.getId());
        Assertions.assertNotNull(findArtist);
        Assertions.assertNotNull(findArtist.getCover());
        Assertions.assertNotNull(findArtist.getCover().getId());
        Assertions.assertNotEquals(findArtist.getCover().getId(), savedArtist.getCover().getId());
        Assertions.assertEquals(findArtist.getName(), "ColdPlay");
    }

    @SneakyThrows
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    public void test_delete() {
        // mock file
        BufferedInputStream fileIs = FileUtil.getInputStream("/Users/jiangzheng/Desktop/vue.js");
        MultipartFile mockFile = new MockMultipartFile("vue.js", "vue.js", "application/js", fileIs);
        FileDto file = fileService.create(mockFile);

        // mock artist
        ArtistCreateRequest request = new ArtistCreateRequest();
        request.setName("周杰伦");
        request.setRemark("Jay Chou");
        request.setCoverId(file.getId());
        ArtistDto artistDto = artistMapper.toDto(request);
        ArtistDto savedArtist = artistService.create(artistDto);

        artistService.delete(savedArtist.getId());

        Assertions.assertThrows(BizException.class, () -> artistService.get(savedArtist.getId()));
    }
}