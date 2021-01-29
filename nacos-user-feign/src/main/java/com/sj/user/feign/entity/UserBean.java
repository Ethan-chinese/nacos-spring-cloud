package com.sj.user.feign.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-18 14:43:16
 */
@Data
@TableName(value = "user")
public class UserBean implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_name", jdbcType = org.apache.ibatis.type.JdbcType.VARCHAR)
    private String userName;

    @TableField(value = "user_age")
    private Integer userAge;
}
