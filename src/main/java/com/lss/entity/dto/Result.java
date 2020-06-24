package com.lss.entity.dto;

import lombok.Data;

/**
 * @authonr: LeeSongsheng
 * @create: 2020/06/24
 **/
@Data
public class Result<T> {
    //本次请求结果的状态码，200成功
    private int code;

    //本次请求结果的详情
    private String msg;

    //本次请求返回的结果集
    private T data;
}
