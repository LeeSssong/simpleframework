package com.lss.controller.frontend;

import com.lss.entity.dto.MainPgeInfoDTO;
import com.lss.entity.dto.Result;
import com.lss.service.combine.HeadLineShopCategoryCombineService;
import lombok.Getter;
import org.simpleframework.core.annotation.Autowired;
import org.simpleframework.core.annotation.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @authonr: LeeSongsheng
 * @create: 2020/06/24
 **/
@Getter
@Controller
public class MainPageController {
    @Autowired(value = "HeadLineShopCategoryCombineServiceImpl")
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result<MainPgeInfoDTO> getMainPageInfo (HttpServletRequest request, HttpServletResponse response) {
        return headLineShopCategoryCombineService.getMainPageInfo();
    }
}
