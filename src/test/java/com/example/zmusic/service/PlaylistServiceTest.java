package com.example.zmusic.service;

import cn.hutool.core.io.FileUtil;
import com.example.zmusic.dto.FileDto;
import com.example.zmusic.dto.PlaylistDto;
import com.example.zmusic.dto.PlaylistSearchFilter;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.mapper.PlaylistMapper;
import com.example.zmusic.request.PlaylistCreateRequest;
import com.example.zmusic.request.PlaylistUpdateRequest;
import java.io.BufferedInputStream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
class PlaylistServiceTest {

  @Autowired private PlaylistService playlistService;

  @Autowired private FileService fileService;

  @Autowired private PlaylistMapper playlistMapper;

  @SneakyThrows
  @Test
  void test_search() {
    // mock file
    BufferedInputStream fileIs = FileUtil.getInputStream("/Users/jiangzheng/Pictures/yellow.jpg");
    MultipartFile mockFile = new MockMultipartFile("vue.js", "vue.js", "application/jpg", fileIs);
    FileDto file = fileService.create(mockFile);

    PlaylistCreateRequest playlistCreateRequest = new PlaylistCreateRequest();
    playlistCreateRequest.setName("我的最爱");
    playlistCreateRequest.setDescription("我的最爱歌单");
    playlistCreateRequest.setCoverId(file.getId());
    PlaylistDto playlistDto = playlistService.create(playlistMapper.toDto(playlistCreateRequest));

    PlaylistSearchFilter playlistSearchFilter = new PlaylistSearchFilter();
    playlistSearchFilter.setPage(1);
    playlistSearchFilter.setSize(10);
    Page<PlaylistDto> page = playlistService.search(playlistSearchFilter);

    Assertions.assertNotNull(playlistDto.getId());
    Assertions.assertNotEquals(0, page.getTotalElements());
    Assertions.assertNotNull(page.getContent().get(0).getCover().getId());
  }

  @SneakyThrows
  @Test
  public void test_create() {
    // mock file
    BufferedInputStream fileIs = FileUtil.getInputStream("/Users/jiangzheng/Pictures/yellow.jpg");
    MultipartFile mockFile = new MockMultipartFile("vue.js", "vue.js", "application/jpg", fileIs);
    FileDto file = fileService.create(mockFile);

    PlaylistCreateRequest playlistCreateRequest = new PlaylistCreateRequest();
    playlistCreateRequest.setName("我的最爱");
    playlistCreateRequest.setDescription("我的最爱歌单");
    playlistCreateRequest.setCoverId(file.getId());
    PlaylistDto playlistDto = playlistService.create(playlistMapper.toDto(playlistCreateRequest));

    Assertions.assertNotNull(playlistDto.getId());
    Assertions.assertNotNull(playlistDto.getCover());
    Assertions.assertEquals("我的最爱", playlistDto.getName());
  }

  @SneakyThrows
  @Test
  public void test_update() {
    // mock file
    BufferedInputStream fileIs = FileUtil.getInputStream("/Users/jiangzheng/Pictures/yellow.jpg");
    MultipartFile mockFile = new MockMultipartFile("vue.js", "vue.js", "application/jpg", fileIs);
    FileDto file = fileService.create(mockFile);

    PlaylistCreateRequest playlistCreateRequest = new PlaylistCreateRequest();
    playlistCreateRequest.setName("我的最爱");
    playlistCreateRequest.setDescription("我的最爱歌单");
    playlistCreateRequest.setCoverId(file.getId());
    PlaylistDto playlistDto = playlistService.create(playlistMapper.toDto(playlistCreateRequest));

    Assertions.assertNotNull(playlistDto.getId());
    Assertions.assertNotNull(playlistDto.getCover());
    Assertions.assertEquals("我的最爱", playlistDto.getName());

    PlaylistUpdateRequest playlistUpdateRequest = new PlaylistUpdateRequest();
    playlistUpdateRequest.setName("ColdPlay");
    playlistUpdateRequest.setDescription("ColdPlay");
    playlistUpdateRequest.setCoverId(file.getId());
    PlaylistDto updatedPlaylistDto =
        playlistService.update(playlistDto.getId(), playlistMapper.toDto(playlistUpdateRequest));

    Assertions.assertEquals(updatedPlaylistDto.getId(), playlistDto.getId());
    Assertions.assertEquals("ColdPlay", updatedPlaylistDto.getName());
  }

  @SneakyThrows
  @Test
  public void test_delete() {
    // mock file
    BufferedInputStream fileIs = FileUtil.getInputStream("/Users/jiangzheng/Pictures/yellow.jpg");
    MultipartFile mockFile = new MockMultipartFile("vue.js", "vue.js", "application/jpg", fileIs);
    FileDto file = fileService.create(mockFile);

    PlaylistCreateRequest playlistCreateRequest = new PlaylistCreateRequest();
    playlistCreateRequest.setName("我的最爱");
    playlistCreateRequest.setDescription("我的最爱歌单");
    playlistCreateRequest.setCoverId(file.getId());
    PlaylistDto playlistDto = playlistService.create(playlistMapper.toDto(playlistCreateRequest));

    Assertions.assertNotNull(playlistDto.getId());
    Assertions.assertNotNull(playlistDto.getCover());
    Assertions.assertEquals("我的最爱", playlistDto.getName());

    playlistService.delete(playlistDto.getId());

    Assertions.assertThrows(
        BizException.class,
        () -> {
          playlistService.get(playlistDto.getId());
        });
  }
}
