package com.example.zmusic.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@ApiModel("用户角色更新请求")
public class UserUpdateRolesRequest {
  @NotNull(message = "角色不能为空")
  @Size(min = 1, message = "角色不能为空")
  private List<String> roleIds;
}
