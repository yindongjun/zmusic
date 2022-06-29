package com.example.zmusic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TraceableEntity extends AbstractId {

  /** 创建时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @CreatedDate
  protected LocalDateTime createdTime;

  /** 更新时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @LastModifiedDate
  protected LocalDateTime updateTime;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "create_by_user_id", referencedColumnName = "id")
  @CreatedBy
  protected User createUser;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "update_by_user_id", referencedColumnName = "id")
  @LastModifiedBy
  protected User updateUser;
}
