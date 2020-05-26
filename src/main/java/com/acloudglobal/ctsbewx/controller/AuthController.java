package com.acloudglobal.ctsbewx.controller;

import com.acloudglobal.ctsbewx.dto.*;
import com.acloudglobal.ctsbewx.exception.AppException;
import com.acloudglobal.ctsbewx.service.UserService;
import com.acloudglobal.ctsbewx.utils.MiniProgramUtil;
import com.acloudglobal.ctsbewx.vo.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/wx/v1")
public class AuthController {
    @Value("${wechat.appid}")
    private String appId;
    @Value("${wechat.secret}")
    private String appSecret;
    @Resource
    UserService userService;

    @PostMapping(value = "/login")
    @ResponseBody
    public BindInfoVO login(@Valid @RequestBody BindUserVO vo) throws Exception {
        OpenIdJson openIdJson = MiniProgramUtil.getOpenid(appId, appSecret, vo.getCode());
        if (!openIdJson.isSuccess()) {
            throw new AppException("获取微信用户唯一标识失败");
        }

        BindUserDTO dto = new BindUserDTO();
        dto.setOpenid(openIdJson.getOpenid());
        dto.setAcctId(vo.getAcctId());
        dto.setPassword(vo.getPassword());
        dto.setUserName(vo.getUserName());

        BindInfoDTO bindInfoDTO = userService.bindOpenIdToUser(dto);

        BindInfoVO bindInfoVO = new BindInfoVO();
        bindInfoVO.setAcctUid(bindInfoDTO.getAcctUid());
        bindInfoVO.setToken(bindInfoDTO.getToken());

        return bindInfoVO;
    }
}
