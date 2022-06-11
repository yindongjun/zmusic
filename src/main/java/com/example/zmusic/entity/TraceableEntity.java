package com.example.zmusic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TraceableEntity extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "create_by_user_id", referencedColumnName = "id")
    @CreatedBy
    protected User createUser;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "update_by_user_id", referencedColumnName = "id")
    @LastModifiedBy
    protected User updateUser;
}
