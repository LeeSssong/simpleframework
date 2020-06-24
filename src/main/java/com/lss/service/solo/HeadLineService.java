package com.lss.service.solo;

import com.lss.entity.bo.HeadLine;
import com.lss.entity.dto.Result;

import java.util.List;

/**
 * @authonr: LeeSongsheng
 * @create: 2020/06/24
 **/
public interface HeadLineService {

    Result<Boolean> addHeadLine(HeadLine headLine);
    Result<Boolean> removeHeadLine(int headLineId);
    Result<Boolean> modifyHeadLine(HeadLine headLine);
    Result<HeadLine> queryHeadLineById(int headLineId);
    Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize);
}
