package com.cjx.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @TableName admin
 */
@TableName(value ="admin")
@Data
public class Admin implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String avatar;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
    private Date deletedAt;
    private static final long serialVersionUID = 1L;
}