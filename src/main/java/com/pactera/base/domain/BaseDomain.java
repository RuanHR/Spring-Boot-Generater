package com.pactera.base.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: BaseDomain
 * @date 2017年9月18日 下午1:49:48
 * @author ToniR
 * @Description: 实体基类
 */
@Setter
@Getter
public class BaseDomain implements Serializable {
	private static final long serialVersionUID = -2721739505707883933L;
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
}
