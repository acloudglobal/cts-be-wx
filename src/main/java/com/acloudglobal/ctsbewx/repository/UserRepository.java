package com.acloudglobal.ctsbewx.repository;

import com.acloudglobal.ctsbewx.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface UserRepository extends JpaRepository<User,String> {
    User findOneByAcctId(String acctId);
    User findOneByOpenid(String openid);
    User findOneByUserNameAndAcctId(String userName,String acctId);
    User findOneByOpenidAndUserNameAndAcctId(String openid,String userName,String acctId);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "update User set openid = ?1,updateTime = ?3 where uid = ?2")
    int bindOpenidToUser(String openid, String uid, Date updateTime);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "update User set openid = '',updateTime = ?2 where uid = ?1")
    int unBindOpenidToUser(String uid, Date updateTime);

}
