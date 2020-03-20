package com.acloudglobal.ctsbewx.domain;

import com.acloudglobal.ctsbewx.common.OrderStatusEnum;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "wx_user_openid")
public class UserOpenid {
    /**
     * 微信小程序用户唯一标识
     */
    @Id
    private String openid;

    /**
     * 用户uid
     */
    @Column(name = "user_uid")
    private String userUid;


    /**
     * 是否订阅消息
     */
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}