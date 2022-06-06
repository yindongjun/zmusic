package com.example.zmusic.controller;

import com.example.zmusic.dto.LoginDto;
import com.example.zmusic.mapper.TokenMapper;
import com.example.zmusic.request.TokenCreateRequest;
import com.example.zmusic.service.UserService;
import com.example.zmusic.vo.TokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = "认证管理")
public class AuthController {

    private final UserService userService;

    private final TokenMapper tokenMapper;

    @PostMapping("/login")
    @ApiOperation("创建令牌")
    public TokenVo login(@Validated @RequestBody TokenCreateRequest tokenCreateRequest,
                          HttpServletRequest request) {
        // check username and password
        LoginDto loginDto = userService.login(tokenCreateRequest, request);

        return tokenMapper.toVo(loginDto);
    }
}
