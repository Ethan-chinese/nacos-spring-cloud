package com.sj.product.feign.entity;

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
@TableName(value = "product")
public class ProductBean implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "product_name")
    private String productName;
}
