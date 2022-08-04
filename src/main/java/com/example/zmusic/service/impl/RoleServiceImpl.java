package com.example.zmusic.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.zmusic.dto.RoleDto;
import com.example.zmusic.dto.RoleSearchFilter;
import com.example.zmusic.entity.Role;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.exception.ExceptionType;
import com.example.zmusic.mapper.MapperInterface;
import com.example.zmusic.mapper.RoleMapper;
import com.example.zmusic.repository.RoleRepository;
import com.example.zmusic.repository.specs.RoleSpecification;
import com.example.zmusic.repository.specs.SearchCriteria;
import com.example.zmusic.repository.specs.SearchOperation;
import com.example.zmusic.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleServiceImpl extends SimpleGeneralServiceImpl<Role, RoleDto>
        implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @Override
    public List<RoleDto> list() {
        return roleRepository.findAll().stream().map(roleMapper::toDto).toList();
    }

    @Override
    public Page<RoleDto> search(RoleSearchFilter roleSearchFilter) {
        Pageable pageable = roleSearchFilter.toPageable();

        RoleSpecification specification = new RoleSpecification();
        if (StrUtil.isNotBlank(roleSearchFilter.getName())) {
            specification.add(
                    new SearchCriteria("name", roleSearchFilter.getName(), SearchOperation.MATCH));
        }

        if (StrUtil.isNotBlank(roleSearchFilter.getTitle())) {
            specification.add(
                    new SearchCriteria("title", roleSearchFilter.getTitle(), SearchOperation.MATCH));
        }

        Page<Role> page = roleRepository.findAll(specification, pageable);
        return page.map(roleMapper::toDto);
    }

    @Override
    @Transactional
    public RoleDto create(RoleDto dto) {
        // Validate
        checkRoleNameDuplicate(dto);

        // Create
        return super.create(dto);
    }

    @Override
    @Transactional
    public RoleDto update(String id, RoleDto dto) {
        // Validate
        dto.setId(id);
        checkRoleNameDuplicate(dto);

        // Update
        return super.update(id, dto);
    }

    private void checkRoleNameDuplicate(RoleDto dto) {
        final String realId = Optional.ofNullable(dto.getId()).orElse("-1");
        roleRepository
                .findFirstByName(dto.getName())
                .ifPresent(
                        role -> {
                            if (ObjectUtil.notEqual(role.getId(), realId)) {
                                throw new BizException(ExceptionType.ROLE_TITLE_DUPLICATE);
                            }
                        });
    }

    @Override
    public MapperInterface<Role, RoleDto> getMapstructMapper() {
        return roleMapper;
    }

    @Override
    public JpaRepository<Role, String> getRepository() {
        return roleRepository;
    }

    @Override
    public BizException getNotFoundException() {
        return new BizException(ExceptionType.ROLE_NOT_FOUND);
    }
}
