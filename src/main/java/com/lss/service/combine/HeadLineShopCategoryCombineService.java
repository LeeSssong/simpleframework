package com.lss.service.combine;

import com.lss.entity.dto.Result;
import com.lss.entity.dto.MainPgeInfoDTO;

/**
 * @authonr: LeeSongsheng
 * @create: 2020/06/24
 **/
public interface HeadLineShopCategoryCombineService {
    Result<MainPgeInfoDTO> getMainPageInfo();
}
