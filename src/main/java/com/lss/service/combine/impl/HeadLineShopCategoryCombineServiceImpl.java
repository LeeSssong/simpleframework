package com.lss.service.combine.impl;

import com.lss.entity.bo.HeadLine;
import com.lss.entity.bo.ShopCategory;
import com.lss.entity.dto.MainPgeInfoDTO;
import com.lss.entity.dto.Result;
import com.lss.service.combine.HeadLineShopCategoryCombineService;
import com.lss.service.solo.HeadLineService;
import com.lss.service.solo.ShopCategoryService;

import java.util.List;

/**
 * @authonr: LeeSongsheng
 * @create: 2020/06/24
 **/
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {
    private HeadLineService headLineService;
    private ShopCategoryService shopCategoryService;

    /**
    * @Description: 将头条信息与店铺信息组合后放到首页中
    * @Param: []
    * @return: com.lss.entity.dto.Result<com.lss.entity.dto.MainPgeInfoDTO>
    * @Author: LeeSongs
    * @Date: 2020/6/24
    */
    @Override
    public Result<MainPgeInfoDTO> getMainPageInfo() {
        //1.获取头条列表
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> HeadLineResult = headLineService.queryHeadLine(headLineCondition, 1, 4);

        //2.获取店铺类别列表
        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult = shopCategoryService.queryShopCategory(shopCategoryCondition, 1, 100);

        //3.合并两者并返回
        Result<MainPgeInfoDTO> result = mergeMainPageInfoResult(HeadLineResult, shopCategoryResult);


        return null;
    }


    private Result<MainPgeInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        return null;
    }
}
