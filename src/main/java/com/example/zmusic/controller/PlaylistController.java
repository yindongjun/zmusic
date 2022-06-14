package com.example.zmusic.controller;

import com.example.zmusic.dto.PlaylistDto;
import com.example.zmusic.mapper.PlaylistMapper;
import com.example.zmusic.request.PlaylistCreateRequest;
import com.example.zmusic.request.PlaylistUpdateRequest;
import com.example.zmusic.service.PlaylistService;
import com.example.zmusic.vo.PlaylistVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
@Api(tags = "歌单管理")
public class PlaylistController {

    private final PlaylistService playlistService;

    private final PlaylistMapper playlistMapper;

    @GetMapping("/{id}")
    @ApiOperation("获取歌单")
    public PlaylistVo get(@PathVariable String id) {
        PlaylistDto playlistDto = playlistService.get(id);
        return playlistMapper.toVo(playlistDto);
    }

    @GetMapping("/")
    @ApiOperation("分页查询歌单列表")
    public Page<PlaylistVo> search(@PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.ASC)
                                   Pageable pageable) {
        Page<PlaylistDto> playlistPage = playlistService.search(pageable);
        return playlistPage.map(playlistMapper::toVo);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("添加歌单")
    public PlaylistVo create(@Validated @RequestBody PlaylistCreateRequest playlistCreateRequest) {
        PlaylistDto playlistDto = playlistService.create(playlistMapper.toDto(playlistCreateRequest));
        return playlistMapper.toVo(playlistDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("更新歌单")
    public PlaylistVo update(@PathVariable String id, @Validated @RequestBody PlaylistUpdateRequest playlistUpdateRequest) {
        PlaylistDto playlistDto = playlistService.update(id, playlistMapper.toDto(playlistUpdateRequest));
        return playlistMapper.toVo(playlistDto);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("删除歌单")
    public void delete(@PathVariable String id) {
        playlistService.delete(id);
    }
}
