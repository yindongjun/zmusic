package com.example.zmusic.entity;

import lombok.*;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role extends AbstractEntity {
    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标识
     */
    private String title;
}

