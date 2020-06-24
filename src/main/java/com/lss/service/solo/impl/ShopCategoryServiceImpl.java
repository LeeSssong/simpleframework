package com.lss.service.solo.impl;

import com.lss.entity.bo.ShopCategory;
import com.lss.entity.dto.Result;
import com.lss.service.solo.ShopCategoryService;

import java.util.List;

/**
 * @authonr: LeeSongsheng
 * @create: 2020/06/24
 **/
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Override
    public Result<Boolean> addShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> removeShopCategory(ShopCategory shopCategoryId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<ShopCategory> queryShopCategoryById(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize) {
        return null;
    }
}
