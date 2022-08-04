package com.example.zmusic.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleSearchFilter extends PageFilter {

    private String name;
    private String title;
}
