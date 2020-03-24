package com.acloudglobal.ctsbewx.service.impl;

import com.acloudglobal.ctsbewx.common.OrderStatusEnum;
import com.acloudglobal.ctsbewx.domain.User;
import com.acloudglobal.ctsbewx.domain.UserOpenid;
import com.acloudglobal.ctsbewx.dto.BindInfoDTO;
import com.acloudglobal.ctsbewx.dto.BindUserDTO;
import com.acloudglobal.ctsbewx.dto.SyncUserDTO;
import com.acloudglobal.ctsbewx.exception.AppException;
import com.acloudglobal.ctsbewx.repository.UserOpenidRepository;
import com.acloudglobal.ctsbewx.repository.UserRepository;
import com.acloudglobal.ctsbewx.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserRepository userRepository;
    @Resource
    UserOpenidRepository userOpenidRepository;


    @Override
    public BindInfoDTO bindOpenIdToUser(BindUserDTO dto) throws AppException {
        BindInfoDTO bindInfoDTO = new BindInfoDTO();
        List<User> users = userRepository.findOneByAcctId(dto.getAcctId());
        if (CollectionUtils.isEmpty(users)) {
            throw new AppException("账户不存在");
        }

        User user = userRepository.findOneByUserNameAndAcctId(dto.getUserName(), dto.getAcctId());
        if (null == user || !user.getPassword().equalsIgnoreCase(DigestUtils.md5Hex(dto.getPassword()))) {
            throw new AppException("用户名或密码错误");
        }

        UserOpenid userOpenid = userOpenidRepository.findUserOpenidByOpenidAndUserUid(dto.getOpenid(),user.getUid());

        if (null == userOpenid) {
            userOpenid = new UserOpenid();
            userOpenid.setOpenid(dto.getOpenid());
            userOpenid.setUserUid(user.getUid());
            userOpenid.setOrderStatus(OrderStatusEnum.NO);
            userOpenid.setCreateTime(new Date());
            userOpenidRepository.save(userOpenid);
        }

        bindInfoDTO.setToken(user.getToken());
        bindInfoDTO.setHost(user.getHost());

        return bindInfoDTO;
    }

    @Override
    public void syncUser(SyncUserDTO dto) throws AppException {
        User user = userRepository.findOneByUserNameAndAcctId(dto.getUserName(), dto.getAcctId());
        if (null != user && user.getPassword().equals(dto.getPassword())) {
            return;
        }


        if (null == user) {
            user = new User();
            user.setAcctId(dto.getAcctId());
            user.setUserName(dto.getUserName());
            user.setPassword(dto.getPassword());
            user.setToken(dto.getToken());
            user.setHost(dto.getHost());
            user.setCreateTime(new Date());
        } else {
            user.setPassword(dto.getPassword());
            user.setUpdateTime(new Date());
        }

        userRepository.save(user);
    }
}
