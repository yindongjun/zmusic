package com.example.zmusic.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDto extends BaseDto {

    private String name;

    private String title;
}
