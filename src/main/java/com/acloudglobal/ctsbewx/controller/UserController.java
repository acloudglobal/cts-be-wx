package com.acloudglobal.ctsbewx.controller;


import com.acloudglobal.ctsbewx.dto.*;
import com.acloudglobal.ctsbewx.service.UserService;
import com.acloudglobal.ctsbewx.vo.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/wx/v1/user")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping(value = "/sync")
    @ResponseBody
    public void sync(@Valid @RequestBody SyncUserVO vo) throws Exception {
        SyncUserDTO dto = new SyncUserDTO();
        dto.setAcctId(vo.getAcctId());
        dto.setUserName(vo.getUserName());
        dto.setPassword(vo.getPassword());
        dto.setToken(vo.getToken());

        userService.syncUser(dto);
    }
}
