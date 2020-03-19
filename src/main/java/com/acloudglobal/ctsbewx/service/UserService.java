package com.acloudglobal.ctsbewx.service;

import com.acloudglobal.ctsbewx.dto.*;
import com.acloudglobal.ctsbewx.exception.AppException;

public interface UserService {
    /**
     * 绑定微信唯一标识到用户
     *
     * @param dto 用户信息
     * @return 绑定成功用户信息
     * @throws AppException 系统异常
     */
    BindInfoDTO bindOpenIdToUser(BindUserDTO dto) throws AppException;

    /**
     * CTS用户同步
     *
     * @param dto CTS用户信息
     * @throws AppException 系统异常
     */
    void syncUser(SyncUserDTO dto) throws AppException;

    /**
     * 解绑微信信息
     *
     * @param dto 解绑信息
     * @throws AppException 系统异常
     */
    void unbindOpenId(UnbindOpenIdDTO dto) throws AppException;
}
