package com.example.zmusic.controller;

import com.example.zmusic.dto.RoleDto;
import com.example.zmusic.dto.RoleSearchFilter;
import com.example.zmusic.mapper.RoleMapper;
import com.example.zmusic.request.RoleCreateRequest;
import com.example.zmusic.request.RoleUpdateRequest;
import com.example.zmusic.service.RoleService;
import com.example.zmusic.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
@Api(tags = "角色管理")
public class RoleController {

    private final RoleService roleService;

    private final RoleMapper roleMapper;

    @GetMapping("/{id}")
    @ApiOperation("获取角色")
    public RoleVo get(@PathVariable String id) {
        RoleDto roleDto = roleService.get(id);
        return roleMapper.toVo(roleDto);
    }

    @GetMapping("/")
    public Page<RoleVo> search(@Validated RoleSearchFilter roleSearchFilter) {
        Page<RoleDto> page = roleService.search(roleSearchFilter);
        return page.map(roleMapper::toVo);
    }

    @GetMapping("/list")
    public List<RoleVo> list() {
        List<RoleDto> roleList = roleService.list();
        return roleMapper.toVo(roleList);
    }

    @PostMapping("/")
    public RoleVo create(@Validated @RequestBody RoleCreateRequest roleCreateRequest) {
        RoleDto roleDto = roleService.create(roleMapper.toDto(roleCreateRequest));
        return roleMapper.toVo(roleDto);
    }

    @PutMapping("/{id}")
    public RoleVo update(
            @PathVariable String id, @Validated @RequestBody RoleUpdateRequest roleUpdateRequest) {
        RoleDto roleDto = roleService.update(id, roleMapper.toDto(roleUpdateRequest));
        return roleMapper.toVo(roleDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        roleService.delete(id);
    }
}
