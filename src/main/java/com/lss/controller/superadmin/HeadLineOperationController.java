package com.lss.controller.superadmin;

import com.lss.entity.bo.HeadLine;
import com.lss.entity.dto.Result;
import com.lss.service.solo.HeadLineService;
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
public class HeadLineOperationController {
    @Autowired
    private HeadLineService headLineService;

    public Result<Boolean> addHeadLine (HttpServletRequest request, HttpServletResponse response) {
        return headLineService.addHeadLine(new HeadLine());
    }

    public Result<Boolean> removeHeadLine (HttpServletRequest request, HttpServletResponse response) {
        return headLineService.removeHeadLine(1);
    }

    public Result<Boolean> modifyHeadLine (HttpServletRequest request, HttpServletResponse response) {
        return headLineService.modifyHeadLine(new HeadLine());
    }

    public Result<HeadLine> queryHeadLineById (HttpServletRequest request, HttpServletResponse response) {
        return headLineService.queryHeadLineById(1);
    }

    public Result<List<HeadLine>> queryHeadLine (HttpServletRequest request, HttpServletResponse response) {
        return headLineService.queryHeadLine(null, 1, 100);
    }
}
