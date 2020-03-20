package com.acloudglobal.ctsbewx.repository;

import com.acloudglobal.ctsbewx.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    List<User> findOneByAcctId(String acctId);
    User findOneByUserNameAndAcctId(String userName,String acctId);
}
