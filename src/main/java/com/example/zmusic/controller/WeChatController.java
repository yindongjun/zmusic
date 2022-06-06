package com.example.zmusic.controller;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wechat")
@RequiredArgsConstructor
public class WeChatController {

    private final WxMpService wxMpService;

    /**
     * 获取请求请求 code 的地址
     *
     * @param redirectUrl 重定向地址
     * @return /
     */
    @GetMapping("/auth_url")
    public String getAuthUrl(@RequestParam("redirectUrl") String redirectUrl) {
        return wxMpService.getOAuth2Service().buildAuthorizationUrl(redirectUrl,
                WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
    }

    @PostMapping("/get_user_info")
    public WxOAuth2UserInfo getUserInfo(@RequestParam("code") String code) throws WxErrorException {
        WxOAuth2AccessToken accessToken = wxMpService.getOAuth2Service().getAccessToken(code);
        return wxMpService.getOAuth2Service().getUserInfo(accessToken, null);
    }
}
