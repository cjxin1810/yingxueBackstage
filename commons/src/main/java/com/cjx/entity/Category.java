package com.cjx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;

import javax.jws.HandlerChain;

/**
 * @TableName category
 */

@TableName(value ="category")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;
    @TableField("parent_id")
    @JsonProperty("parent_id")
    private Integer parentId;
    @TableField(exist = false)
    private List<Category> children;
    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private static final long serialVersionUID = 1L;
}