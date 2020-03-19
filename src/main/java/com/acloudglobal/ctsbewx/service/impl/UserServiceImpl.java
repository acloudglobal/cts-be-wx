package com.acloudglobal.ctsbewx.service.impl;

import com.acloudglobal.ctsbewx.domain.User;
import com.acloudglobal.ctsbewx.dto.*;
import com.acloudglobal.ctsbewx.exception.AppException;
import com.acloudglobal.ctsbewx.repository.UserRepository;
import com.acloudglobal.ctsbewx.service.UserService;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserRepository userRepository;


    @Override
    public BindInfoDTO bindOpenIdToUser(BindUserDTO dto) throws AppException {
        BindInfoDTO bindInfoDTO = new BindInfoDTO();
        User user = userRepository.findOneByOpenidAndUserNameAndAcctId(dto.getOpenid(), dto.getUserName(), dto.getAcctId());
        if (null != user) {
            bindInfoDTO.setHost(user.getHost());
            bindInfoDTO.setToken(user.getToken());

            return bindInfoDTO;
        }

        user =    userRepository.findOneByAcctId(dto.getAcctId());
        if (null == user) {
            throw new AppException("账户不存在");
        }

        user = userRepository.findOneByUserNameAndAcctId(dto.getUserName(),dto.getAcctId());
        if (null == user || !user.getPassword().equalsIgnoreCase(DigestUtils.md5Hex(dto.getPassword()))) {
            throw new AppException("用户名或密码错误");
        }

        userRepository.bindOpenidToUser(dto.getOpenid(), user.getUid(), new Date());
        bindInfoDTO.setToken(user.getToken());
        bindInfoDTO.setHost(user.getHost());

        return bindInfoDTO;
    }

    @Override
    public void syncUser(SyncUserDTO dto) throws AppException {
       User user =  userRepository.findOneByUserNameAndAcctId(dto.getUserName(),dto.getAcctId());
       if(null != user){
           return;
       }

       user = new User();
       user.setOpenid("");
       user.setAcctId(dto.getAcctId());
       user.setUserName(dto.getUserName());
       user.setPassword(dto.getPassword());
       user.setToken(dto.getToken());
       user.setHost(dto.getHost());
       user.setCreateTime(new Date());

       userRepository.save(user);
    }

    @Override
    public void unbindOpenId(UnbindOpenIdDTO dto) throws AppException {
        User user = userRepository.findOneByOpenid(dto.getOpenid());
        if (null == user) {
            throw new AppException("用户不存在");
        }

        if (!user.getPassword().equalsIgnoreCase(DigestUtils.md5Hex(dto.getPassword()))) {
            throw new AppException("密码错误");
        }

        userRepository.unBindOpenidToUser(user.getUid(), new Date());
    }
}
