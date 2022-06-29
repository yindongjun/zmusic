package com.example.zmusic.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PageFilter {
  @NotNull(message = "分页页码不能为空")
  @Min(value = 1, message = "分页页码最小为 1")
  protected Integer page;

  @NotNull(message = "分页大小不能为空")
  @Min(value = 1, message = "分页大小最小为 1")
  protected Integer size;

  protected String sort = "createdTime";

  protected String direction = "ASC";

  public Pageable toPageable() {
    return PageRequest.of(page - 1, size, Sort.Direction.fromString(direction), sort);
  }
}
