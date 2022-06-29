package com.example.zmusic.service;

import cn.hutool.core.lang.Assert;
import com.example.zmusic.dto.RoleDto;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.mapper.RoleMapper;
import com.example.zmusic.request.RoleCreateRequest;
import com.example.zmusic.request.RoleUpdateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoleServiceTest {

  @Autowired private RoleService roleService;

  @Autowired private RoleMapper roleMapper;

  @Test
  public void test_get() {
    RoleCreateRequest roleCreateRequest = new RoleCreateRequest();
    roleCreateRequest.setName("ROLE_TEST");
    roleCreateRequest.setTitle("ROLE_TEST");

    RoleDto roleDto = roleService.create(roleMapper.toDto(roleCreateRequest));

    RoleDto findRoleDto = roleService.get(roleDto.getId());

    Assert.notNull(findRoleDto);
  }

  @Test
  public void test_create() {
    RoleCreateRequest roleCreateRequest = new RoleCreateRequest();
    roleCreateRequest.setName("ROLE_TEST_2");
    roleCreateRequest.setTitle("ROLE_TEST_2");

    RoleDto roleDto = roleService.create(roleMapper.toDto(roleCreateRequest));

    RoleDto findRoleDto = roleService.get(roleDto.getId());

    Assertions.assertNotNull(findRoleDto);
    Assertions.assertEquals(findRoleDto.getTitle(), "ROLE_TEST_2");
    Assertions.assertEquals(findRoleDto.getName(), "ROLE_TEST_2");
  }

  @Test
  public void test_update() {
    RoleCreateRequest roleCreateRequest = new RoleCreateRequest();
    roleCreateRequest.setName("ROLE_TEST_3");
    roleCreateRequest.setTitle("ROLE_TEST_3");

    RoleDto roleDto = roleService.create(roleMapper.toDto(roleCreateRequest));

    RoleDto findRoleDto = roleService.get(roleDto.getId());

    RoleUpdateRequest roleUpdateRequest = new RoleUpdateRequest();
    roleUpdateRequest.setName("ROLE_TEST_3_UPDATED");
    roleUpdateRequest.setTitle("ROLE_TEST_3_UPDATED");
    RoleDto updatedRole =
        roleService.update(findRoleDto.getId(), roleMapper.toDto(roleUpdateRequest));

    Assertions.assertEquals(updatedRole.getTitle(), "ROLE_TEST_3_UPDATED");
    Assertions.assertEquals(updatedRole.getName(), "ROLE_TEST_3_UPDATED");
  }

  @Test
  public void test_delete() {
    RoleCreateRequest roleCreateRequest = new RoleCreateRequest();
    roleCreateRequest.setName("ROLE_TEST_4");
    roleCreateRequest.setTitle("ROLE_TEST_4");

    RoleDto roleDto = roleService.create(roleMapper.toDto(roleCreateRequest));

    RoleDto findRoleDto = roleService.get(roleDto.getId());

    roleService.delete(findRoleDto.getId());

    Assertions.assertThrows(BizException.class, () -> roleService.get(findRoleDto.getId()));
  }
}
