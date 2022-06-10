package com.example.zmusic.controller;

import com.example.zmusic.dto.FileDto;
import com.example.zmusic.mapper.FileMapper;
import com.example.zmusic.service.FileService;
import com.example.zmusic.vo.FileVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Api(tags = "文件管理接口")
public class FileController {

    private final FileService fileService;

    private final FileMapper fileMapper;

    @GetMapping("/{id}")
    @ApiOperation("获取文件")
    public FileVo get(@PathVariable String id) {
        FileDto fileDto = fileService.get(id);
        return fileMapper.toVo(fileDto);
    }

    @GetMapping
    public Page<FileVo> search(@PageableDefault(sort = {"createTime"}, direction = Sort.Direction.ASC)
                               Pageable pageable) {
        return fileService.search(pageable)
                .map(fileMapper::toVo);
    }

    @PostMapping("/")
    @ApiOperation("上传文件")
    public FileVo create(MultipartFile file) {
        FileDto fileDto = fileService.create(file);
        return fileMapper.toVo(fileDto);
    }

    @PutMapping("/{id}")
    @ApiOperation("更新文件")
    public FileVo update(@PathVariable String id, MultipartFile fileToUpdate) {
        FileDto fileDto = fileService.update(id, fileToUpdate);
        return fileMapper.toVo(fileDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        fileService.deleteById(id);
    }
}
