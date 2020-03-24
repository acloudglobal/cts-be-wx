package com.acloudglobal.ctsbewx.repository;

import com.acloudglobal.ctsbewx.domain.UserOpenid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOpenidRepository extends JpaRepository<UserOpenid,String> {
    UserOpenid findUserOpenidByOpenidAndUserUid(String openid,String userUid);
}
