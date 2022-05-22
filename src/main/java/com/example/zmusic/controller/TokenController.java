package com.example.zmusic.controller;

import com.example.zmusic.dto.TokenDto;
import com.example.zmusic.mapper.TokenMapper;
import com.example.zmusic.request.TokenCreateRequest;
import com.example.zmusic.service.UserService;
import com.example.zmusic.vo.TokenVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tokens")
@RequiredArgsConstructor
public class TokenController {

    private final UserService userService;

    private final TokenMapper tokenMapper;

    @PostMapping("/")
    public TokenVo create(@Validated @RequestBody TokenCreateRequest tokenCreateRequest,
                          HttpServletRequest request) {
        // check username and password
        TokenDto tokenDto = userService.createToken(tokenCreateRequest, request);

        return tokenMapper.toVo(tokenDto);
    }
}
