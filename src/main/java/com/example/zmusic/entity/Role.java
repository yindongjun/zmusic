package com.example.zmusic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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


    @ManyToMany(mappedBy = "roles", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "roles")
    private List<User> users = new ArrayList<>();
}

