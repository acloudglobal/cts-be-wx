package com.acloudglobal.ctsbewx.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "wx_user")
public class User {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uid;

    /**
     * 微信小程序用户唯一标识
     */
    private String openid;

    /**
     * 账户编码
     */
    @Column(name = "acct_id")
    private String acctId;

    /**
     * 用户名，新增时输入
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 接入秘钥
     */
    private String token;

    /**
     * 租户后台域名
     */
    private String host;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
}