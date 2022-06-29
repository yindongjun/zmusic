package com.example.zmusic.service;

import com.example.zmusic.dto.RoleDto;
import com.example.zmusic.dto.RoleSearchFilter;
import com.example.zmusic.entity.Role;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService extends GeneralService<Role, RoleDto> {
  List<RoleDto> list();

  Page<RoleDto> search(RoleSearchFilter roleSearchFilter);
}
