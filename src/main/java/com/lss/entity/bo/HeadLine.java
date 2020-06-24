package com.lss.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * @authonr: LeeSongsheng
 * @create: 2020/06/23
 **/
@Data
public class HeadLine {
    private Long lineId;

    private String  lineName;

    private String lineLink;

    private String lineImg;

    private Integer priority;

    //头条信息可用状态，0：不可用，1：可用
    private Integer enableStatus;

    private Date createTime;

    private Date lastEditTime;
}
