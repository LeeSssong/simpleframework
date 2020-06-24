package com.lss.entity.dto;

import com.lss.entity.bo.HeadLine;
import com.lss.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;

/**
 * @authonr: LeeSongsheng
 * @create: 2020/06/24
 **/
@Data
public class MainPgeInfoDTO {

    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;
}
