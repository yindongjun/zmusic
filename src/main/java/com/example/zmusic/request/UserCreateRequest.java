package com.example.zmusic.request;

import com.example.zmusic.enums.Gender;
import com.example.zmusic.validate.EnumExist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 64, message = "用户名长度应该在4个字符到64个字符之间")
    private String username;

    @Size(min = 4, max = 64, message = "昵称长度应该在4个字符到64个字符之间")
    private String nickname;

    @NotBlank(message = "密码不能为空")
    @Size(min = 4, max = 64, message = "密码长度在4个字符到64个字符之间")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @Size(min = 4, max = 64, message = "确认密码长度在4个字符到64个字符之间")
    private String confirmPassword;

    @EnumExist(enumClass = Gender.class, message = "性别不符合规范")
    private String gender;
}
