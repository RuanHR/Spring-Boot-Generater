package com.pactera.business.domain;

import com.pactera.base.domain.BaseDomain;
import javax.persistence.*;

public class User extends BaseDomain {
    /**
     * 员工号
     */
    private String code;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 性别 0 女 1 男
     */
    private Integer gender;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 电话
     */
    private String phone;

    /**
     * 员工描述
     */
    private String description;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门id
     */
    @Column(name = "org_id")
    private Long orgId;

    /**
     * 状态 0 不可用 1 可用
     */
    private Integer status;
}