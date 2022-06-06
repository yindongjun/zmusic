package com.example.zmusic.controller;

import com.example.zmusic.dto.MusicDto;
import com.example.zmusic.mapper.MusicMapper;
import com.example.zmusic.request.MusicCreateRequest;
import com.example.zmusic.request.MusicUpdateRequest;
import com.example.zmusic.service.MusicService;
import com.example.zmusic.vo.MusicVo;
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
@RequestMapping("/musics")
@RequiredArgsConstructor
@Api(tags = "音乐管理")
public class MusicController {

    private final MusicService musicService;

    private final MusicMapper musicMapper;

    @GetMapping("/{id}")
    @ApiOperation("获取音乐")
    public MusicVo get(@PathVariable String id) {
        MusicDto musicDto = musicService.get(id);
        return musicMapper.toVo(musicDto);
    }

    @GetMapping("/")
    public Page<MusicVo> search(@PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.ASC)
                                Pageable pageable) {
        Page<MusicDto> musicDtoPage = musicService.search(pageable);
        return musicDtoPage.map(musicMapper::toVo);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("添加音乐")
    public MusicVo create(@Validated @RequestBody MusicCreateRequest musicCreateRequest) {
        MusicDto musicDto = musicService.create(musicCreateRequest);
        return musicMapper.toVo(musicDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("更新音乐")
    public MusicVo update(@PathVariable String id, @Validated @RequestBody MusicUpdateRequest musicUpdateRequest) {
        MusicDto musicDto = musicService.update(id, musicUpdateRequest);
        return musicMapper.toVo(musicDto);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("删除音乐")
    public void delete(@PathVariable String id) {
        musicService.delete(id);
    }

    @PostMapping("/{id}/publish")
    @ApiOperation("上架音乐")
    public void publish(@PathVariable String id) {
        musicService.publish(id);
    }

    @PostMapping("/{id}/close")
    public void close(@PathVariable String id) {
        musicService.close(id);
    }
}
