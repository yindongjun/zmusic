package com.example.zmusic.controller;

import com.example.zmusic.dto.ArtistDto;
import com.example.zmusic.dto.ArtistSearchFilter;
import com.example.zmusic.mapper.ArtistMapper;
import com.example.zmusic.request.ArtistCreateRequest;
import com.example.zmusic.request.ArtistUpdateRequest;
import com.example.zmusic.service.ArtistService;
import com.example.zmusic.vo.ArtistVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artists")
@RequiredArgsConstructor
@Api(tags = "艺术家管理")
public class ArtistController {

  private final ArtistService artistService;

  private final ArtistMapper artistMapper;

  @GetMapping("/{id}")
  @ApiOperation("获取艺术家")
  public ArtistVo get(@PathVariable String id) {
    ArtistDto ArtistDto = artistService.get(id);
    return artistMapper.toVo(ArtistDto);
  }

  @GetMapping("/")
  @ApiOperation("分页查询艺术家")
  public Page<ArtistVo> search(@Validated ArtistSearchFilter filter) {
    Page<ArtistDto> ArtistDtoPage = artistService.search(filter);
    return ArtistDtoPage.map(artistMapper::toVo);
  }

  @PostMapping("/")
  @PreAuthorize("hasRole('ADMIN')")
  @ApiOperation("添加艺术家")
  public ArtistVo create(@Validated @RequestBody ArtistCreateRequest artistCreateRequest) {
    ArtistDto ArtistDto = artistService.create(artistMapper.toDto(artistCreateRequest));
    return artistMapper.toVo(ArtistDto);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  @ApiOperation("更新艺术家")
  public ArtistVo update(
      @PathVariable String id, @Validated @RequestBody ArtistUpdateRequest artistUpdateRequest) {
    ArtistDto ArtistDto = artistService.update(id, artistMapper.toDto(artistUpdateRequest));
    return artistMapper.toVo(ArtistDto);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  @ApiOperation("删除艺术家")
  public void delete(@PathVariable String id) {
    artistService.delete(id);
  }
}
