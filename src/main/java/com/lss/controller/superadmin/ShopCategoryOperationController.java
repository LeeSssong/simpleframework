package com.lss.controller.superadmin;

import com.lss.entity.bo.HeadLine;
import com.lss.entity.bo.ShopCategory;
import com.lss.entity.dto.Result;
import com.lss.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Autowired;
import org.simpleframework.core.annotation.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @authonr: LeeSongsheng
 * @create: 2020/06/24
 **/
@Controller
public class ShopCategoryOperationController {
    @Autowired
    private ShopCategoryService ShopCategoryService;

    public Result<Boolean> addShopCategory (HttpServletRequest request, HttpServletResponse response) {
        return ShopCategoryService.addShopCategory(new ShopCategory());
    }

    public Result<Boolean> removeShopCategory (HttpServletRequest request, HttpServletResponse response) {
        return ShopCategoryService.removeShopCategory(1);
    }

    public Result<Boolean> modifyShopCategory (HttpServletRequest request, HttpServletResponse response) {
        return ShopCategoryService.modifyShopCategory(new ShopCategory());
    }

    public Result<ShopCategory> queryShopCategoryById (HttpServletRequest request, HttpServletResponse response) {
        return ShopCategoryService.queryShopCategoryById(1);
    }

    public Result<List<ShopCategory>> queryShopCategory (HttpServletRequest request, HttpServletResponse response) {
        return ShopCategoryService.queryShopCategory(null, 1, 100);
    }
}
