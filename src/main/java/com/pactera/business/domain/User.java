package com.pactera.business.domain;

import com.pactera.base.domain.BaseDomain;
import javax.persistence.*;

public class User extends BaseDomain {
    /**
     * 名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 业务线id
     */
    @Column(name = "org_id")
    private Long orgId;
}