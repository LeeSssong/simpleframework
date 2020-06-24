package com.lss.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * @authonr: LeeSongsheng
 * @create: 2020/06/23
 **/
@Data
public class ShopCategory {
    private Long shopCategoryId;

    private String shopCategoryName;

    private String shopCategoryDesc;

    private String shopCategoryImg;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    private ShopCategory parent;
}
